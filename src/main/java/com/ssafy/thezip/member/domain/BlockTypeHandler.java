package com.ssafy.thezip.member.domain;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BlockTypeHandler extends BaseTypeHandler<Blocked> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Blocked parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter == Blocked.T ? "T" : "F");
    }

    @Override
    public Blocked getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return "T".equals(value) ? Blocked.T : Blocked.F;
    }

    @Override
    public Blocked getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return "T".equals(value) ? Blocked.T : Blocked.F;
    }

    @Override
    public Blocked getNullableResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return "T".equals(value) ? Blocked.T : Blocked.F;
    }
}
