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

    private String dogName;

    private String cageName;
    /**
     * 领养人姓名
     */
    private String name;

    private Integer phoneNumber;

    private String area;

    private String adoptTime;

    @Column(nullable = false)
    private String remark;
}
