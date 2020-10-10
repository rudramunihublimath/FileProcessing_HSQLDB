package com.io.demo.repo;

import com.io.demo.model.MyLogResp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyLogRespRepo extends JpaRepository<MyLogResp,String > {

}
