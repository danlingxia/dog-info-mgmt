package com.doggiehome.doginfomgmt.controller.manage;

import com.doggiehome.doginfomgmt.common.ServerResponse;
import com.doggiehome.doginfomgmt.pojo.Transfer;
import com.doggiehome.doginfomgmt.service.DogService;
import com.doggiehome.doginfomgmt.service.TransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
        dogService.updateCageByDogId(cageId,dogId);
        return ServerResponse.successResponse(200,"更新成功");
    }

    @ApiOperation(value = "增加一条中转的信息", notes = "增加一条中转的信息", httpMethod = "POST")
    @PostMapping("/addTransfer")
    public ServerResponse addTransfer(@RequestBody @Valid Transfer transfer, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.errorResponse(result);
        }
        transferService.addTransfer(transfer);
        return ServerResponse.successResponse(200,"添加成功");
    }

    @ApiOperation(value = "【中转中】界面，搜索“中转人姓名/宠物姓名/中转地址", notes = "【中转中】界面，搜索“中转人姓名/宠物姓名/中转地址", httpMethod = "POST")
    @PostMapping("/transferSearch")
    public ServerResponse transferSearch(@RequestParam String search) {
        Transfer transfer = transferService.transferSearch(search);
        return ServerResponse.successResponse(transfer);
    }
}
