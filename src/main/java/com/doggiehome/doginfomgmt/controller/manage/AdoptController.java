package com.doggiehome.doginfomgmt.controller.manage;

import com.doggiehome.doginfomgmt.common.ServerResponse;
import com.doggiehome.doginfomgmt.pojo.Adopter;
import com.doggiehome.doginfomgmt.service.AdopterService;
import com.doggiehome.doginfomgmt.service.DogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "领养管理相关", tags = {"领养信息管理相关的api接口"})
@RequestMapping("adopt-manage")
@RestController
public class AdoptController {

    @Autowired
    private DogService dogService;

    @Autowired
    private AdopterService adopterService;

    @ApiOperation(value = "增加一条领养人的信息", notes = "增加一条领养人的信息", httpMethod = "POST")
    @PostMapping("/addAdopter")
    public ServerResponse addAdopter(@RequestBody @Valid Adopter adopter, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.errorResponse(result);
        }
        dogService.addAdopter(adopter);
        return ServerResponse.successResponse(200,"添加成功");
    }

    @ApiOperation(value = "修改一条领养人的信息", notes = "修改一条领养人的信息", httpMethod = "POST")
    @PostMapping("/updateAdopter")
    public ServerResponse updateAdopter(@RequestBody @Valid Adopter adopter, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.errorResponse(result);
        }
        adopterService.updateAdopter(adopter);
        return ServerResponse.successResponse(200,"添加成功");
    }

    @ApiOperation(value = "【已领养】界面，上方 [查询] 中直接搜索“领养人姓名/宠物姓名", notes = "【中转中】界面，搜索“中转人姓名/宠物姓名/中转地址", httpMethod = "POST")
    @PostMapping("/adopterSearch")
    public ServerResponse adopterSearch(@RequestParam(required = false) String search) {
        ServerResponse adopter = adopterService.adopterSearch(search);
        return ServerResponse.successResponse(adopter);
    }

    @ApiOperation(value = " 从【已领养】列表中删除信息", notes = "从【已领养】列表中删除信息", httpMethod = "POST")
    @PostMapping("/deleteAdopter")
    public ServerResponse deleteAdopter(@RequestParam Integer dogId) {
        adopterService.deleteAdopter(dogId);
        return ServerResponse.successResponse(200,"删除成功");
    }

    @ApiOperation(value = "从【已领养】列表转回笼中", notes = "从【已领养】列表转回笼中", httpMethod = "POST")
    @GetMapping("/backCage")
    public ServerResponse backCage(@RequestParam Integer dogId) {
        adopterService.backCage(dogId);
        return ServerResponse.successResponse(200,"删除成功");
    }
}
