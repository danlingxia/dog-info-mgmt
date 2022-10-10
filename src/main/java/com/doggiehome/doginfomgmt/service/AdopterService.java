package com.doggiehome.doginfomgmt.service;

import com.doggiehome.doginfomgmt.common.ServerResponse;
import com.doggiehome.doginfomgmt.pojo.Adopter;

public interface AdopterService {
    ServerResponse adopterSearch(String search);

    void updateAdopter(Adopter adopter);

    void deleteAdopter(Integer dogId);

    void backCage(Integer dogId);
}
