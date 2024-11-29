package com.ssafy.thezip.common.util;

import com.opencsv.CSVReader;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class CollegeDataProcessorWithCSVReader {
    public static void main(String[] args) {
        String csvFile = "C:\\SSAFY\\college.csv"; // CSV 파일 경로
        String jdbcUrl = "jdbc:mysql://localhost:3306/ssafyhome?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
        String username = "ssafy"; // DB 사용자명
        String password = "ssafy"; // DB 비밀번호

        String insertQuery = "INSERT INTO college (college_name, college_english_name, branch_type, region_name, road_address, homepage) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        Set<String> uniqueColleges = new HashSet<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(csvFile), "EUC-KR"));
             Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {

            // 자동 커밋 비활성화
            connection.setAutoCommit(false);

            String[] line;
            int batchCount = 0;
            int batchSize = 100; // 배치 크기

            // 첫 번째 줄은 헤더이므로 건너뜀
            reader.readNext();

            while ((line = reader.readNext()) != null) {
                if(line[0].contains("사이버")){
                    continue;
                }

                StringTokenizer stringTokenizer = new StringTokenizer(line[0]);
                String collegeName = stringTokenizer.nextToken();
                String collegeEnglishName = line[1];
                String branchType = line[2];
                String universityType = line[3];
                String collegeType = line[4];
                String regionName = line[7];
                String roadAddress = line[8];
                String homepage = line[12];

                // 조건 필터링
                if (!collegeType.equals("사이버대학(대학)") &&
                        uniqueColleges.add(collegeName + "|" + branchType)) {

                    pstmt.setString(1, collegeName);
                    pstmt.setString(2, collegeEnglishName);
                    pstmt.setString(3, branchType);
                    pstmt.setString(4, regionName);
                    pstmt.setString(5, roadAddress);
                    pstmt.setString(6, homepage);

                    pstmt.addBatch();
                    batchCount++;

                    // 배치 실행
                    if (batchCount % batchSize == 0) {
                        pstmt.executeBatch();
                        connection.commit();
                    }
                }
            }

            // 남은 배치 실행
            if (batchCount % batchSize != 0) {
                pstmt.executeBatch();
                connection.commit();
            }

            System.out.println("Data insertion completed!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
