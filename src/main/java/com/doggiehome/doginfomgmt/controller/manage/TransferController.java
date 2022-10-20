package com.doggiehome.doginfomgmt.controller.manage;

import com.doggiehome.doginfomgmt.common.ResponseCode;
import com.doggiehome.doginfomgmt.common.ServerResponse;
import com.doggiehome.doginfomgmt.pojo.Transfer;
import com.doggiehome.doginfomgmt.service.DogService;
import com.doggiehome.doginfomgmt.service.TransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "中转管理相关", tags = {"中转信息管理相关的api接口"})
@RequestMapping("transfer-manage")
@RestController
public class TransferController {

    @Autowired
    private TransferService transferService;
    @Autowired
    private DogService dogService;

    @ApiOperation(value = "【中转中】列表转回笼中", notes = "【中转中】列表转回笼中", httpMethod = "POST")
    @PostMapping("/updateCageByDogId")
    public ServerResponse updateCageByDogId(@RequestParam Integer cageId, @RequestParam Integer dogId) {
        return dogService.updateCageByDogId(cageId,dogId);
    }

    @ApiOperation(value = "【在笼中】转入【中转站】", notes = "【在笼中】转入【中转站】", httpMethod = "POST")
    @PostMapping("/addTransfer")
    public ServerResponse addTransfer(@RequestBody @Valid Transfer transfer, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.errorResponse(result);
        }
        return transferService.addTransfer(transfer);
    }

    // 模糊搜索
    @ApiOperation(value = "【中转中】界面，搜索“中转人姓名/宠物姓名/中转地址", notes = "【中转中】界面，搜索“中转人姓名/宠物姓名/中转地址", httpMethod = "POST")
    @PostMapping("/transferSearch")
    public ServerResponse transferSearch(@RequestParam(required = false) String search,
                                         @ApiParam(value = "页数", required = true) @RequestParam Integer pageNumber,
                                         @ApiParam(value = "每页大小", required = true) @RequestParam Integer pageSize) {
        if (pageNumber < 1) {
            return ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "起始页页数不能小于1");
        }
        pageNumber = pageNumber - 1;
        return transferService.transferSearch(search,pageNumber, pageSize);
    }

    @ApiOperation(value = "点击【中转中】列表中的 [编辑] 按钮，进入到编辑详情页", notes = "点击【中转中】列表中的 [编辑] 按钮，进入到编辑详情页", httpMethod = "POST")
    @PostMapping("/transferSearchById")
    public ServerResponse transferSearchById(@RequestParam Integer dogId) {
        return transferService.transferSearchById(dogId);
    }
}
