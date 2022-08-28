package com.doggiehome.doginfomgmt.service.impl;

import com.doggiehome.doginfomgmt.pojo.Dog;
import com.doggiehome.doginfomgmt.pojo.DogImg;
import com.doggiehome.doginfomgmt.repository.DogImgRepository;
import com.doggiehome.doginfomgmt.service.DogImgService;
import com.doggiehome.doginfomgmt.util.MinioHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@Service
public class DogImgServiceImpl implements DogImgService {
    @Autowired
    DogImgRepository dogImgRepository;

    @Autowired
    private MinioHelper minioHelper;

//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public int saveDogImg() {
//
//
//
//
//        dogImgMain.setUrl(urlMain);
//        dogImgMain.setId(uploadPicMainName);
//        dogImgRepository.save(dogImgMain);
//
//
//
//
//        return 0;
//    }

//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public int saveDogImg(DogImg dogImgMain, DogImg dogImgSec, DogImg dogImgThird, MultipartFile picMain, MultipartFile picSecond, MultipartFile picThird) {
//
//
//
//        String picMainExtensionName = picMain.getContentType().substring(picMain.getContentType().lastIndexOf("/")+1);
//        String uploadPicMainName = UUID.randomUUID().toString()+"."+picMainExtensionName;
//
//        String picSecExtensionName = picSecond.getContentType().substring(picSecond.getContentType().lastIndexOf("/")+1);
//        String uploadPicSecName = UUID.randomUUID().toString()+"."+picSecExtensionName;
//
//        String picThirdExtensionName = picThird.getContentType().substring(picThird.getContentType().lastIndexOf("/")+1);
//        String uploadPicThirdName = UUID.randomUUID().toString()+"."+picThirdExtensionName;
//
//        String urlMain = null;
//        try {
//            urlMain = minioHelper.upload(picMain, uploadPicMainName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        }
//        dogImgMain.setUrl(urlMain);
//        dogImgMain.setId(uploadPicMainName);
//        dogImgRepository.save(dogImgMain);
//
//        String urlSec = null;
//        try {
//            urlSec = minioHelper.upload(picSecond, uploadPicSecName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        }
//        dogImgSec.setUrl(urlSec);
//        dogImgSec.setId(uploadPicSecName);
//        dogImgRepository.save(dogImgSec);
//
//        String urlThird = null;
//        try {
//            urlThird = minioHelper.upload(picThird, uploadPicThirdName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        }
//        dogImgThird.setUrl(urlThird);
//        dogImgThird.setId(uploadPicThirdName);
//        dogImgRepository.save(dogImgThird);
//
//        return 0;
//    }
//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public int saveDogImg(DogImg dogImgMain, DogImg dogImgSec, DogImg dogImgThird, MultipartFile picMain, MultipartFile picSecond, MultipartFile picThird) {
//
//
//
//        String picMainExtensionName = picMain.getContentType().substring(picMain.getContentType().lastIndexOf("/")+1);
//        String uploadPicMainName = UUID.randomUUID().toString()+"."+picMainExtensionName;
//
//        String picSecExtensionName = picSecond.getContentType().substring(picSecond.getContentType().lastIndexOf("/")+1);
//        String uploadPicSecName = UUID.randomUUID().toString()+"."+picSecExtensionName;
//
//        String picThirdExtensionName = picThird.getContentType().substring(picThird.getContentType().lastIndexOf("/")+1);
//        String uploadPicThirdName = UUID.randomUUID().toString()+"."+picThirdExtensionName;
//
//        String urlMain = null;
//        try {
//            urlMain = minioHelper.upload(picMain, uploadPicMainName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        }
//        dogImgMain.setUrl(urlMain);
//        dogImgMain.setId(uploadPicMainName);
//        dogImgRepository.save(dogImgMain);
//
//        String urlSec = null;
//        try {
//            urlSec = minioHelper.upload(picSecond, uploadPicSecName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        }
//        dogImgSec.setUrl(urlSec);
//        dogImgSec.setId(uploadPicSecName);
//        dogImgRepository.save(dogImgSec);
//
//        String urlThird = null;
//        try {
//            urlThird = minioHelper.upload(picThird, uploadPicThirdName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        }
//        dogImgThird.setUrl(urlThird);
//        dogImgThird.setId(uploadPicThirdName);
//        dogImgRepository.save(dogImgThird);
//
//        return 0;
//    }

    @Override
    public List<DogImg> getAllImg(int dogId) {
        List<DogImg> dogImgs = dogImgRepository.findAllByDogId(dogId);
        return dogImgs;
    }


//    public Dog findDogImg(String dogId){
//        return dogImgRepository.
//    }
}
