package com.doggiehome.doginfomgmt.repository;

import com.doggiehome.doginfomgmt.pojo.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, String> {
}
