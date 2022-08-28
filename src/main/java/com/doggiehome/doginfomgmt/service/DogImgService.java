package com.doggiehome.doginfomgmt.service;

import com.doggiehome.doginfomgmt.pojo.DogImg;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DogImgService {

//    int saveDogImg();

    List<DogImg> getAllImg(int dogId);
}
