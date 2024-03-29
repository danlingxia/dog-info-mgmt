package com.doggiehome.doginfomgmt.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class DogImg {

    /**
     * 图片主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 狗狗外键id 狗狗外键id
     */

    private int dogId;

    /**
     * 图片地址 图片地址
     */
    @Column(nullable = false)
    private String url;

    /**
     * 顺序 图片顺序，从小到大
     */
    @Column(nullable = false)
    private Integer sort;

    /**
     * 是否主图 是否主图，1：是，0：否
     */
    @Column(nullable = false)
    private Integer isMain;

    /**
     * 图片宽度
     */
    @Column(nullable = false)
    private Integer width;

    /**
     * 图片高度
     */
    @Column(nullable = false)
    private Integer height;

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
