package com.doggiehome.doginfomgmt.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 管理人员信息表
 */
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Manager {
    /**
     *主键，管理人员id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * 用户名
     */
    @Column(nullable = false, unique=true)
    private String username;


    private String secretKey;

    /**
     * 密码
     */
    @Column(nullable = false)
    private String password;

    /**
     * 角色，1是超级管理员，2是普通管理员
     */
    private Integer role;

    /**
     * 创建时间
     */
    @CreatedDate
    private Date createTime;

    /**
     * 更新时间
     */
    @LastModifiedDate
    private Date updateTime;


}
