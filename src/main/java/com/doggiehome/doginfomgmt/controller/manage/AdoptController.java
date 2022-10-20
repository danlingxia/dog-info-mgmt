package com.doggiehome.doginfomgmt.controller.manage;

import com.doggiehome.doginfomgmt.common.ResponseCode;
import com.doggiehome.doginfomgmt.common.ServerResponse;
import com.doggiehome.doginfomgmt.pojo.Adopter;
import com.doggiehome.doginfomgmt.service.AdopterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "领养管理相关", tags = {"领养信息管理相关的api接口"})
@RequestMapping("adopt-manage")
@RestController
public class AdoptController {

    @Autowired
    private AdopterService adopterService;

    @ApiOperation(value = "从【在笼中】转入【已领养】", notes = "从【在笼中】转入【已领养】", httpMethod = "POST")
    @PostMapping("/addAdopter")
    public ServerResponse addAdopter(@RequestBody @Valid Adopter adopter, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.errorResponse(result);
        }
        return adopterService.addAdopter(adopter);
    }

    @ApiOperation(value = "编辑领养信息：点击【已领养列表】中的 [编辑]", notes = "编辑领养信息：点击【已领养列表】中的 [编辑]", httpMethod = "POST")
    @PostMapping("/updateAdopter")
    public ServerResponse updateAdopter(@RequestBody @Valid Adopter adopter, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.errorResponse(result);
        }
        return adopterService.updateAdopter(adopter);
    }

    @ApiOperation(value = "【已领养】界面，上方 [查询] 中直接搜索“领养人姓名/宠物姓名", notes = "【中转中】界面，搜索“中转人姓名/宠物姓名/中转地址", httpMethod = "POST")
    @PostMapping("/adopterSearch")
    public ServerResponse adopterSearch(@RequestParam(required = false) String search,
                                        @ApiParam(value = "页数", required = true) @RequestParam Integer pageNumber,
                                        @ApiParam(value = "每页大小", required = true) @RequestParam Integer pageSize) {
        if (pageNumber < 1) {
            return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "起始页页数不能小于1");
        }
        pageNumber = pageNumber - 1;
        return adopterService.adopterSearch(search,pageNumber,pageSize);
    }

    @ApiOperation(value = " 从【已领养】列表中删除信息", notes = "从【已领养】列表中删除信息", httpMethod = "POST")
    @PostMapping("/deleteAdopter")
    public ServerResponse deleteAdopter(@RequestParam Integer dogId) {
        return adopterService.deleteAdopter(dogId);
    }

    @ApiOperation(value = "从【已领养】列表转回笼中", notes = "从【已领养】列表转回笼中", httpMethod = "POST")
    @PostMapping("/backCage")
    public ServerResponse backCage(@RequestParam Integer dogId,
                                   @RequestParam String description,
                                   @RequestParam Integer cageId) {
        return adopterService.backCage(dogId,description,cageId);
    }
}
