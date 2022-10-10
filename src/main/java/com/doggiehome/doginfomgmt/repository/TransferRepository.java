package com.doggiehome.doginfomgmt.repository;

import com.doggiehome.doginfomgmt.pojo.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer,Integer> {

    @Query(value = "select * from transfer where name LIKE CONCAT('%',:search,'%') or dog_name LIKE CONCAT('%',:search,'%') or area LIKE CONCAT('%',:search,'%') ", nativeQuery = true)
    Transfer transferSearch(String search);
}
