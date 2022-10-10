package com.doggiehome.doginfomgmt.service;

import com.doggiehome.doginfomgmt.pojo.Transfer;

public interface TransferService {

    Transfer transferSearch(String search);

    void addTransfer(Transfer transfer);
}
