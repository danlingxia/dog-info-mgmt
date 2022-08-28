package com.doggiehome.doginfomgmt.repository;

import com.doggiehome.doginfomgmt.pojo.Cage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CageRepository extends JpaRepository<Cage, Integer> , JpaSpecificationExecutor {

    Cage findByYardIdAndName(int yardId, String name);


//    Boolean existById(int id);

    //    List<Cage> findAllByYardId(int yardId);


    @Modifying
    @Transactional
    int deleteAllById(int cageId);

    Page<Cage> findAllByYardId(int yardId, Pageable pageable);
}
