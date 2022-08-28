package com.doggiehome.doginfomgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EntityScan("com.doggiehome.doginfomgmt.pojo")
//@EntityScan({"com.doggiehome.doginfomgmt.pojo", "com.doggiehome.doginfomgmt.repository"})
@EntityScan({"com.doggiehome.doginfomgmt.repository", "com.doggiehome.doginfomgmt.pojo"})
@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing

public class DogInfoMgmtApplication {

    public static void main(String[] args) {
        SpringApplication.run(DogInfoMgmtApplication.class, args);
    }

}
