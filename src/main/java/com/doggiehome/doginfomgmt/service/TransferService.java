package com.doggiehome.doginfomgmt.service;

import com.doggiehome.doginfomgmt.common.ServerResponse;
import com.doggiehome.doginfomgmt.pojo.Transfer;

public interface TransferService {

    ServerResponse transferSearch(String search,Integer pageNumber,Integer pageSize);

    ServerResponse addTransfer(Transfer transfer);

    ServerResponse transferSearchById(Integer dogName);
}
