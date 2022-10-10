package com.doggiehome.doginfomgmt.repository;

import com.doggiehome.doginfomgmt.pojo.Adopter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptRepository extends JpaRepository<Adopter, Integer> {

    @Query(value = "select * from adopter where name LIKE CONCAT('%',:search,'%') or dog_name LIKE CONCAT('%',:search,'%')", nativeQuery = true)
    Adopter adopterSearch(String search);

    @Modifying
    @Query(value = "update adopter set name = :name, phone_number = :phoneNumber, area = :area, adopt_time = :adoptTime, remark = :remark where id = :id", nativeQuery = true)
    void updateAdopter(@Param("id") Integer id,
                       @Param("name") String name,
                       @Param("phoneNumber") Integer phoneNumber,
                       @Param("adoptTime") String adoptTime,
                       @Param("area") String area,
                       @Param("remark") String remark);
}
