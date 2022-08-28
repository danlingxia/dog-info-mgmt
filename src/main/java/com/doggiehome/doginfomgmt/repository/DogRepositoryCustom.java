package com.doggiehome.doginfomgmt.repository;

import com.doggiehome.doginfomgmt.pojo.Dog;
import io.swagger.models.auth.In;


import java.time.LocalDateTime;
import java.util.List;

public interface DogRepositoryCustom {

//    List<Dog> findDogs(int sex, List<Integer> range, int size, int hairLength,int pageNum, int pageSize);

    List<Dog> findDogIds(Integer sex, List<Integer> ageRange, Integer size, Integer hairLength);


//    List<DogListVo> findDogList(List<Integer> dogIds);
}
