package com.doggiehome.doginfomgmt.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 狗狗驱虫表
 */
@Entity
@Getter
@Setter
public class Deworming {

    /**
     *主键，驱虫id
     */
    @Id
    private String id;

    /**
     * 狗狗id
     */
    private String dogId;

    /**
     * 驱虫种类，体内驱虫 1，体外驱虫 2
     */
    private String DewormingType;

    /**
     * 驱虫药
     */
    private String DewormingMedicine;

    /**
     * 驱虫时间
     */
    private Date DewormingTime;

}
