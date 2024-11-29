package com.ssafy.thezip.member.domain;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@MappedTypes(Role.class)
public class RoleTypeHandler implements TypeHandler<Role> {


    @Override
    public void setParameter(PreparedStatement ps, int i, Role parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getKey());
    }

    @Override
    // 칼럼 이름 기반으로 조회된 값을 활용해서 실제 반환할 객체 구성하기
    public Role getResult(ResultSet rs, String columnName) throws SQLException {
        String roleKey = rs.getString(columnName);
        return getRole(roleKey);
    }

    @Override
    // 칼럼 index 기반으로 조회된 값을 활용해서
    public Role getResult(ResultSet rs, int columnIndex) throws SQLException {
        String roleKey = rs.getString(columnIndex);
        return getRole(roleKey);
    }

    @Override
    //CallableStatement에서 칼럼 index 기반으로 조회된 값을 활용해서 실제 반환할 객체 구성
    public Role getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String roleKey = cs.getString(columnIndex);
        return getRole(roleKey);
    }

    private Role getRole(String roleKey){
        Role role = null;
        switch (roleKey){
            case "ROLE_ADMIN":
                role = Role.ADMIN;
                break;
            case "ROLE_USER":
                role = Role.USER;
                break;

            case "ROLE_AGENT":
                role = Role.AGENT;
                break;

            default:
                role = Role.GUEST;
                break;
        }

        return role;
    }
}
