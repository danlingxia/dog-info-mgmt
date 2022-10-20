package com.doggiehome.doginfomgmt.service;

import com.doggiehome.doginfomgmt.common.ServerResponse;
import com.doggiehome.doginfomgmt.pojo.Adopter;

public interface AdopterService {

    ServerResponse updateAdopter(Adopter adopter);

    ServerResponse deleteAdopter(Integer dogId);

    ServerResponse addAdopter(Adopter adopter);

    ServerResponse backCage(Integer dogId, String question, Integer cageId);

    ServerResponse adopterSearch(String search, Integer pageNumber, Integer pageSize);
}
