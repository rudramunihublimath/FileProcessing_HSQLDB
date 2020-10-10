package com.io.demo.controller;

import com.io.demo.impl.MyLogRespService;
import com.io.demo.model.MyLogResp;
import com.io.demo.repo.MyLogRespRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyLogRespController {


    @Autowired
    MyLogRespService myLogRespService;

    //String filename ="C:\\Users\\Rudra\\Desktop\\logfile.txt";

    @GetMapping("/findallEvents")
    public List<MyLogResp> getallEvents(){
        List<MyLogResp> res=  myLogRespService.findevents();
        return res;
    }

    @GetMapping("/ReadFile")
    public String readfile(@RequestParam String filename){
        return myLogRespService.readfilesaveEvents(filename);
    }
}
