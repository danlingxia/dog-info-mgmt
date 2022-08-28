package com.doggiehome.doginfomgmt.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 疫苗接种情况
 */
@Entity
@Getter
@Setter
public class Vaccine {

    /**
     *主键，接种id
     */
    @Id
    private String id;

    /**
     * 狗狗id
     */
    private String dogId;


    /**
     * 疫苗名称
     */
    private String vaccineName;

    /**
     * 第几针
     */
    private int sequence;

    /**
     * 接种时间
     */
    private Date injectTime;


}
