package com.example.NestDigitalApp_BackEnd.dao;

import com.example.NestDigitalApp_BackEnd.model.EmpLog;
import com.example.NestDigitalApp_BackEnd.model.LeaveApplication;
import com.example.NestDigitalApp_BackEnd.model.LeaveCounter;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface LiveApplicationDao extends CrudRepository<LeaveApplication,Integer> {
    @Query(value="SELECT e. `empcode`, e.`empname`,l.leavetype,l.status,l.from_date,l.to_date FROM `empdetails` e JOIN leaveapplication l ON e.empcode=l.empid",nativeQuery = true)
    List<Map<String,String>> viewleave();

    @Modifying
    @Transactional
    @Query(value = "UPDATE `leaveapplication` SET `status`=:status  WHERE `empid`=:empid",nativeQuery = true)
    void updateStatus(@Param("empid") Integer empid,@Param("status")String status);
}
