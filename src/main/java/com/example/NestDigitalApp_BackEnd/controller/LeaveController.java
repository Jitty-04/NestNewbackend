package com.example.NestDigitalApp_BackEnd.controller;

import com.example.NestDigitalApp_BackEnd.dao.LiveApplicationDao;
import com.example.NestDigitalApp_BackEnd.dao.VisitorDao;
import com.example.NestDigitalApp_BackEnd.model.EmpLog;
import com.example.NestDigitalApp_BackEnd.model.LeaveApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LeaveController {
    @Autowired
    private LiveApplicationDao dao;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/leaveapp",consumes = "application/json",produces = "application/json")
    public Map<String,String> Leaveapp(@RequestBody LeaveApplication l)
    {
        System.out.println(l.getId());
        System.out.println(l.getEmpid());
        System.out.println(l.getLeavetype().toString());
        System.out.println(l.getApply_date().toString());
        System.out.println(l.getFrom_date().toString());
        System.out.println(l.getTo_date().toString());
        System.out.println(l.getRemarks().toString());
        System.out.println(l.getStatus().toString());
        dao.save(l);
        HashMap<String,String> map=new HashMap<>();
        map.put("status","success");
        return map;
    }


}
