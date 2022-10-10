package com.doggiehome.doginfomgmt.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@ApiModel(value = "狗狗对象Vo", description = "由页面传入的数据封装在此实体中")
@Data
public class DogModifyVo {

    @ApiModelProperty(value = "狗狗id", name = "id", example = "1", required = true)
    private int id;

    @ApiModelProperty(value = "小院id", name = "yardId", example = "1", required = true)
    private int yardId;

    @ApiModelProperty(value = "笼子id", name = "cageId", example = "1", required = true)
    private int cageId;

    @ApiModelProperty(value = "狗狗名字", name = "name", example = "小白", required = true)
    private String name;

    @ApiModelProperty(value = "生日", name = "birthday", example = "yyyy-MM-ddThh:MM")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "性别， 0：未知, 1：公，2：母", name = "sex", allowableValues="0, 1, 2", required = true)
    private int sex;

    @ApiModelProperty(value = "体型， 1：小型犬，2：中型犬，3：大型犬", name = "size", allowableValues="1, 2, 3", required = true)
    private int size;

    @ApiModelProperty(value = "毛发长度，1：无毛，2：短毛，3：长毛", name = "hairLength", allowableValues="1, 2, 3", required = true)
    private int hairLength;

    @ApiModelProperty(value = "毛发特点，1：卷毛，2：直毛", name = "hairFeature", allowableValues="1, 2", required = true)
    private int hairFeature;

    @ApiModelProperty(value = "性格， 0:未知, 1：胆小谨慎，2：安静乖巧，3：活泼开朗", name = "personality", allowableValues="0, 1, 2, 3", required = true)
    private int personality;

    @ApiModelProperty(value = "绝育情况， 0:未知, 1：未绝育，2：已绝育", name = "sterilization", allowableValues="0, 1, 2", required = true)
    private int sterilization;

    @ApiModelProperty(value = "来源，0:未知, 1：城区, 2：农村", name = "origin",  allowableValues="0, 1, 2", required = true)
    private int origin;

    @ApiModelProperty(value = "出身，0:未知, 1：被遗弃,  2：跑丢， 3：流浪二代", name = "background", allowableValues="0, 1, 2, 3", required = true)
    private int background;

    @ApiModelProperty(value = "图片", name = "pictures", required = true)
    private List<String> pictures;
}