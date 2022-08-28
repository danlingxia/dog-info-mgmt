package com.doggiehome.doginfomgmt.pojo;

import javax.persistence.Id;
import java.util.Date;

public class Yard {


    /**
     *主键，小院id
     */
    @Id
    private String id;


    /**
     * 小院名字
     */
    private String name;

    /**
     * 小院地址
     */
    private String address;


    /**
     * 小院描述
     */
    private String desc;


    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */

    private Date updatedTime;
}
