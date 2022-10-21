package com.doggiehome.doginfomgmt.controller.manage;

import com.doggiehome.doginfomgmt.common.Const;
import com.doggiehome.doginfomgmt.common.ResponseCode;
import com.doggiehome.doginfomgmt.common.ServerResponse;
import com.doggiehome.doginfomgmt.pojo.Dog;
import com.doggiehome.doginfomgmt.pojo.bo.DogBo;
import com.doggiehome.doginfomgmt.pojo.bo.DogModifyBo;
import com.doggiehome.doginfomgmt.service.CageService;
import com.doggiehome.doginfomgmt.service.DogImgService;
import com.doggiehome.doginfomgmt.service.DogService;
import com.doggiehome.doginfomgmt.util.MinioHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Api(value = "狗狗信息管理相关", tags = {"狗狗信息管理相关的api接口"})
@RequestMapping("dog-manage")
@RestController
public class DogManageController {
    @Autowired
    private DogService dogService;

    @Autowired
    private DogImgService dogImgService;

    @Autowired
    private CageService cageService;

    @Autowired
    private MinioHelper minioHelper;

    private Map<Long, List<Integer>> randomList = new HashMap<>();

    //TODO 客户页面需要

    /**
     * 根据条件查找所有符合条件的狗狗信息，狗狗特征，已领养等
     */

    //TODO 管理页面需要
    /**
     * 根据条件查找所有符合条件的狗狗信息，已领养等
     */

//    //TODO 增加检查笼子是否存在的逻辑

    /**
     * 增加一条狗狗信息
     */
    //TODO 增加检查笼子是否存在的逻辑

    @ApiOperation(value = "增加一条狗狗数据", notes = "增加一条狗狗数据", httpMethod = "POST")
    @PostMapping("/newDog")
    public ServerResponse newDog(@RequestBody @Valid DogBo dogBo, BindingResult result){
//        Date d = new Date();
//        System.out.println(dogBo);
        if (result.hasErrors()) {
//            Map<String, String> errorMap = getErrors(result);
//            return ServerResponse.errorResponse(errorMap);
            return ServerResponse.errorResponse(result);
        }
        if (dogBo.getPictures().size() == 0){
            return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "请传入图片");
        }
        //加入生日时间判断
        ZonedDateTime zny = ZonedDateTime.now(ZoneId.of("UTC+8"));
        LocalDateTime ldtNow = zny.toLocalDateTime();
        if (dogBo.getBirthday().isAfter(ldtNow)){
            return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "生日不能在未来");
        }
        if ((Boolean) cageService.findCage(dogBo.getCageId()).getData() == false){
            return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "该笼子不存在");
        }

        Dog dogEntity = null;
        try {
            dogEntity = (Dog) dogService.newDog(dogBo).getData();
        } catch (IOException e) {
            return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "image exception");
        }

        return dogService.findOneDog(dogEntity.getId());
    }



    @ApiOperation(value = "删除狗狗信息", notes = "删除狗狗信息", httpMethod = "DELETE")
    @DeleteMapping("/deleteOneDog")
    public ServerResponse deleteOneDog(@ApiParam(value = "狗狗id", required = true) @RequestParam int dogId) {
        ServerResponse serverResponse = dogService.deleteADog(dogId);
        return serverResponse;
    }


    @ApiOperation(value = "修改狗狗信息", notes = "修改狗狗信息", httpMethod = "PUT")
    @PutMapping("/modifyOneDog")
    public ServerResponse modifyOneDog(@RequestBody @Valid DogModifyBo dogModifyBo, BindingResult result) throws IOException {

        if (result.hasErrors()) {
//            Map<String, String> errorMap = getErrors(result);
//            return ServerResponse.errorResponse(errorMap);
            return ServerResponse.errorResponse(result);
        }
        if (dogModifyBo.getPictures().size() == 0){
            return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "请传入图片");
        }
        if ((Boolean) cageService.findCage(dogModifyBo.getCageId()).getData() == false){
            return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "该笼子不存在");
        }
        ServerResponse serverResponse = dogService.modifyADog(dogModifyBo);

//        String s = "http://104.243.27.33:9000/dog.picd86e56e8-941f-427e-8ebf-2c03ec1c29ca.jpeg";
//        MultipartFile multipartFile = new M
        return serverResponse;
    }

    /**
     * 管理员根据狗狗id查找某条狗狗信息
     */
    @ApiOperation(value = "根据狗狗id查找一条狗狗数据", notes = "根据狗狗id查找一条狗狗数据", httpMethod = "GET")
    @GetMapping("/searchOneDog")
    public ServerResponse searchOneDog(@ApiParam(value = "狗狗id", required = true) @RequestParam int id){
        return dogService.findOneDog(id);

    }


//    private Map<String, String> getErrors(BindingResult result) {
//        Map<String, String> map = new HashMap<>();
//        List<FieldError> errorList = result.getFieldErrors();
//        for (FieldError error : errorList) {
//            // 发生验证错误所对应的某一个属性
//            String errorField = error.getField();
//            // 验证错误的信息
//            String errorMsg = error.getDefaultMessage();
//
//            map.put(errorField, errorMsg);
//        }
//        return map;
//    }


    /**
     * 上传图片
     */
//    @RequestPart("pictures")
//    @RequestParam("multipartFiles")
    @ApiOperation(value = "上传图片", notes = "上传图片", httpMethod = "POST")
    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ServerResponse upload(@ApiParam(value = "图片", required = true) @RequestPart("pictures") List<MultipartFile> pictures) {
        if (null == pictures) {
            return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "请添加照片");
        }
        if (pictures.size() > Const.UPLOAD_MAX_NUM){
            return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "照片不能超过" + Const.UPLOAD_MAX_NUM + "张");
        }
        int num = pictures.size();
        List<String> urlList = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            MultipartFile picture = pictures.get(i);
            String pictureExtensionName = picture.getContentType().substring(picture.getContentType().lastIndexOf("/") + 1);
            String uploadPictureName = UUID.randomUUID() + "." + pictureExtensionName;
            try {
                String url = minioHelper.upload(picture, uploadPictureName);
                urlList.add(url);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        }
        return ServerResponse.successResponse(urlList);
//        return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "请重试");
    }

//        pictures.parallelStream().forEachOrdered(picture ->{
//            String picThirdExtensionName = picture.getContentType().substring(picture.getContentType().lastIndexOf("/")+1);
//            String uploadPicThirdName = UUID.randomUUID().toString()+"."+picThirdExtensionName;
////            urlMain = minioHelper.upload(picture, uploadPicThirdName);
//            return picture.getContentType().substring(picture.getContentType().lastIndexOf("/")+1));
//        });

//        MultipartFile picMain;
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
//        return ;
//    }


    // TODO: 2022/1/9
    /**
     * 删除一条狗狗信息，逻辑删除（领养/死亡/其他-需备注）
     */
//    @ApiOperation(value = "删除一条狗狗数据", notes = "删除一条狗狗数据", httpMethod = "POST")
//    @PostMapping("/delOneDog")
//    public void delOneDog(@ApiParam(value = "狗狗编号", required = true) @RequestParam String id){
//        return ;
//    }

    /**
     * 修改一条狗狗信息
     */










    /**
     * 领养，修改狗狗状态
     */
    /**
     * 查找狗狗图片
     */


//用不上，作废
//    /**
//     * 查询某小院某笼子里的狗狗
//     * @param yardId
//     * @param cageId
//     * @return
//     */
//    @ApiOperation(value = "根据小院id和笼子id查询笼子里的狗狗", notes = "根据小院id和笼子id查询笼子里的狗狗", httpMethod = "GET")
//    @GetMapping("/find-dog-by-yard-and-cage")
//    public ServerResponse cageInfo( @ApiParam(value = "小院id", required = true) @RequestParam int yardId,
//                                    @ApiParam(value = "笼子id", required = true) @RequestParam int cageId,
//                                    @ApiParam(value = "页数", required = true) @RequestParam int pageNumber,
//                                    @ApiParam(value = "每页大小", required = true) @RequestParam int pageSize
//    ){
//
//        if (pageNumber < 1){
//            return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "起始页页数不能小于1");
//        }
//        pageNumber = pageNumber - 1;
//        return dogService.findDogsByCageAndYard(yardId, cageId,  pageNumber,  pageSize);
//
//    }


    @ApiOperation(value = "根据小院id和其他查询条件查询笼子里的狗狗", notes = "根据小院id和其他查询条件查询笼子里的狗狗", httpMethod = "GET")
    @GetMapping("/find-dogs")
    public ServerResponse findDogs(@ApiParam(value = "小院id", required = true) @RequestParam int yardId,
                                   @ApiParam(value = "笼子id") @RequestParam(required = false) Integer cageId,
                                   @ApiParam(value = "笼子名称") @RequestParam(required = false) String cageName,
                                   @ApiParam(value = "编号") @RequestParam(required = false) String identifier,
                                   @ApiParam(value = "狗狗名称") @RequestParam(required = false) String dogName,
                                   @ApiParam(value = "页数", required = true) @RequestParam int pageNumber,
                                   @ApiParam(value = "每页大小", required = true) @RequestParam int pageSize)
    {
        if (pageNumber < 1){
            return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "起始页页数不能小于1");
        }
        pageNumber = pageNumber - 1;

        return dogService.findDogByTerm(yardId, cageId, cageName, identifier, dogName, pageNumber, pageSize);
    }




}
