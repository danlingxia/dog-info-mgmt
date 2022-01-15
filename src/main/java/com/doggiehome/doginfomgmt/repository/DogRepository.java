package com.doggiehome.doginfomgmt.repository;

import com.doggiehome.doginfomgmt.pojo.Dog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends CrudRepository<Dog, String> {
    Dog findDogById(String id);


}
