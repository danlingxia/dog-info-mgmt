package com.doggiehome.doginfomgmt.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Transfer {

    /**
     *主键，狗狗id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String dogName;

    private String cageName;

    /**
     * 中转人姓名
     */
    private String name;

    private Integer phoneNumber;

    private String area;

    private String startTime;

    private String endTime;

    private int isDelete;

    @Column(nullable = false)
    private String remark;
}
