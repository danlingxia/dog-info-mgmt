package com.doggiehome.doginfomgmt.repository;

import com.doggiehome.doginfomgmt.pojo.DogImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogImgRepository extends JpaRepository<DogImg, Integer> {

    List<DogImg> findAllByDogId(int dogId);

    int deleteAllByDogId(int dogId);


}
