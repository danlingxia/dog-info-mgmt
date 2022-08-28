package com.doggiehome.doginfomgmt.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class Disease {

    /**
     * 主键，疾病id
     */
    @Id
    private String id;

    /**
     * 狗狗id
     */
    private String dogId;

    /**
     * 病情简介
     */
    private String title;

    /**
     * 详细描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;


}
