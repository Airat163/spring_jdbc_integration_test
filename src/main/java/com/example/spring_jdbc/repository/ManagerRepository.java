package com.example.spring_jdbc.repository;

import com.example.spring_jdbc.model.Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Repository
public class ManagerRepository implements DaoManagerRepository<Manager> {

    private final JdbcTemplate jdbcTemplate;
    public static final String SAVE_MANAGER =
            "INSERT INTO manager(name, age, project, company_name)" +
                    " VALUES(?,?,?,?)";
    public static final String DELETE_MANAGER_BY_ID = "DELETE FROM manager WHERE id = ?";
    public static final String FIND_BY_ID_MANAGER = "SELECT * FROM manager WHERE id = ?";
    public static final String FIND_ALL_MANAGER = "SELECT * FROM manager";
    public static final String UPDATE_MANAGER_BY_ID =
            "UPDATE manager SET " +
                    "name = ?," +
                    "age = ?," +
                    "project = ?," +
                    "company_name = ? " +
                    "WHERE id = ?";

    public static final String FIND_COUNTS_GROUP_BY_COMPANY =
            "SELECT company_name, COUNT(*) as count FROM manager GROUP BY company_name";

    public static final String FIND_AVG_AGES = "SELECT AVG(age) as avg_age FROM manager";
    public static final String FIND_MANAGERS_BY_PROJECT = "SELECT * FROM manager where project = ?";
    public static final String FIND_YOUNG_MANAGER_IN_COMPANY = "SELECT * FROM manager WHERE company_name = ? " +
            "ORDER BY age LIMIT 1";

    @Override
    public void createManager(Manager manager) {
        jdbcTemplate.update(SAVE_MANAGER, manager.getName(),
                manager.getAge(), manager.getProject().name()
                , manager.getCompanyName());
    }

    @Override
    public void deleteManager(Integer id) {
        jdbcTemplate.update(DELETE_MANAGER_BY_ID, id);
    }

    @Override
    public Optional<Manager> findByIdManager(Integer id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                FIND_BY_ID_MANAGER,
                new Object[]{id},
                new BeanPropertyRowMapper<>(Manager.class)));
    }

    @Override
    public List<Manager> findAllManager() {
        return jdbcTemplate.query(FIND_ALL_MANAGER, new BeanPropertyRowMapper<>(Manager.class));
    }

    @Override
    public void updateManager(Manager manager) {
        jdbcTemplate.update(
                UPDATE_MANAGER_BY_ID,
                manager.getName(),
                manager.getAge(),
                manager.getProject(),
                manager.getCompanyName(),
                manager.getId());
    }

    @Override
    public Map<String, Integer> findCountsManagersByGroupByCompanyName() {
        Map<String, Integer> result = new HashMap<>();
        jdbcTemplate.query(
                FIND_COUNTS_GROUP_BY_COMPANY,
                rs -> {
                    String companyName = rs.getString("company_name");
                    Integer count = rs.getInt("count");
                    result.put(companyName, count);
                });
        return result;
    }

    @Override
    public Double findAvgAges() {
        Double result;
        return jdbcTemplate.queryForObject(FIND_AVG_AGES, Double.class);
    }

    @Override
    public List<Manager> findManagersByProject(String str) {
        return jdbcTemplate.query(FIND_MANAGERS_BY_PROJECT,
                new Object[]{str},
                new BeanPropertyRowMapper<>(Manager.class));
    }

    @Override
    public Optional<Manager> findYoungManagerInCompany(String company) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_YOUNG_MANAGER_IN_COMPANY,new Object[] {company}, new BeanPropertyRowMapper<>(Manager.class)));
    }


}
