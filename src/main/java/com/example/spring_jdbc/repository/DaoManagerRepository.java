package com.example.spring_jdbc.repository;

import com.example.spring_jdbc.model.Manager;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DaoManagerRepository<T> {

    void createManager(T t);

    void deleteManager(Integer id);

    Optional<T> findByIdManager(Integer id);

    List<T> findAllManager();

    void updateManager(T t);

    Map<String,Integer> findCountsManagersByGroupByCompanyName();

    Double findAvgAges();

    List<T> findManagersByProject(String str);

    Optional<T> findYoungManagerInCompany(String company);
}
