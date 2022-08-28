package com.doggiehome.doginfomgmt.service;

import com.doggiehome.doginfomgmt.common.ServerResponse;

public interface CageService {

    ServerResponse newCage(int yardId, String cageName);

//    ServerResponse cages(int yardId);

    ServerResponse findCage(int cageId);

    ServerResponse cages(int yardId, Integer cageId, String cageName, int pageNumber, int pageSize);

    ServerResponse deleteCage(int cageId);

    ServerResponse cageNames(int yardId);
}
