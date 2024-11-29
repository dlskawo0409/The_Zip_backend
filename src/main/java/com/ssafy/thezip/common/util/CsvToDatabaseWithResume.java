package com.ssafy.thezip.common.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CsvToDatabaseWithResume {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/ssafyhome";
    private static final String USER = "admin";
    private static final String PASS = "ssafy1234";
    private static final String INSERT_SQL = "INSERT INTO charter " +
            "(pre_code, post_code, charter_kind, floor, deal_year, deal_month, deal_day, deposit, rent, name, construction_year, building_use, size," +
            "charter_dong, charter_gu, bonbun, bubun)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?)";

    private static final int BATCH_SIZE = 1000;
    private static final String PROGRESS_FILE = "C:\\ssafy\\progress.txt";
    private static final String ERROR_LOG_FILE = "C:\\ssafy\\error_log.txt";

    public static void main(String[] args) {
        String csvFilePath = "C:\\ssafy\\charter.csv";
        System.out.println(Paths.get(csvFilePath).normalize());
        int lastProcessedIndex = readProgress();
        long startTime = System.nanoTime();
        List<String[]> csvData = readCSV(csvFilePath, lastProcessedIndex);
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {
                System.out.println("connection success");
                insertDataWithBufferAndResume(conn, csvData, lastProcessedIndex);
            }
            long endTime = System.nanoTime();
            // 실행 시간 계산 (나노초에서 밀리초로 변환)
            long duration = (endTime - startTime) / 1_000_000; // 밀리초 단위로 변환
            System.out.println("Execution time: " + duration + " ms");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static List<String[]> readCSV(String filePath, int startFromIndex) {
        List<String[]> records = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            reader.readNext(); // 헤더 스킵
            int currentIndex = 0;
            String[] line;

            while ((line = reader.readNext()) != null) {
                if (currentIndex >= startFromIndex) {
                    records.add(line);
                }
                currentIndex++;
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return records;
    }

    private static void insertDataWithBufferAndResume(Connection conn, List<String[]> csvData, int startFromIndex) {
        try (PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL);
             BufferedWriter progressWriter = new BufferedWriter(new FileWriter(PROGRESS_FILE, false))) {

            int count = 0;
            int currentIndex = startFromIndex;

            for (String[] record : csvData) {
                try {
//                    "(dong_code, charter_kind, floor, deal_year, deal_month, deal_day, deposit, rent, name, construction_year, building_use,
//                    charter_dong, bonbun, bubun)" +
//                            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                if(record[7].trim().isEmpty() || record[8].trim().isEmpty()){
                    continue;
                }

                    pstmt.setString(1, record[1].trim());
                    pstmt.setString(2, record[3].trim());
                    pstmt.setString(3, record[11].trim());
                    pstmt.setString(4, record[9].trim());

                    String dealDate = record[10].trim();
                    if (dealDate.length() == 8) {
                        pstmt.setInt(5, Integer.parseInt(dealDate.substring(0, 4)));
                        pstmt.setInt(6, Integer.parseInt(dealDate.substring(4, 6)));
                        pstmt.setInt(7, Integer.parseInt(dealDate.substring(6, 8)));
                    } else {
                        pstmt.setNull(5, Types.INTEGER);
                        pstmt.setNull(6, Types.INTEGER);
                        pstmt.setNull(7, Types.INTEGER);
                    }

                    pstmt.setInt(8,Integer.parseInt(record[13].trim()));
                    pstmt.setInt(9, Integer.parseInt(record[14].trim()));
                    pstmt.setString(10, record[15].trim());
                    pstmt.setInt(11, record[16].isEmpty() ? 0 : Integer.parseInt(record[16].trim()));
                    pstmt.setString(12, record[17].trim());
                    pstmt.setFloat(13, Float.parseFloat(record[12].trim()));
                    pstmt.setString(14, record[2].trim());
                    pstmt.setString(15, record[4].trim());
                    pstmt.setInt(16, Integer.parseInt(record[8].trim()));
                    pstmt.setInt(17,Integer.parseInt(record[7].trim()));


                    pstmt.addBatch();
                    count++;
                    currentIndex++;


                    if (count % BATCH_SIZE == 0) {
                        pstmt.executeBatch();
                        progressWriter.write(String.valueOf(currentIndex));
                        progressWriter.newLine();
                        progressWriter.flush();
                    }
                } catch (SQLException e) {
                    logError(currentIndex, e, record);
                }
            }
            pstmt.executeBatch();
            progressWriter.write(String.valueOf(currentIndex));
            System.out.println("insert ok!");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

//    private static int parseAmount(String amount) {
//        if (amount == null || amount.isEmpty() || amount.equals("0")) {
//            return 0;
//        }
//        try {
//            return (int) (Double.parseDouble(amount.replace(",", "")) * 10000);
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }

    private static int readProgress() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PROGRESS_FILE))) {
            String lastLine = reader.readLine();
            if (lastLine != null) {
                System.out.println(Integer.parseInt(lastLine));
                return Integer.parseInt(lastLine);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("진행 상태를 찾을 수 없습니다. 0부터 시작합니다.");
        }
        return 0;
    }

    private static void logError(int index, Exception e, String[] record) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ERROR_LOG_FILE, true))) {
            writer.write("오류 발생 인덱스: " + index);
            writer.newLine();
            writer.write("오류 원인: " + e.getMessage());
            writer.newLine();
            writer.write("문제 데이터: " + String.join(",", record));
            writer.newLine();
            writer.write("--------------------------------------------------");
            writer.newLine();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
