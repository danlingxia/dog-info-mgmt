package com.doggiehome.doginfomgmt.repository;

import com.doggiehome.doginfomgmt.pojo.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

    Manager findManagerByUsername(String name);

}
