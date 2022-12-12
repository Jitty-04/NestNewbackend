package com.example.NestDigitalApp_BackEnd.controller;

import com.example.NestDigitalApp_BackEnd.dao.LoginDao;
import com.example.NestDigitalApp_BackEnd.dao.VisitorDao;
import com.example.NestDigitalApp_BackEnd.model.EmpLog;
import com.example.NestDigitalApp_BackEnd.model.VisitorLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private LoginDao dao;
    @Autowired
    private VisitorDao d;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/logemp",consumes = "application/json",produces = "application/json")
    public Map<String,String> EmployLog(@RequestBody EmpLog l)
    {
        System.out.println(l.getEmpid());
        System.out.println(l.getDate().toString());
        System.out.println(l.getEntry_datetime().toString());
        System.out.println(l.getExit_datetime().toString());
        dao.save(l);
        HashMap<String,String> map=new HashMap<>();
        map.put("status","success");
        return map;
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/logvisitor",consumes = "application/json",produces = "application/json")
    public Map<String,String> VisitorLog(@RequestBody VisitorLog v)
    {
        System.out.println(v.getName().toString());
        System.out.println(v.getPurpose().toString());
        System.out.println(v.getWhomtomeet().toString());
        System.out.println(v.getEntry_datetime().toString());
        System.out.println(v.getExit_datetime().toString());
        d.save(v);
        HashMap<String,String> map=new HashMap<>();
        map.put("status","success");
        return map;
    }


}
