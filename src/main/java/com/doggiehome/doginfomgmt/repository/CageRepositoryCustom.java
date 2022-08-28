package com.doggiehome.doginfomgmt.repository;

import com.doggiehome.doginfomgmt.pojo.Cage;
import com.doggiehome.doginfomgmt.pojo.vo.CageVo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CageRepositoryCustom {

//    List<Dog> findDogs(int sex, List<Integer> range, int size, int hairLength,int pageNum, int pageSize);

//    List<Dog> findDogIds(int sex, List<Integer> range, int size, int hairLength);

    Page<Cage> findCageByParam(int yardId, Integer cageId, String cageName, int pageNumber, int pageSize);

    List<CageVo> getCageNames(int yardId);

//    List<DogListVo> findDogList(List<Integer> dogIds);
}
