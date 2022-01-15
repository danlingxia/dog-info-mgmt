package com.doggiehome.doginfomgmt.controller;

import com.doggiehome.doginfomgmt.pojo.Dog;
import com.doggiehome.doginfomgmt.service.DogService;
import com.doggiehome.doginfomgmt.util.MinioHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Api(value = "狗狗信息管理相关", tags = {"狗狗信息管理相关的api接口"})
@RequestMapping("dog")
@RestController
public class DogController {
    @Autowired
    private DogService dogService;

    @Autowired
    private MinioHelper minioHelper;

    /**
     * 增加一条狗狗信息
     */

    @ApiOperation(value = "增加一条狗狗数据", notes = "增加一条狗狗数据", httpMethod = "POST")
    @PostMapping("/saveOneDog")
    public void saveOneDog(
            @ApiParam(value = "狗狗编号", required = true) @RequestParam String id,
            @ApiParam(value = "小院编号", required = true) @RequestParam String home,
            @ApiParam(value = "笼子编号", required = true) @RequestParam Integer container,
            @ApiParam(value = "狗狗名字") @RequestParam String name,
            @ApiParam(value = "狗狗生日",example = "yyyy-MM-dd",format = "date") @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday,
            @ApiParam(value = "狗狗性别（0：女，1：男，2：未知）", allowableValues="0, 1, 2") @RequestParam Integer sex,
            @ApiParam(value = "狗狗体型（1：超小型犬，2：小型犬，3：中型犬，4：大型犬）", allowableValues="1, 2, 3, 4") @RequestParam Integer size,
            @ApiParam(value = "狗狗毛发特点（0：无毛，1：短毛，2：长毛）", allowableValues="0, 1, 2") @RequestParam Integer hair,
            @ApiParam(value = "狗狗性格（1：胆小谨慎，2：安静乖巧，3：活泼开朗）", allowableValues="1, 2, 3") @RequestParam Integer personality,
            @ApiParam(value = "狗狗是绝育情况（0：未绝育，1：已绝育）", allowableValues="0, 1") @RequestParam Integer sterilization,
            @ApiParam(value = "狗狗是否纯色（1：纯色，2：杂色）", allowableValues="1, 2") @RequestParam Integer pureColor,
            @ApiParam(value = "狗狗是否浅色（1：浅色，2：深色）", allowableValues="1, 2") @RequestParam Integer lightColor,
            @ApiParam(value = "狗狗是否已被领养（1:待领养 2:已领养）", allowableValues="1, 2") @RequestParam Integer adoptedStatus,

            @ApiParam(value = "狗狗主图", required = true) @RequestPart("picMain") MultipartFile picMain,
            @ApiParam(value = "狗狗图二", required = true) @RequestPart("picSecond") MultipartFile picSecond,
            @ApiParam(value = "狗狗图三", required = true) @RequestPart("picThird") MultipartFile picThird
//            @RequestBody @Valid DogBo dogBo,
//            BindingResult result
    ){
        Dog dog = new Dog();
        dog.setId(id);
        dog.setHome(home);
        dog.setContainer(container);
        dog.setName(name);
        dog.setBirthday(birthday);
        dog.setSex(sex);
        dog.setSize(size);
        dog.setHair(hair);
        dog.setPersonality(personality);
        dog.setSterilization(sterilization);
        dog.setPureColor(pureColor);
        dog.setLightColor(lightColor);
        dog.setAdoptedStatus(adoptedStatus);
        dog.setCreatedTime(new Date());
        dog.setUpdatedTime(new Date());


        dogService.saveOneDog(dog);
//        minioHelper.upload(picMain, );
    }

    /**
     * 查找所有狗狗信息
     */

    // TODO: 2022/1/9
    /**
     * 删除一条狗狗信息
     */

    /**
     * 修改一条狗狗信息
     */

    /**
     * 查找一条狗狗信息
     */
//    @ApiOperation(value = "查找一条狗狗数据", notes = "查找一条狗狗数据", httpMethod = "GET")
//    @GetMapping("/searchOneDog")




}
