package com.doggiehome.doginfomgmt.service.impl;

import com.doggiehome.doginfomgmt.common.ResponseCode;
import com.doggiehome.doginfomgmt.common.ServerResponse;
import com.doggiehome.doginfomgmt.pojo.Cage;
import com.doggiehome.doginfomgmt.pojo.Dog;
import com.doggiehome.doginfomgmt.pojo.vo.CageVo;
import com.doggiehome.doginfomgmt.repository.CageRepository;
import com.doggiehome.doginfomgmt.repository.CageRepositoryCustom;
import com.doggiehome.doginfomgmt.repository.DogRepository;
import com.doggiehome.doginfomgmt.service.CageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CageServiceImpl implements CageService {

    @Autowired
    CageRepository cageRepository;

    @Autowired
    DogRepository dogRepository;

    @Autowired
    CageRepositoryCustom cageRepositoryCustom;

    @Override
    public ServerResponse newCage(int yardId, String cageName) {
        Cage cageFind = cageRepository.findByYardIdAndName(yardId, cageName);
        if (null != cageFind){
//            同一小院已存在该名称笼子就不能创建
            return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "已存在该笼子");
        }
        Cage cage = new Cage();
        cage.setName(cageName);
        cage.setYardId(yardId);
        Cage cageNewed = cageRepository.save(cage);

        return ServerResponse.successResponse(cageNewed);
    }

    /**
     * 查询笼子存不存在
     * @param cageId
     * @return
     */
    @Override
    public ServerResponse findCage(int cageId){
        Boolean exist = cageRepository.existsById(cageId);
        return ServerResponse.successResponse(exist);
    }

    @Override
    public ServerResponse cages(int yardId, Integer cageId, String cageName, int pageNumber, int pageSize) {
        //id生序排序
//        Sort sort = new Sort;
//        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC,"id"));
//        Pageable pageable = new PageRequest(pageNumber, pageSize, );


//        Page<Cage> cagePage =  cageRepository.findAllByYardId(yardId, pageable);

        Page<Cage> cagePage = cageRepositoryCustom.findCageByParam(yardId, cageId, cageName, pageNumber, pageSize);
//        if (null != cageFind){
//            return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "已存在该笼子");
//        }
//        Cage cage = new Cage();
//        cage.setName(cageName);
//        cage.setYardId(yardId);
//        Cage cageNewed = cageRepository.save(cage);

        return ServerResponse.successResponse(cagePage);
    }

    @Override
    public ServerResponse deleteCage(int cageId) {
        List<Dog> dogs = dogRepository.findAllByCageId(cageId);
        if (dogs.size() > 0){
            return ServerResponse.errorResponse(ResponseCode.CAGE_NO_EMPTY.getCode(), ResponseCode.CAGE_NO_EMPTY.getDesc());
        }
        int num = cageRepository.deleteAllById(cageId);
        if (num == 0){
            return ServerResponse.errorResponse(ResponseCode.CAGE_NO_EXIST.getCode(), ResponseCode.CAGE_NO_EXIST.getDesc());
        }else {
            return ServerResponse.successResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc());
        }
    }

    @Override
    public ServerResponse cageNames(int yardId) {
        List<CageVo> cageVos = cageRepositoryCustom.getCageNames(yardId);
        return ServerResponse.successResponse(cageVos);
    }


}
