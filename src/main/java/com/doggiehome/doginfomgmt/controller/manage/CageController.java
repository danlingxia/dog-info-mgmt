package com.doggiehome.doginfomgmt.controller.manage;


import com.doggiehome.doginfomgmt.common.ResponseCode;
import com.doggiehome.doginfomgmt.common.ServerResponse;
import com.doggiehome.doginfomgmt.pojo.Dog;
import com.doggiehome.doginfomgmt.service.CageService;
import com.doggiehome.doginfomgmt.service.DogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "笼子管理相关", tags = {"笼子管理相关的api接口"})
@RequestMapping("cage")
@RestController
public class CageController {
    @Autowired
    CageService cageService;

    @Autowired
    DogService dogService;

    @ApiOperation(value = "新建笼子", notes = "新建笼子", httpMethod = "POST")
    @PostMapping("/newCage")
    public ServerResponse newCage(
            @ApiParam(value = "小院id", allowableValues="1", required = true) @RequestParam int yardId,
            @ApiParam(value = "笼子名字", required = true) @RequestParam String cageName){
        if (cageName.equals("")){
            return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "填写不能为空");
        }
        return cageService.newCage(yardId, cageName);
    }

    @ApiOperation(value = "查看小院里笼子信息", notes = "查看小院里笼子信息", httpMethod = "GET")
    @GetMapping("/cages")
    public ServerResponse cages(
            @ApiParam(value = "小院id", required = true) @RequestParam int yardId,
            @ApiParam(value = "笼子id") @RequestParam(required = false) Integer cageId,
            @ApiParam(value = "笼子名称") @RequestParam(required = false) String cageName,
            @ApiParam(value = "页数", required = true) @RequestParam int pageNumber,
            @ApiParam(value = "每页大小", required = true) @RequestParam int pageSize){

        if (pageNumber < 1){
            return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "起始页页数不能小于1");
        }
        pageNumber = pageNumber - 1;

        return cageService.cages(yardId, cageId, cageName, pageNumber, pageSize);
    }

    @ApiOperation(value = "查看小院里所有笼子id和name", notes = "查看小院里所有笼子id和name", httpMethod = "GET")
    @GetMapping("/cageNames")
    public ServerResponse cageNames(
            @ApiParam(value = "小院id", required = true) @RequestParam int yardId){

//        if (pageNumber < 1){
//            return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "起始页页数不能小于1");
//        }
//        pageNumber = pageNumber - 1;

        return cageService.cageNames(yardId);
    }

//    public ServerResponse findCageByName(){
//
//    }



    /**
     * 删除笼子（需要检查笼子中是否有狗狗，只有空笼子才能被删除）
     */

    @ApiOperation(value = "删除笼子", notes = "删除笼子", httpMethod = "DELETE")
    @DeleteMapping("/deleteCage")
    public ServerResponse deleteCage(
            @ApiParam(value = "笼子id", required = true) @RequestParam int cageId){
//        if ((Boolean) cageService.findCage(cageId).getData() == false){
//            return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "该笼子不存在");
//        }
//        List<Dog> dogs = dogService.getDogByCageId(cageId).getData();

        return cageService.deleteCage(cageId);
    }



}
