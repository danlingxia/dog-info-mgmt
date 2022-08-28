package com.doggiehome.doginfomgmt.service;

import com.doggiehome.doginfomgmt.common.ServerResponse;

public interface ManagerService {
    ServerResponse login(String username, String password);

    ServerResponse newManager(String username, String password, int role);

}
