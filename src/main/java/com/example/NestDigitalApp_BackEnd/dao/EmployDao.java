package com.example.NestDigitalApp_BackEnd.dao;

import com.example.NestDigitalApp_BackEnd.model.Employ;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployDao extends CrudRepository<Employ, Integer> {

    @Query(value = "SELECT `id`, `designation`, `empcode`, `empname`, `password`, `salary`, `username` FROM `empdetails` WHERE `empcode`= :empcode",nativeQuery = true)
    List<Employ> EmpSearch(@Param("empcode") Integer empcode);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `empdetails` WHERE `id`= :id",nativeQuery = true)
    void DeleteEmploy(@Param("id") Integer id);

    @Query(value = "SELECT `id`, `designation`, `empcode`, `empname`, `password`, `salary`, `username` FROM `empdetails` WHERE `username`= :username AND `password`= :password",nativeQuery = true)
    List<Employ> EmployLogin(@Param("username") String username,@Param("password") String password);

    @Query(value = "SELECT `id`, `designation`, `empcode`, `empname`, `password`, `salary`, `username` FROM `empdetails` WHERE `id`= :id",nativeQuery = true)
    List<Employ> EmpViewByID(@Param("id") Integer id);
}
