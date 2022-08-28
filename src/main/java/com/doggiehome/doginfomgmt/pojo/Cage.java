package com.doggiehome.doginfomgmt.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 笼子表
 */
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Cage {

    /**
     *主键，笼子id
     */
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    /**
     * 小院
     */
    @Column(nullable = false)
    private int yardId;



    /**
     * 笼子名字
     */
    private String name;



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
