package com.doggiehome.doginfomgmt.pojo.vo;

import com.doggiehome.doginfomgmt.pojo.Dog;
import com.doggiehome.doginfomgmt.pojo.DogImg;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DogVo {
    /**
     * 狗狗信息
     */
    private Dog dog;

    /**
     * 狗狗图片地址
     */
    private List<DogImg> dogImgList;
}
