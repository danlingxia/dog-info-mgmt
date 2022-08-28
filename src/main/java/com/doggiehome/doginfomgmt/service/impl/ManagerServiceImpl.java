package com.doggiehome.doginfomgmt.service.impl;

import com.doggiehome.doginfomgmt.common.ResponseCode;
import com.doggiehome.doginfomgmt.common.ServerResponse;
import com.doggiehome.doginfomgmt.pojo.Manager;
import com.doggiehome.doginfomgmt.pojo.vo.ManagerVo;
import com.doggiehome.doginfomgmt.repository.ManagerRepository;
import com.doggiehome.doginfomgmt.service.ManagerService;
import com.doggiehome.doginfomgmt.util.MD5Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.EnumMap;

@Service("ManagerService")
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public ServerResponse login(String username, String password) {


//        Manager manager = new Manager();
//        manager.setUsername(username);
        Manager managerData = managerRepository.findManagerByUsername(username);
        if (null == managerData){
            return ServerResponse.errorResponse(ResponseCode.NO_MANAGER.getCode(), ResponseCode.NO_MANAGER.getDesc());
        }
        String sk = managerData.getSecretKey();
        String encodedMsg = managerData.getPassword();
        try {
            EnumMap<MD5Util.EncodePair, String> enumMap = MD5Util.fixedSaltMD5(sk, password);
            String encodedPassword = enumMap.get(MD5Util.EncodePair.ENCODED_MSG);
            if (!encodedMsg.equals("") && encodedPassword.equals(encodedMsg)){
                ManagerVo managerVo = new ManagerVo(managerData);
                return ServerResponse.successResponse(ResponseCode.SUCCESS.getCode(),
                        ResponseCode.SUCCESS.getDesc(), managerVo);
            }else {
                return ServerResponse.errorResponse(ResponseCode.WRONG_PASSWORD.getCode(), ResponseCode.WRONG_PASSWORD.getDesc());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "出现服务器问题");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ServerResponse newManager(String username, String password, int role) {
        try {
            EnumMap<MD5Util.EncodePair, String> enumMap = MD5Util.randomSaltMD5(password);
            String salt = enumMap.get(MD5Util.EncodePair.SALT);
            String encodedMsg = enumMap.get(MD5Util.EncodePair.ENCODED_MSG);
            Manager manager = new Manager();
            manager.setUsername(username);
            manager.setPassword(encodedMsg);
            manager.setSecretKey(salt);
            manager.setRole(role);
            managerRepository.save(manager);
            return ServerResponse.successResponse(manager);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "新增管理员失败");
    }
}
