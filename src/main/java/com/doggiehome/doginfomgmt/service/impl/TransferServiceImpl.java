package com.doggiehome.doginfomgmt.service.impl;

import com.doggiehome.doginfomgmt.common.ResponseCode;
import com.doggiehome.doginfomgmt.common.ServerResponse;
import com.doggiehome.doginfomgmt.pojo.Transfer;
import com.doggiehome.doginfomgmt.repository.DogRepository;
import com.doggiehome.doginfomgmt.repository.TransferRepository;
import com.doggiehome.doginfomgmt.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    DogRepository dogRepository;

    @Override
    public ServerResponse transferSearch(String search,Integer pageNumber,Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC,"id"));
        Page<Transfer> objects = null;
        if (StringUtils.hasText(search)){
            objects = transferRepository.transferSearch(search,pageable);
        }else {
            objects =transferRepository.findAllByIsDelete(pageable);
        }
        List<Transfer> transferList = objects.getContent();
        Page<Transfer> result = new PageImpl<Transfer>(transferList, objects.getPageable(), objects.getTotalElements());
        return ServerResponse.successResponse(result);
    }

    @Transactional
    @Override
    public ServerResponse addTransfer(Transfer transfer) {
        Transfer save = transferRepository.save(transfer);
        if (save == null) {
            return ServerResponse.errorResponse(ResponseCode.PARAMETER_INVALID.getCode(), ResponseCode.PARAMETER_INVALID.getDesc());
        }
        int i = dogRepository.updateAdoptById(transfer.getId(), 3);
        if (i != 0){
            return ServerResponse.successResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc());
        }else{
            return ServerResponse.errorResponse(ResponseCode.DOG_NO_EXIST.getCode(), ResponseCode.DOG_NO_EXIST.getDesc());
        }
    }

    @Override
    public ServerResponse transferSearchById(Integer id) {
        return ServerResponse.successResponse(transferRepository.getById(id));
    }
}
