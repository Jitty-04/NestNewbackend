package com.example.NestDigitalApp_BackEnd.dao;

import com.example.NestDigitalApp_BackEnd.model.EmpLog;
import com.example.NestDigitalApp_BackEnd.model.LeaveApplication;
import com.example.NestDigitalApp_BackEnd.model.LeaveCounter;
import org.springframework.data.repository.CrudRepository;

public interface LiveApplicationDao extends CrudRepository<LeaveApplication,Integer> {
}
