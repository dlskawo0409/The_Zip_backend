package com.ssafy.thezip.charter.domain;


import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@MappedTypes(BuildingUse.class)
public class BuildingUseTypeHandler extends BaseTypeHandler<BuildingUse> {


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BuildingUse parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getKey());
    }

    @Override
    public BuildingUse getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String buildingUseKey = rs.getString(columnName);
        return getBuildingUse(buildingUseKey);
    }

    @Override
    public BuildingUse getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String buildingUseKey = rs.getString(columnIndex);
        return getBuildingUse(buildingUseKey);
    }

    @Override
    public BuildingUse getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String buildingUseKey = cs.getString(columnIndex);
        return getBuildingUse(buildingUseKey);
    }

    private BuildingUse getBuildingUse(String buildingUseKey ){
        BuildingUse buildingUse = null;
        switch (buildingUseKey){
            case "오피스텔":
                buildingUse = BuildingUse.OFFICE;
            break;

            case "단독다가구" :
                buildingUse = BuildingUse.SINGLE_FAMILY;
                break;

            case "아파트" :
                buildingUse = BuildingUse.APARTMENT;
                break;

            case "연립다세대" :
                buildingUse = BuildingUse.MULTI_FAMILY;
                break;

        }
        return buildingUse;
    }


}
