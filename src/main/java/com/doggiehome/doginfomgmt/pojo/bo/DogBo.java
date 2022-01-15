package com.doggiehome.doginfomgmt.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "狗狗对象BO", description = "从客户端，由用户传入的数据封装在此entity中")
public class DogBo {


    /**
     *主键，狗狗id
     */
    @ApiModelProperty(value = "狗狗编号id", example = "sy0001", required = true)
    private String id;


    /**
     * 小院
     */
    @ApiModelProperty(value = "小院编号", example = "sy", required = true)
    private String home;

    /**
     * 笼子
     */
    @ApiModelProperty(value = "笼子编号", example = "1", required = true)
    private Integer container;

    /**
     * 狗狗名字
     */
    @ApiModelProperty(value = "狗狗名字", example = "小白")
    private String name;


    /**
     * 生日
     */
    @ApiModelProperty(value = "狗狗生日")
    private Date birthday;

    /**
     * 性别， 0：女，1：男，2：未知
     */
    @ApiModelProperty(value = "狗狗性别")
    private Integer sex;

    /**
     * 体型， 1：超小型犬，2：小型犬，3：中型犬，4：大型犬
     */
    private Integer size;

    /**
     * 毛发特点，0：无毛，1：短毛，2：长毛
     */
    private Integer hair;

    /**
     * 性格， 1：胆小谨慎，2：安静乖巧，3：活泼开朗
     */
    private Integer personality;

    /**
     * 绝育情况， 0：未绝育，1：已绝育
     */
    private Integer sterilization;
    /**
     * 颜色 1：纯色，2：杂色
     */
    private Integer pureColor;

    /**
     * 颜色 1：浅色，2：深色
     */
    private Integer lightColor;


    /**
     * 被领养状态 被领养状态,1:待领养 2:已领养
     */

    private Integer adoptedStatus;

}
