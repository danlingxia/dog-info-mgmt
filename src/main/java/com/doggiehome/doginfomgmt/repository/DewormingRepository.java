package com.doggiehome.doginfomgmt.repository;

import com.doggiehome.doginfomgmt.pojo.Deworming;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DewormingRepository extends JpaRepository<Deworming, String> {
}
