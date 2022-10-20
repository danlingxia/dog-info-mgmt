package com.doggiehome.doginfomgmt.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Adopter {

    /**
     *主键，狗狗id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int dogId;

    private String dogName;

    private String cageName;
    /**
     * 领养人姓名
     */
    private String name;

    private Integer phoneNumber;

    private String area;

    private String adoptTime;

    // 备注
    @Column(nullable = false)
    private String remark;

    // 弃养原因
    @Column(nullable = false)
    private String description;

    // 0是未删除 1是已删除
    private Integer isDelete;
}
