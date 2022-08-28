package com.doggiehome.doginfomgmt.service.impl;

import com.doggiehome.doginfomgmt.common.ResponseCode;
import com.doggiehome.doginfomgmt.common.ServerResponse;
import com.doggiehome.doginfomgmt.pojo.Dog;
import com.doggiehome.doginfomgmt.pojo.DogImg;
import com.doggiehome.doginfomgmt.pojo.bo.DogBo;
import com.doggiehome.doginfomgmt.pojo.bo.DogModifyBo;
import com.doggiehome.doginfomgmt.pojo.vo.DogListVo;
import com.doggiehome.doginfomgmt.pojo.vo.DogManageListVo;
import com.doggiehome.doginfomgmt.pojo.vo.DogVo;
import com.doggiehome.doginfomgmt.repository.DogImgRepository;
import com.doggiehome.doginfomgmt.repository.DogRepository;
import com.doggiehome.doginfomgmt.repository.DogRepositoryCustom;
import com.doggiehome.doginfomgmt.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DogServiceImpl implements DogService {
    @Autowired
    DogRepository dogRepository;

    @Autowired
    DogRepositoryCustom dogRepositoryCustom;

    @Autowired
    DogImgRepository dogImgRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ServerResponse deleteADog(int dogId) {
        int numDog = dogRepository.deleteAllById(dogId);
        int numImg = dogImgRepository.deleteAllByDogId(dogId);

        if (numDog == 0){
            return ServerResponse.errorResponse(ResponseCode.DOG_NO_EXIST.getCode(), ResponseCode.DOG_NO_EXIST.getDesc());

        }else{
            return ServerResponse.successResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc());
        }
    }

//    @Override
//    public ServerResponse getDogByCageId(int cageId) {
//        List<Dog> dogs = dogRepository.findAllByCageId(cageId);
////        if (dogs.size() > 0){
////            return ServerResponse.errorResponse(ResponseCode.CAGE_NO_EMPTY.getCode(), ResponseCode.CAGE_NO_EMPTY.getDesc());
////        }
//        return ServerResponse.successResponse(dogs);
//    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ServerResponse newDog(DogBo dogBo) {
        Dog dog = new Dog();
        dog.setYardId(dogBo.getYardId());
        dog.setCageId(dogBo.getCageId());
        dog.setIdentifier(String.valueOf(UUID.randomUUID()));
        dog.setName(dogBo.getName());
        dog.setBirthday(dogBo.getBirthday());
        dog.setSex(dogBo.getSex());
        dog.setSize(dogBo.getSize());
        dog.setHairLength(dogBo.getHairLength());
        dog.setHairFeature(dogBo.getHairFeature());
        dog.setPersonality(dogBo.getPersonality());
        dog.setSterilization(dogBo.getSterilization());
        dog.setOrigin(dogBo.getOrigin());
        dog.setBackground(dogBo.getBackground());
        dog.setAdoptedStatus(1);

        List<String> pictures  = dogBo.getPictures();
        Dog dogEntity = dogRepository.save(dog);

        int num = pictures.size();
        for (int i = 0; i < num; i++){
            DogImg dogImg = new DogImg();
            String urlString = pictures.get(i);
            URL url = null;
            BufferedImage image = null;
            try {
                url = new URL(urlString);
                image = ImageIO.read(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            int width = image.getWidth();
            int height = image.getHeight();
            dogImg.setDogId(dogEntity.getId());
            dogImg.setUrl(urlString);
            dogImg.setWidth(width);
            dogImg.setHeight(height);

            if (i == 0){
                dogImg.setIsMain(1);
            }else {
                dogImg.setIsMain(0);
            }
            dogImg.setSort(i+1);
            dogImgRepository.save(dogImg);
        }


//        dogImgService.saveDogImg();

        return ServerResponse.successResponse(dogEntity);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ServerResponse modifyADog(DogModifyBo dogModifyBo) throws IOException {
        Dog dogToUpdate = dogRepository.getById(dogModifyBo.getId());
        if (null == dogToUpdate){
            return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "该id不存在，不能更新");
        }

        dogToUpdate.setYardId(dogModifyBo.getYardId());
        dogToUpdate.setCageId(dogModifyBo.getCageId());

        dogToUpdate.setIdentifier(dogToUpdate.getIdentifier());

        dogToUpdate.setName(dogModifyBo.getName());
        dogToUpdate.setBirthday(dogModifyBo.getBirthday());
        dogToUpdate.setSex(dogModifyBo.getSex());
        dogToUpdate.setSize(dogModifyBo.getSize());
        dogToUpdate.setHairLength(dogModifyBo.getHairLength());
        dogToUpdate.setHairFeature(dogModifyBo.getHairFeature());
        dogToUpdate.setPersonality(dogModifyBo.getPersonality());
        dogToUpdate.setSterilization(dogModifyBo.getSterilization());
        dogToUpdate.setOrigin(dogModifyBo.getOrigin());
        dogToUpdate.setBackground(dogModifyBo.getBackground());
        dogToUpdate.setAdoptedStatus(dogModifyBo.getAdoptedStatus());
        Dog dogNew = dogRepository.save(dogToUpdate);

        List<DogImg> dogImgs = dogImgRepository.findAllByDogId(dogToUpdate.getId());

        List<String> urlsOld = dogImgs.stream().map(DogImg::getUrl).collect(Collectors.toList());

//        Set<String> urlsOld = dogImgs.stream().map(DogImg::getUrl).collect(Collectors.toSet());

        List<String> pictures  = dogModifyBo.getPictures();
        List<String> urlsNew = pictures.stream().collect(Collectors.toList());

//        Set<String> urlsNew = pictures.stream().collect(Collectors.toSet());
        //两次url都相同，跳过修改dog_img
//        if (urlsOld.containsAll(urlsNew) && urlsNew.containsAll(urlsOld)){
        if (urlsOld.equals(urlsNew)){


            //两次url都相同，跳过修改dog_img
            DogVo dogVo = new DogVo();
            dogVo.setDog(dogNew);
            dogVo.setDogImgList(dogImgs);

            return ServerResponse.successResponse(dogVo);
        }else {

            //删除旧的
            dogImgRepository.deleteAllByDogId(dogToUpdate.getId());

            //增加新的
            int num = pictures.size();
            for (int i = 0; i < num; i++){
                DogImg dogImg = new DogImg();
                String urlString = pictures.get(i);
                URL url = new URL(urlString);
                BufferedImage image = ImageIO.read(url);
                int width = image.getWidth();
                int height = image.getHeight();
                dogImg.setDogId(dogNew.getId());
                dogImg.setUrl(urlString);
                dogImg.setWidth(width);
                dogImg.setHeight(height);

                if (i == 0){
                    dogImg.setIsMain(1);
                }else {
                    dogImg.setIsMain(0);
                }
                dogImg.setSort(i+1);
                dogImgRepository.save(dogImg);
            }
            List<DogImg> dogImgsNew = dogImgRepository.findAllByDogId(dogToUpdate.getId());
            DogVo dogVo = new DogVo();
            dogVo.setDog(dogNew);
            dogVo.setDogImgList(dogImgsNew);

            return ServerResponse.successResponse(dogVo);
        }

//        DogVo dogVo = new DogVo();
//        dogVo.setDog(dogToUpdate);
//        dogVo.setDogImgList(dogImgsNew);
//
//        return ServerResponse.successResponse(dogVo);
    }

    public ServerResponse findOneDog(int dogId){
        Dog dog = dogRepository.findDogById(dogId);
//        List<DogImg> dogImgs = dogImgService.getAllImg(dog.getId());
        List<DogImg> dogImgs = dogImgRepository.findAllByDogId(dogId);
//        return dogImgs;

        DogVo dogVo = new DogVo();
        dogVo.setDog(dog);
        dogVo.setDogImgList(dogImgs);
        return ServerResponse.successResponse(dogVo);
    }

//    public ServerResponse findDogs(int sex, List<Integer> range, int size, int hairLength, int pageNum, int pageSize){
//        List<Dog> dogs = dogRepositoryCustom.findDogs(sex, range, size, hairLength, pageNum, pageSize);
//
//        return ServerResponse.successResponse(dogs);
//    }

    public ServerResponse findDogIds(Integer sex, List<Integer> ageRange, Integer size, Integer hairLength){



        List<Dog> dogIds = dogRepositoryCustom.findDogIds(sex, ageRange, size, hairLength);

        return ServerResponse.successResponse(dogIds);
    }

    public ServerResponse findDogList(List<Integer> dogIds){
        List<Object[]> dogList = dogRepository.getDogList(dogIds);;
        Iterator iterator = dogList.iterator();
        List<DogListVo> dogListVos = new ArrayList<>();
        while (iterator.hasNext()){
            DogListVo dogListVo = new DogListVo();
            Object[] o = (Object[]) iterator.next();
            dogListVo.setId((Integer) o[0]);
            dogListVo.setName((String) o[1]);
            dogListVo.setBirthday((Date) o[2]);
            dogListVo.setSex((int) o[3]);
            dogListVo.setSize((int) o[4]);
            dogListVo.setHairLength((int) o[5]);
            dogListVo.setUrl((String) o[6]);
            dogListVo.setWidth((int) o[7]);
            dogListVo.setHeight((int) o[8]);
            dogListVos.add(dogListVo);
        }
        return ServerResponse.successResponse(dogListVos);


    }


    @Override
    public ServerResponse findDogsByCageAndYard(int yardId, int cageId, int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC,"id"));
        Page<Object[]> dogPage = dogRepository.getDogManageList(yardId, cageId, pageable);;
        List<Object[]> dogList = dogPage.getContent();
        Iterator iterator = dogList.iterator();

        List<DogManageListVo> dogs = new ArrayList<>();
        while (iterator.hasNext()){
            DogManageListVo dogManageListVo = new DogManageListVo();
            Object[] o = (Object[]) iterator.next();
            dogManageListVo.setId((Integer) o[0]);
            dogManageListVo.setYardId((int) o[1]);
            dogManageListVo.setCageId((int) o[2]);
            dogManageListVo.setIdentifier((String) o[3]);

            dogManageListVo.setName((String) o[4]);
            dogManageListVo.setBirthday((Date) o[5]);
            dogManageListVo.setSex((int) o[6]);
            dogManageListVo.setSize((int) o[7]);
            dogManageListVo.setSterilization((int) o[8]);
            dogManageListVo.setUrl((String) o[9]);
            dogs.add(dogManageListVo);
        }
        Page<DogManageListVo> dogPageResult = new PageImpl<>(dogs, dogPage.getPageable(), dogPage.getTotalElements());
        return ServerResponse.successResponse(dogPageResult);
    }

    @Override
    public ServerResponse findDogByTerm(int yardId, Integer cageId, String cageName, String identifier,
                                        String dogName, int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC,"id"));

        Page<Object[]> dogPage = dogRepository.getDogManageByTerm(yardId, cageId, cageName, identifier,
                dogName, pageable);;

        List<Object[]> dogList = dogPage.getContent();
//        List<Object[]> dogList = dogRepository.getDogManageList(yardId, cageId);;
        Iterator iterator = dogList.iterator();

        List<DogManageListVo> dogs = new ArrayList<>();
        while (iterator.hasNext()){
            DogManageListVo dogManageListVo = new DogManageListVo();
            Object[] o = (Object[]) iterator.next();
            dogManageListVo.setId((Integer) o[0]);
            dogManageListVo.setYardId((int) o[1]);
            dogManageListVo.setCageId((int) o[2]);
            dogManageListVo.setCageName((String) o[3]);
            dogManageListVo.setIdentifier((String) o[4]);

            dogManageListVo.setName((String) o[5]);
            dogManageListVo.setBirthday((Date) o[6]);
            dogManageListVo.setSex((int) o[7]);
            dogManageListVo.setSize((int) o[8]);
            dogManageListVo.setSterilization((int) o[9]);
            dogManageListVo.setUrl((String) o[10]);

            dogs.add(dogManageListVo);
        }
        Page<DogManageListVo> dogPageResult = new PageImpl<>(dogs, dogPage.getPageable(), dogPage.getTotalElements());
        return ServerResponse.successResponse(dogPageResult);
    }

//    @Override
//    public ServerResponse existADog(int dogId) {
//        return null;
//    }


}
