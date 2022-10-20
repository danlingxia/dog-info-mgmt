package com.doggiehome.doginfomgmt.repository;

import com.doggiehome.doginfomgmt.pojo.Adopter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptRepository extends JpaRepository<Adopter, Integer> {

    @Query(value = "select * from adopter where is_delete = 0 and name LIKE CONCAT('%',:search,'%') or dog_name LIKE CONCAT('%',:search,'%')", nativeQuery = true)
    Page<Adopter> adopterSearch(String search, Pageable pageable);

    @Query(value = "select * from adopter where is_delete = 0", nativeQuery = true)
    Page<Adopter> findAllByIsDelete(Pageable pageable);

    @Modifying
    @Query(value = "update adopter set name = :name, phone_number = :phoneNumber, area = :area, adopt_time = :adoptTime, remark = :remark where id = :id and is_delete = 0", nativeQuery = true)
    int updateAdopter(@Param("id") Integer id,
                       @Param("name") String name,
                       @Param("phoneNumber") Integer phoneNumber,
                       @Param("adoptTime") String adoptTime,
                       @Param("area") String area,
                       @Param("remark") String remark);

    @Modifying
    @Query(value = "update adopter set is_delete = 1, description = :description where dog_id = :dogId", nativeQuery = true)
    int deleteAdopterById(@Param("description") String description, @Param("dogId") int dogId);

    @Modifying
    @Query(value = "update adopter set is_delete = 1, remark = :remark where dog_d = :dogId", nativeQuery = true)
    int deleteAdopterById(@Param("dogId") Integer dogId);
}
