package com.doggiehome.doginfomgmt.controller.portal;

import com.doggiehome.doginfomgmt.common.Const;
import com.doggiehome.doginfomgmt.common.ResponseCode;
import com.doggiehome.doginfomgmt.common.ServerResponse;
import com.doggiehome.doginfomgmt.service.DogImgService;
import com.doggiehome.doginfomgmt.service.DogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@Api(value = "领养人端狗狗信息展示相关接口", tags = {"领养人端狗狗信息展示相关api接口"})
@RequestMapping("dog")
@RestController
public class DogController {
    @Autowired
    private DogService dogService;

    @Autowired
    private DogImgService dogImgService;
//
//    @Autowired
//    private MinioHelper minioHelper;
//
    private Map<Long, List<Integer>> randomList = new HashMap<>();
//
//    //TODO 客户页面需要
//
//    /**
//     * 根据条件查找所有符合条件的狗狗信息，狗狗特征，已领养等
//     */
//
//    //TODO 管理页面需要
//    /**
//     * 根据条件查找所有符合条件的狗狗信息，已领养等
//     */
//
//
//
////    /**
////     * 增加一条狗狗信息
////     */
////    //TODO 增加检查笼子是否存在的逻辑
////
////    @ApiOperation(value = "增加一条狗狗数据", notes = "增加一条狗狗数据", httpMethod = "POST")
////    @PostMapping("/newDog")
////    public void newDog(
//////            @ApiParam(value = "狗狗编号", required = true) @RequestParam String id,
////            @ApiParam(value = "小院编号", required = true) @RequestParam int yardId,
////            @ApiParam(value = "笼子编号", required = true) @RequestParam int cageId,
////            @ApiParam(value = "狗狗名字") @RequestParam String name,
////            @ApiParam(value = "狗狗生日",example = "yyyy-MM-dd",format = "date") @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday,
////            @ApiParam(value = "狗狗性别（0：未知, 1：公，2：母）", allowableValues="0, 1, 2") @RequestParam int sex,
////            @ApiParam(value = "狗狗体型（1：小型犬，2：中型犬，3：大型犬）", allowableValues="1, 2, 3", required = true) @RequestParam int size,
////            @ApiParam(value = "狗狗毛发长度（1：无毛，2：短毛，3：长毛）", allowableValues="1, 2, 3", required = true) @RequestParam int hairLength,
////            @ApiParam(value = "狗狗毛发特点（1：卷毛，2：直毛）", allowableValues="1, 2", required = true) @RequestParam int hairFeature,
////            @ApiParam(value = "狗狗性格（0:未知, 1：胆小谨慎，2：安静乖巧，3：活泼开朗）", allowableValues="0, 1, 2, 3") @RequestParam int personality,
////            @ApiParam(value = "狗狗是绝育情况（0:未知, 1：未绝育，2：已绝育）", allowableValues="0, 1, 2") @RequestParam int sterilization,
//////            @ApiParam(value = "狗狗是否纯色（1：纯色，2：杂色）", allowableValues="1, 2") @RequestParam Integer pureColor,
//////            @ApiParam(value = "狗狗是否浅色（1：浅色，2：深色）", allowableValues="1, 2") @RequestParam Integer lightColor,
////
////            @ApiParam(value = "狗狗来源（0:未知, 1：城区, 2：农村 ）", allowableValues="0, 1, 2") @RequestParam Integer origin,
////            @ApiParam(value = "狗狗出身（0:未知, 1：被遗弃,  2：跑丢， 3：流浪二代）", allowableValues="0, 1, 2, 3") @RequestParam Integer background,
////
////            @ApiParam(value = "狗狗是否已被领养（1:待领养 2:已领养）", allowableValues="1, 2", required = true) @RequestParam Integer adoptedStatus,
////
////            @ApiParam(value = "狗狗主图", required = true) @RequestPart("mainPicture") MultipartFile mainPicture,
////            @ApiParam(value = "狗狗图二", required = true) @RequestPart("secondPicture") MultipartFile secondPicture,
////            @ApiParam(value = "狗狗图三", required = true) @RequestPart("thirdPicture") MultipartFile thirdPicture
//////            @RequestBody @Valid DogBo dogBo,
//////            BindingResult result
////    ){
////        Dog dog = new Dog();
//////        dog.setId(id);
////        dog.setYardId(yardId);
////        dog.setCageId(cageId);
////        String identifier = UUID.randomUUID().toString();
////        dog.setIdentifier(identifier);
////        dog.setName(name);
////        dog.setBirthday(birthday);
////        dog.setSex(sex);
////        dog.setSize(size);
////        dog.setHairLength(hairLength);
////        dog.setHairFeature(hairFeature);
////
//////        dog.setHair(hair);
////        dog.setPersonality(personality);
////        dog.setSterilization(sterilization);
//////        dog.setPureColor(pureColor);
//////        dog.setLightColor(lightColor);
////        dog.setAdoptedStatus(adoptedStatus);
////        dog.setCreatedTime(new Date());
////        dog.setUpdatedTime(new Date());
////
////
////        DogImg dogImgMain = new DogImg();
//////        dogImgMain.setDogId(id);
////        dogImgMain.setIsMain(1);
////        dogImgMain.setCreatedTime(new Date());
////        dogImgMain.setUpdatedTime(new Date());
////        dogImgMain.setSort(1);
////
////        DogImg dogImgSec = new DogImg();
////        dogImgSec.setDogId(id);
////        dogImgSec.setIsMain(0);
////        dogImgSec.setCreatedTime(new Date());
////        dogImgSec.setUpdatedTime(new Date());
////        dogImgSec.setSort(2);
////
////        DogImg dogImgThird = new DogImg();
////        dogImgThird.setDogId(id);
////        dogImgThird.setIsMain(0);
////        dogImgThird.setCreatedTime(new Date());
////        dogImgThird.setUpdatedTime(new Date());
////        dogImgThird.setSort(3);
////
////        dogService.newDog(dog, dogImgMain, dogImgSec, dogImgThird, picMain, picSecond, picThird);
////
////
////
////
////    }
//
//
//    /**
//     * 增加一条狗狗信息
//     */
//    //TODO 增加检查笼子是否存在的逻辑
//
//    @ApiOperation(value = "增加一条狗狗数据", notes = "增加一条狗狗数据", httpMethod = "POST")
//    @PostMapping("/newDog")
//    public ServerResponse newDog(@RequestBody @Valid DogBo dogBo, BindingResult result){
//        System.out.println(dogBo);
//        if (result.hasErrors()) {
////            Map<String, String> errorMap = getErrors(result);
////            return ServerResponse.errorResponse(errorMap);
//            return ServerResponse.errorResponse(result);
//        }
//        Dog dogEntity = null;
//        try {
//            dogEntity = (Dog) dogService.newDog(dogBo).getData();
//        } catch (IOException e) {
//            return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "image exception");
//        }
//
//        return dogService.findOneDog(dogEntity.getId());
//    }
//
////    private Map<String, String> getErrors(BindingResult result) {
////        Map<String, String> map = new HashMap<>();
////        List<FieldError> errorList = result.getFieldErrors();
////        for (FieldError error : errorList) {
////            // 发生验证错误所对应的某一个属性
////            String errorField = error.getField();
////            // 验证错误的信息
////            String errorMsg = error.getDefaultMessage();
////
////            map.put(errorField, errorMsg);
////        }
////        return map;
////    }
//
//
//    /**
//     * 上传图片
//     */
////    @RequestPart("pictures")
////    @RequestParam("multipartFiles")
//    @ApiOperation(value = "上传图片", notes = "上传图片", httpMethod = "POST")
//    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ServerResponse upload(@ApiParam(value = "图片", required = true) @RequestPart("pictures") List<MultipartFile> pictures) {
//        if (null == pictures) {
//            return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "请添加照片");
//        }
//        if (pictures.size() > Const.UPLOAD_MAX_NUM){
//            return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "照片不能超过" + Const.UPLOAD_MAX_NUM + "张");
//        }
//        int num = pictures.size();
//        List<String> urlList = new ArrayList<>(num);
//        for (int i = 0; i < num; i++) {
//            MultipartFile picture = pictures.get(i);
//            String pictureExtensionName = picture.getContentType().substring(picture.getContentType().lastIndexOf("/") + 1);
//            String uploadPictureName = UUID.randomUUID() + "." + pictureExtensionName;
//            try {
//                String url = minioHelper.upload(picture, uploadPictureName);
//                urlList.add(url);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
//            } catch (InvalidKeyException e) {
//                e.printStackTrace();
//            }
//        }
//        return ServerResponse.successResponse(urlList);
////        return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "请重试");
//    }


    /**
     * 领养端根据狗狗id查找一条狗狗信息
     */
    @ApiOperation(value = "根据狗狗id查找一条狗狗数据", notes = "根据狗狗id查找一条狗狗数据", httpMethod = "GET")
    @GetMapping("/searchOneDog")
    public ServerResponse searchOneDog(@ApiParam(value = "狗狗编号", required = true) @RequestParam int id){
        return dogService.findOneDog(id);
    }


    /**
     * 根据条件查找狗狗信息
     * 性别，年龄，体型，毛发长度
     */

    @ApiOperation(value = "根据条件查找狗狗数据", notes = "根据条件查找狗狗数据", httpMethod = "GET")
    @GetMapping("/searchDogs")
    public ServerResponse searchDogs(@ApiParam(value = "狗狗性别") @RequestParam(required = false) Integer sex,
                                     @ApiParam(value = "狗狗年龄(m)") @RequestParam(required = false) List<Integer> range,
                                     @ApiParam(value = "狗狗体型") @RequestParam(required = false) Integer size,
                                     @ApiParam(value = "狗狗毛发长度") @RequestParam(required = false) Integer hairLength,
                                     @ApiParam(value = "起始") @RequestParam int offset,
                                     @ApiParam(value = "页大小") @RequestParam int limit
//                                     @ApiParam(value = "随机seed") @RequestParam(required = false) Long seed
    ){

//        LocalDateTime localDateTime = LocalDateTime.now();
//        return dogService.findDog(id);
//        if (range)
//        return dogService.findDogs(sex, range, size, hairLength, start, pageSize);
        if (offset < 0) {
            return ServerResponse.errorResponse(ResponseCode.START_INDEX_NEGATIVE.getCode(), ResponseCode.START_INDEX_NEGATIVE.getDesc());
        }
        if (limit <= 0) {
            return ServerResponse.errorResponse(ResponseCode.PAGE_SIZE_NO_POSITIVE.getCode(), ResponseCode.PAGE_SIZE_NO_POSITIVE.getDesc());
        }
        List<Integer> dogIds;

//        nextOffset : 下一页起始index
        int nextOffset = offset + limit;
//        if (null != seed){
//            if(null != randomList.get(seed)){
//                dogIds = randomList.get(seed);
//                List<Integer> subList = dogIds.subList(0 < offset && offset < dogIds.size()? offset:0,
//                        offset < nextOffset && nextOffset < dogIds.size()? nextOffset:Math.min(offset + Const.PAGE_SIZE, dogIds.size()));
//                ServerResponse response_next = dogService.findDogList(subList);
//                ServerResponse response_last = ServerResponse.successResponse(Map.of(
//                        "seed", seed,
//                        "totalNum", dogIds.size(),
//                        "dogList", response_next.getData()
//                ));
//                return response_last;
//            }
//        }
        ServerResponse response = dogService.findDogIds(sex, range, size, hairLength);
//        List<Object[]> dogListVos = dogRepository.getDogList((List<Integer>) response.getData());
        dogIds = (List<Integer>) response.getData();


        List<Integer> subList;
        if (offset > dogIds.size()) {
//            subList 不包含元素
            subList = new ArrayList<>();
        } else {
            subList = dogIds.subList(offset, Math.min(nextOffset, dogIds.size()));
        }



//        List<Integer> subList = dogIds.subList(0 < offset && offset < dogIds.size()? offset:0,
//                        offset < nextOffset && nextOffset < dogIds.size()? nextOffset:Math.min(offset + Const.PAGE_SIZE, dogIds.size()));
//

//        此时 offset >= 0, nextOffset > offset


//            Long seed = System.currentTimeMillis();
//        Long seedNew = System.currentTimeMillis();
//
//        Random rand = new Random(seedNew);
//        Collections.shuffle(dogIds, rand);
//        randomList.put(seedNew, dogIds);
        ServerResponse response_next = dogService.findDogList(subList);
//        ServerResponse response_next = dogService.findDogList();
        ServerResponse last_response = ServerResponse.successResponse(Map.of(
//                "seed", seedNew,
                "totalNum", dogIds.size(),
                "dogList", response_next.getData()
        ));
        return last_response;
    }



//    @ApiOperation(value = "根据条件查找狗狗数据", notes = "根据条件查找狗狗数据", httpMethod = "GET")
//    @GetMapping("/searchDogs")
//    public ServerResponse searchDogs(@ApiParam(value = "狗狗性别") @RequestParam int sex,
//                                     @ApiParam(value = "狗狗年龄(m)") @RequestParam List<Integer> range,
//                                     @ApiParam(value = "狗狗体型") @RequestParam int size,
//                                     @ApiParam(value = "狗狗毛发长度") @RequestParam int hairLength,
//                                     @ApiParam(value = "起始") @RequestParam int offset,
//                                     @ApiParam(value = "页大小") @RequestParam int limit,
//                                     @ApiParam(value = "随机seed") @RequestParam(required = false) Long seed
//    ){
////        return dogService.findDog(id);
////        if (range)
////        return dogService.findDogs(sex, range, size, hairLength, start, pageSize);
//        List<Integer> dogIds;
//        int nextOffset = offset + limit;
//        if (null != seed){
//            if(null != randomList.get(seed)){
//                dogIds = randomList.get(seed);
//                List<Integer> subList = dogIds.subList(0 < offset && offset < dogIds.size()? offset:0,
//                        offset < nextOffset && nextOffset < dogIds.size()? nextOffset:Math.min(offset + Const.PAGE_SIZE, dogIds.size()));
//                ServerResponse response_next = dogService.findDogList(subList);
//                ServerResponse response_last = ServerResponse.successResponse(Map.of(
//                        "seed", seed,
//                        "totalNum", dogIds.size(),
//                        "dogList", response_next.getData()
//                        ));
//                return response_last;
//            }
//        }
//        ServerResponse response = dogService.findDogIds(sex, range, size, hairLength);
////        List<Object[]> dogListVos = dogRepository.getDogList((List<Integer>) response.getData());
//        dogIds = (List<Integer>) response.getData();
////            Long seed = System.currentTimeMillis();
//        Long seedNew = System.currentTimeMillis();
////
//        Random rand = new Random(seedNew);
//        Collections.shuffle(dogIds, rand);
//        randomList.put(seedNew, dogIds);
//        ServerResponse response_next = dogService.findDogList(dogIds);
////        ServerResponse response_next = dogService.findDogList();
//        ServerResponse last_response = ServerResponse.successResponse(Map.of(
//                "seed", seedNew,
//                "totalNum", dogIds.size(),
//                "dogList", response_next.getData()
//        ));
//        return last_response;
//    }
}
