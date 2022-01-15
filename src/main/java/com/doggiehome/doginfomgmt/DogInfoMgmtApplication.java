package com.doggiehome.doginfomgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.doggiehome.doginfomgmt.pojo")
@SpringBootApplication
public class DogInfoMgmtApplication {

    public static void main(String[] args) {
        SpringApplication.run(DogInfoMgmtApplication.class, args);
    }

}
