package com.doggiehome.doginfomgmt.service.impl;

import com.doggiehome.doginfomgmt.pojo.Dog;
import com.doggiehome.doginfomgmt.repository.DogRepository;
import com.doggiehome.doginfomgmt.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogServiceImpl implements DogService {
    @Autowired
    DogRepository dogRepository;


//    @Override
//    public Dog queryOneDogByName(String id) {
//        return null;
//    }
//
//    @Override
//    public List<Dog> queryDogs() {
//        return null;
//    }

    @Override
    public int saveOneDog(Dog dog) {
        dogRepository.save(dog);
//        dogRepository.
        return 0;
    }
}
