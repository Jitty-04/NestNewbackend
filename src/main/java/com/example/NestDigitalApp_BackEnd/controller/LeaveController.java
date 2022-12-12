package com.example.NestDigitalApp_BackEnd.controller;

import com.example.NestDigitalApp_BackEnd.dao.LiveApplicationDao;
import com.example.NestDigitalApp_BackEnd.dao.VisitorDao;
import com.example.NestDigitalApp_BackEnd.model.EmpLog;
import com.example.NestDigitalApp_BackEnd.model.LeaveApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
@CrossOrigin(origins = "*")
    @GetMapping(path="/viewallleave")
    public List<Map<String,String>>viewallleave(){
        return(List<Map<String, String>>) dao.viewleave();
}
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/update",consumes = "application/json",produces = "application/json")
    public Map<String,String> Updatestatus(@RequestBody LeaveApplication l)
    {
       String empid=String.valueOf(l.getEmpid());
        String status=l.getStatus().toString();
        System.out.println(empid);
        System.out.println(status);
        dao.updateStatus(l.getEmpid(),l.getStatus());
        HashMap<String,String> map=new HashMap<>();
        map.put("status","success");
        return map;
    }



}
