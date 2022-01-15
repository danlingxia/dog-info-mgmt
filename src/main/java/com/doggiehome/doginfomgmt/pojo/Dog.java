package com.doggiehome.doginfomgmt.pojo;

import lombok.Getter;
import lombok.Setter;
//import org.hibernate.annotations.Table;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
//@Table(name="dog")
public class Dog {

    /**
     *主键，狗狗id
     */
    @Id
    private String id;


    /**
     * 小院
     */
    private String home;

    /**
     * 笼子
     */
    private Integer container;

    /**
     * 狗狗名字
     */
    private String name;


    /**
     * 生日
     */
    private Date birthday;

    /**
     * 性别， 0：母，1：公，2：未知
     */
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

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */

    private Date updatedTime;
}
