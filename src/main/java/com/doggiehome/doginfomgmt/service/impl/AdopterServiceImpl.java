package com.doggiehome.doginfomgmt.service.impl;

import com.doggiehome.doginfomgmt.common.ServerResponse;
import com.doggiehome.doginfomgmt.pojo.Adopter;
import com.doggiehome.doginfomgmt.repository.AdoptRepository;
import com.doggiehome.doginfomgmt.repository.DogRepository;
import com.doggiehome.doginfomgmt.service.AdopterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class AdopterServiceImpl implements AdopterService {

    @Autowired
    private AdoptRepository adoptRepository;
    @Autowired
    private DogRepository dogRepository;

    @Override
    public ServerResponse adopterSearch(String search) {
        // 如果为空 则为查询全部
        if (StringUtils.hasText(search)){
            Adopter adopter = adoptRepository.adopterSearch(search);
            return ServerResponse.successResponse(adopter);
        }else {
            List<Adopter> all = adoptRepository.findAll();
            return ServerResponse.successResponse(all);
        }
    }

    @Override
    public void updateAdopter(Adopter adopter) {
        adoptRepository.updateAdopter(adopter.getId(),adopter.getName(), adopter.getPhoneNumber(),
                adopter.getAdoptTime(),adopter.getArea(),adopter.getRemark());
    }

    @Override
    public void deleteAdopter(Integer dogId) {
        adoptRepository.deleteById(dogId);
        dogRepository.deleteById(dogId);
    }

    @Override
    public void backCage(Integer dogId) {
        adoptRepository.deleteById(dogId);
        dogRepository.updateAdoptById(dogId,5);
    }
}
