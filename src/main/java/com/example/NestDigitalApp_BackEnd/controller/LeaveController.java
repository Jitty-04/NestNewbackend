package com.example.NestDigitalApp_BackEnd.controller;

import com.example.NestDigitalApp_BackEnd.dao.LeaveCounterDao;
import com.example.NestDigitalApp_BackEnd.dao.LiveApplicationDao;
import com.example.NestDigitalApp_BackEnd.dao.VisitorDao;
import com.example.NestDigitalApp_BackEnd.model.EmpLog;
import com.example.NestDigitalApp_BackEnd.model.Employ;
import com.example.NestDigitalApp_BackEnd.model.LeaveApplication;
import com.example.NestDigitalApp_BackEnd.model.LeaveCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LeaveController {
    @Autowired
    private LiveApplicationDao dao;
    @Autowired
    private LeaveCounterDao ldao;
    int casual,sick,special;

    Date currentdate=new Date();
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/leaveapply",produces = "application/json",consumes = "application/json")
    public Map<String,String> ApplyLeave(@RequestBody LeaveApplication l)
    {

        l.setApply_date(String.valueOf(currentdate));
        l.setStatus(0);
        dao.save(l);
        HashMap<String,String> map=new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/viewallleave")
    public List<Map<String,String>> ViewAllLeave()
    {
        return (List<Map<String, String>>) dao.ViewAllLeave();
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/update",consumes = "application/json",produces = "application/json")
    public Map<String,String> Updatestatus(@RequestBody LeaveApplication l)
    {
       String empid=String.valueOf(l.getEmpid());
        int status=l.getStatus();
        System.out.println(empid);
        System.out.println(status);
        dao.updateStatus(l.getEmpid(),l.getStatus());
        HashMap<String,String> map=new HashMap<>();
        map.put("status","success");
        return map;
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/searchstatus",consumes = "application/json",produces = "application/json")
    public List<LeaveApplication> SearchStatus(@RequestBody LeaveApplication l)
    {
        String empid=String.valueOf(l.getEmpid());
        System.out.println(empid);
        return (List<LeaveApplication>) dao.SearchStatus(l.getEmpid());
    }
    @CrossOrigin(origins = "*")
    @PostMapping(value = "updatecounter",consumes = "application/json",produces = "application/json")
    public Map<String,String> UpdateCounter(@RequestBody LeaveApplication l) throws ParseException {
        String empid=String.valueOf(l.getEmpid());

        List<LeaveApplication> result1=(List<LeaveApplication>) dao.SearchStatus(l.getEmpid());
        l.setLeavetype(result1.get(0).getLeavetype());

        LocalDate from_date= LocalDate.parse(result1.get(0).getFrom_date());
        LocalDate to_date=LocalDate.parse(result1.get(0).getTo_date());

        long daysDiff= ChronoUnit.DAYS.between(from_date,to_date)+1;
        System.out.println("no of days"+daysDiff);

        List<LeaveCounter> result=(List<LeaveCounter>) ldao.Leaves(l.getEmpid());
        long casual=result.get(0).getCasual();
        long sick=result.get(0).getSick();
        long other=result.get(0).getOther();



        if(l.getLeavetype().equalsIgnoreCase("casual")&& daysDiff<=casual){
            casual=casual-daysDiff;
            sick=sick;
            other=other;

            ldao.UpdateCounter(l.getEmpid(),(int) casual,(int) sick,(int) other);

        } else if (l.getLeavetype().equalsIgnoreCase("sick")&& daysDiff<=sick) {
            casual=casual;
            sick=sick-daysDiff;
            other=other;

            ldao.UpdateCounter(l.getEmpid(),(int) casual,(int) sick,(int) other);
        }else if (l.getLeavetype().equalsIgnoreCase("other") && daysDiff<=other){
            casual=casual;
            sick=sick;
            other=other-daysDiff;
            ldao.UpdateCounter(l.getEmpid(),(int) casual,(int) sick,(int) other);
        }else {
            HashMap<String,String> map=new HashMap<>();
            map.put("leavetype",l.getLeavetype());
            String id=String.valueOf(result.get(0).getEmpid());
            map.put("empid",id);
            map.put("message","np leaves are available");
            return map;
        }
        HashMap<String,String> map=new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path="/viewpendingleaves")
    public List<LeaveCounter> ViewPendingLeaves(@RequestBody LeaveCounter lc){
        String empid=String.valueOf(lc.getEmpid());
        System.out.println(empid);
        return (List<LeaveCounter>) ldao.ViewPendingLeaves(lc.getEmpid());
    }



}
