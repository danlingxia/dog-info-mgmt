package com.doggiehome.doginfomgmt.service.impl;

import com.doggiehome.doginfomgmt.pojo.Transfer;
import com.doggiehome.doginfomgmt.repository.DogRepository;
import com.doggiehome.doginfomgmt.repository.TransferRepository;
import com.doggiehome.doginfomgmt.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    DogRepository dogRepository;

    @Override
    public Transfer transferSearch(String search) {
        Transfer transfer = transferRepository.transferSearch(search);
        return transfer;
    }

    @Override
    public void addTransfer(Transfer transfer) {
        transferRepository.save(transfer);
        dogRepository.updateAdoptById(transfer.getId(),3);
    }
}
