package com.doggiehome.doginfomgmt.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@ApiModel(value = "狗狗对象修改BO", description = "从客户端，由管理员用户传入的修改数据封装在此实体中")
public class DogModifyBo {

    @ApiModelProperty(value = "狗狗id", name = "id", example = "1", required = true)
    private int id;

    @ApiModelProperty(value = "小院id", name = "yardId", example = "1", required = true)
    private int yardId;

    @ApiModelProperty(value = "笼子id", name = "cageId", example = "1", required = true)
    private int cageId;

    @ApiModelProperty(value = "狗狗名字", name = "name", example = "小白")
    private String name;

    @ApiModelProperty(value = "生日", name = "birthday", example = "yyyy-MM-ddThh-MM")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "性别， 0：未知, 1：公，2：母", name = "sex", allowableValues="0, 1, 2")
    private int sex;

    @ApiModelProperty(value = "体型， 1：小型犬，2：中型犬，3：大型犬", name = "size", allowableValues="1, 2, 3", required = true)
    private int size;

    @ApiModelProperty(value = "毛发长度，1：无毛，2：短毛，3：长毛", name = "hairLength", allowableValues="1, 2, 3", required = true)
    private int hairLength;

    @ApiModelProperty(value = "毛发特点，1：卷毛，2：直毛", name = "hairFeature", allowableValues="1, 2", required = true)
    private int hairFeature;

    @ApiModelProperty(value = "性格， 0:未知, 1：胆小谨慎，2：安静乖巧，3：活泼开朗", name = "personality", allowableValues="0, 1, 2, 3")
    private int personality;

    @ApiModelProperty(value = "绝育情况， 0:未知, 1：未绝育，2：已绝育", name = "sterilization", allowableValues="0, 1, 2")
    private int sterilization;

    @ApiModelProperty(value = "来源，0:未知, 1：城区, 2：农村", name = "origin",  allowableValues="0, 1, 2")
    private int origin;

    @ApiModelProperty(value = "出身，0:未知, 1：被遗弃,  2：跑丢， 3：流浪二代", name = "background", allowableValues="0, 1, 2, 3")
    private int background;

    @ApiModelProperty(value = "被领养状态, 1:待领养 2:已领养", name = "adoptedStatus", example = "1", required = true)
    private int adoptedStatus;


    @ApiModelProperty(value = "图片", name = "pictures", required = true)
    private List<String> pictures;




}
