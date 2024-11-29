package com.ssafy.thezip.dormitory.domain;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(DormitoryKind.class)
public class DormitoryKindTypeHandler extends BaseTypeHandler<DormitoryKind> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, DormitoryKind parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getDescription()); // "대학생"으로 저장
    }

    @Override
    public DormitoryKind getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String description = rs.getString(columnName);
        return DormitoryKind.fromDescription(description); // "대학생" → STUDENT
    }

    @Override
    public DormitoryKind getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String description = rs.getString(columnIndex);
        return DormitoryKind.fromDescription(description); // "대학생" → STUDENT
    }

    @Override
    public DormitoryKind getNullableResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
        String description = cs.getString(columnIndex);
        return DormitoryKind.fromDescription(description); // "대학생" → STUDENT
    }
}