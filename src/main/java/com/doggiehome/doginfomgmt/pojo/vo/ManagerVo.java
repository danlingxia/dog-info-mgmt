package com.doggiehome.doginfomgmt.pojo.vo;

import com.doggiehome.doginfomgmt.pojo.Manager;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ManagerVo {

    private int id;

    /**
     * 用户名
     */
    private String username;


    /**
     * 角色，1是超级管理员，2是普通管理员
     */
    private Integer role;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public ManagerVo(Manager manager) {
        this.id = manager.getId();
        this.username = manager.getUsername();
        this.role = manager.getRole();
        this.createTime = manager.getCreateTime();
        this.updateTime = manager.getUpdateTime();
    }

//    public ManagerVo getManagerVo(Manager manager){
//        return new ManagerVo(manager);
//
//    }
}
