package com.doggiehome.doginfomgmt.service;

import com.doggiehome.doginfomgmt.common.ServerResponse;
import com.doggiehome.doginfomgmt.pojo.bo.DogBo;
import com.doggiehome.doginfomgmt.pojo.bo.DogModifyBo;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
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

//    ServerResponse queryDogs();

    /**
     * 保存一条狗狗信息
     * @param dogBo
     * @return
     */
    ServerResponse newDog(DogBo dogBo) throws IOException;

    /**
     * 查找狗狗信息
     * @param dogId
     * @return
     */
    ServerResponse findOneDog(int dogId);


//    ServerResponse findDogs(int sex, List<Integer> month, int size, int hairLength, int pageNum, int pageSize);

    ServerResponse findDogIds(Integer sex, List<Integer> month, Integer size, Integer hairLength);

    ServerResponse findDogList(List<Integer> dogIds);

    ServerResponse findDogsByCageAndYard(int yardId, int cageId, int pageNumber, int pageSize);

    ServerResponse findDogByTerm(int yardId, Integer cageId, String cageName, String identifier, String dogName, int pageNumber, int pageSize);

    ServerResponse modifyADog(DogModifyBo dogModifyBo) throws IOException;

    ServerResponse deleteADog(int dogId);

//    ServerResponse existADog(int dogId);

//    ServerResponse getDogByCageId(int cageId);
}
