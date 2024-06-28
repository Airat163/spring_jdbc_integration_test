package com.example.spring_jdbc.mapper;

import com.example.spring_jdbc.model.Manager;
import com.example.spring_jdbc.model.Project;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerRowMapper implements RowMapper<Manager> {
    @Override
    public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;/*new Manager(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("project").transform(this::ProjectTransform),
                rs.getString("company_name"));*/
    }

    /*Project ProjectTransform(String str) {
        if (str.equals("TS")) return Project.TEZIS;
        if (str.equals("SN")) return Project.SOCIAL_NETWORK;
        if (str.equals("PR_STRING")) return Project.PR;
        throw new IllegalArgumentException();
    }*/
}
