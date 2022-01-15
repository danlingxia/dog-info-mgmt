package com.doggiehome.doginfomgmt.service;

import com.doggiehome.doginfomgmt.pojo.Dog;

import java.util.List;

public interface DogService {

//    /**
//     * 根据编号查询一条狗狗信息
//     * @param id
//     * @return
//     */
//    public Dog queryOneDogByName(String id);
//
//    /**
//     * 查询所有狗狗信息
//     * @return
//     */
//    public List<Dog> queryDogs();
//
    /**
     * 保存一条狗狗信息
     * @param dog
     * @return
     */
    int saveOneDog(Dog dog);
}
