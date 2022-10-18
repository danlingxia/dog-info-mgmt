package com.doggiehome.doginfomgmt.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;
//import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
//@Table(name="dog")
public class Dog {

    /**
     *主键，狗狗id
     */
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    /**
     * 小院id
     */
    @Column(nullable = false)
    private int yardId;

    /**
     * 笼子id
     */
    @Column(nullable = false)
    private int cageId;

    /**
     * 狗狗编号
     */
    @Column(nullable = false)
    private String identifier;

    /**
     * 狗狗名字
     */
    private String name;


    /**
     * 生日
     */
//    private Date birthday;
    private LocalDateTime birthday;

    /**
     * 性别， 0：未知, 1：公，2：母
     */
    private int sex;

    /**
     * 体型， 1：小型犬，2：中型犬，3：大型犬
     */
    private int size;

    /**
     * 毛发长度，1：无毛，2：短毛，3：长毛
     */
    private int hairLength;

    /**
     * 毛发特点，1：卷毛，2：直毛
     */
    private int hairFeature;

    /**
     * 性格， 0:未知, 1：胆小谨慎，2：安静乖巧，3：活泼开朗
     */
    private int personality;

    /**
     * 绝育情况， 0:未知, 1：未绝育，2：已绝育
     */
    private int sterilization;
//    /**
//     * 颜色 1：纯色，2：杂色
//     */
//    private Integer pureColor;
//
//    /**
//     * 颜色 1：浅色，2：深色
//     */
//    private Integer lightColor;

    /**
     * 来源，0:未知, 1：城区, 2：农村
     */
    private int origin;

    /**
     * 出身，0:未知, 1：被遗弃,  2：跑丢， 3：流浪二代
     */
    private int background;

    /**
     * 被领养状态, 1:待领养 2:已领养
     */

    private int adoptedStatus;

    /**
     * 创建时间
     */
    @CreatedDate
    private Date createdTime;

    /**
     * 更新时间
     */

    @LastModifiedDate
    private Date updatedTime;
}
