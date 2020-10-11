package com.io.demo.controller;

import com.io.demo.impl.MyLogRespService;
import com.io.demo.model.MyLogResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
public class MyLogRespController {

    private static final Logger logger = LoggerFactory.getLogger(MyLogRespController.class);

    @Autowired
    MyLogRespService myLogRespService;

    //String filename ="C:\\Users\\Rudra\\Desktop\\logfile.txt";

    @GetMapping("/findallEvents")
    public List<MyLogResp> getallEvents(){
        logger.info("getallEvents start");
        List<MyLogResp> res=  myLogRespService.findevents();
        return res;
    }

    @GetMapping("/ReadFile")
    public String readfile(@RequestParam String filename){
        logger.info("File processing start");
        return myLogRespService.readfilesaveEvents(filename);
    }
}
