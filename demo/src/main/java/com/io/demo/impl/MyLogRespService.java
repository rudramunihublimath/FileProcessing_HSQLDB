package com.io.demo.impl;

import com.google.gson.Gson;
import com.io.demo.model.MyLog;
import com.io.demo.model.MyLogResp;
import com.io.demo.repo.MyLogRespRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MyLogRespService {
    @Autowired
    MyLogRespRepo myLogRespRepo;

    public List<MyLogResp> findevents() {
        return myLogRespRepo.findAll();
    }


    public String readfilesaveEvents(String filename) {
        FileReader fr= null;

        try {
            fr = new FileReader(filename);
            Gson gson = new Gson();
            Map<String, MyLog> map= new HashMap<>();
            int i;
            String str="";
            while((i=fr.read())!=-1){
                // System.out.print((char)i);
                str= str+ (char)i;
                if(str.contains("{")  && str.contains("}")){
                    MyLog logreq = gson.fromJson(str,MyLog.class);


                    if(map.containsKey(logreq.getId())){
                        //Traversing map
                        for(Map.Entry<String, MyLog> entry:map.entrySet()){
                            String key=entry.getKey();
                            if(key.equals(logreq.getId())){
                                MyLog b=entry.getValue();
                                //System.out.println(key+" Details:");
                                //System.out.println(b.getId()+":"+b.getHost()+":"+b.getType()+":"+b.getState()+":"+b.getTimestamp());

                                MyLogResp myLogResp = new MyLogResp();
                                long dur = Math.abs(b.getTimestamp()-logreq.getTimestamp());
                                boolean alerflag=  (dur > 4)? true:false;
                                Optional<String> checkNull = Optional.ofNullable(b.getType());
                                if(checkNull.isPresent()){
                                    myLogResp.setType(checkNull.get());
                                }
                                Optional<String> checkNull2 = Optional.ofNullable(b.getHost());
                                if(checkNull2.isPresent()){
                                    myLogResp.setHost(checkNull2.get());
                                }

                                myLogResp.setId(logreq.getId());
                                myLogResp.setDuration(dur);
                                //myLogResp.setType("T");
                                //myLogResp.setHost("H");
                                myLogResp.setAlert(alerflag);
                                myLogRespRepo.save(myLogResp);
                                map.remove(logreq.getId());  // remove id from map if its Finished
                                break;
                            }
                        }



                    }
                    else {
                        map.put(logreq.getId(),logreq);
                        System.out.println("Added in map :"+logreq.getId());
                    }

                    //System.out.println(log);
                    str= "";

                }
            }
            //System.out.println(str);
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "file processed";
    }


}
