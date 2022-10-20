package com.doggiehome.doginfomgmt.service.impl;

import com.doggiehome.doginfomgmt.common.ResponseCode;
import com.doggiehome.doginfomgmt.common.ServerResponse;
import com.doggiehome.doginfomgmt.pojo.Adopter;
import com.doggiehome.doginfomgmt.repository.AdoptRepository;
import com.doggiehome.doginfomgmt.repository.DogRepository;
import com.doggiehome.doginfomgmt.service.AdopterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class AdopterServiceImpl implements AdopterService {

    @Autowired
    private AdoptRepository adoptRepository;
    @Autowired
    private DogRepository dogRepository;

    @Transactional
    @Override
    public ServerResponse addAdopter(Adopter adopter) {
        Adopter save = adoptRepository.save(adopter);
        if ( save == null){
            return ServerResponse.errorResponse(ResponseCode.PARAMETER_INVALID.getCode(), ResponseCode.PARAMETER_INVALID.getDesc());
        }
        int i = dogRepository.updateAdoptById(adopter.getDogId(), 2);
        if (i == 1) {
            return ServerResponse.successResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc());
        }else{
            return ServerResponse.errorResponse(ResponseCode.DOG_NO_EXIST.getCode(), ResponseCode.ADOPT_NO_EXIST.getDesc());
        }
    }

    @Override
    public ServerResponse adopterSearch(String search, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC,"id"));
        Page<Adopter> objects = null;
        if (StringUtils.hasText(search)){
            objects = adoptRepository.adopterSearch(search,pageable);
        }else {
            objects =adoptRepository.findAllByIsDelete(pageable);
        }
        List<Adopter> adopterList = objects.getContent();
        Page<Adopter> result = new PageImpl<Adopter>(adopterList, objects.getPageable(), objects.getTotalElements());
        return ServerResponse.successResponse(result);
    }

    @Transactional
    @Override
    public ServerResponse backCage(Integer dogId, String description, Integer cageId) {
        int i1 = adoptRepository.deleteAdopterById(description,dogId);
        if (i1 == 0){
            return ServerResponse.errorResponse(ResponseCode.ADOPT_NO_EXIST.getCode(), ResponseCode.ADOPT_NO_EXIST.getDesc());
        }
        int i = dogRepository.updateCageByDogId(cageId,dogId, 1);
        if (i == 0){
            return ServerResponse.errorResponse(ResponseCode.DOG_NO_EXIST.getCode(), ResponseCode.ADOPT_NO_EXIST.getDesc());
        }else {
            return ServerResponse.successResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc());
        }
    }

    @Override
    public ServerResponse updateAdopter(Adopter adopter) {
        int i = adoptRepository.updateAdopter(adopter.getId(), adopter.getName(), adopter.getPhoneNumber(),
                adopter.getAdoptTime(), adopter.getArea(), adopter.getRemark());
        if (i == 0) {
            return ServerResponse.errorResponse(ResponseCode.ADOPT_NO_EXIST.getCode(), ResponseCode.ADOPT_NO_EXIST.getDesc());
        }else{
            return ServerResponse.successResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc());
        }
    }

    @Override
    public ServerResponse deleteAdopter(Integer dogId) {
        int i = adoptRepository.deleteAdopterById(dogId);
        if (i != 0){
            return ServerResponse.successResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc());
        }else{
            return ServerResponse.errorResponse(ResponseCode.ADOPT_NO_EXIST.getCode(), ResponseCode.ADOPT_NO_EXIST.getDesc());
        }
    }

}
