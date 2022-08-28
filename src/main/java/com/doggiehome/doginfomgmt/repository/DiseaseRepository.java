package com.doggiehome.doginfomgmt.repository;

import com.doggiehome.doginfomgmt.pojo.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, String> {
}
