package com.ssafy.thezip.charter.domain;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(CharterKind.class)
public class    CharterKindTypeHandler extends BaseTypeHandler<CharterKind> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, CharterKind parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getKey());
    }

    @Override
    public CharterKind getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String charterKindKey = rs.getString(columnName);
        return getCharterKind(charterKindKey);
    }

    @Override
    public CharterKind getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String charterKindKey = rs.getString(columnIndex);
        return getCharterKind(charterKindKey);
    }

    @Override
    public CharterKind getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String charterKindKey = cs.getString(columnIndex);
        return getCharterKind(charterKindKey);
    }

    private CharterKind getCharterKind(String rentKey){
        CharterKind charterKind = null;
        switch(rentKey){
            case "월세":
                charterKind = CharterKind.MONTHLY_RENT;
                break;
            case "전세":
                charterKind = CharterKind.YEARLY_RENT;
                break;
        }

        return charterKind;
    }


}
