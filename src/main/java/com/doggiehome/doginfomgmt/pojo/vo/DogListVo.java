package com.doggiehome.doginfomgmt.pojo.vo;

import com.doggiehome.doginfomgmt.pojo.Dog;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
//
//@SqlResultSetMapping
//        (
//                name = "DogListMapping",
//                classes = @ConstructorResult(
//                        targetClass = DogListVo.class,
//                        columns = {
//                                @ColumnResult(name="id"),
//                                @ColumnResult(name="name"),
//                                @ColumnResult(name="birthday"),
//                                @ColumnResult(name="sex"),
//                                @ColumnResult(name="size"),
//                                @ColumnResult(name="hairLength"),
//                                @ColumnResult(name="url")
//                        }
//                )
//        )
//@NamedNativeQuery(
//        name = "DogListPage",
//        query = "select"
//                + " d.id,"
//                + " d.name,"
//                + " d.birthday,"
//                + " d.sex,"
//                + " d.size,"
//                + " d.hairLength,"
//                + " i.url"
//                + " from Dog d left join DogImg i  "
//                + " on  i.dogId = d.id and i.isMain = 1"
//                + " where d.id in :dogIds",
//        resultSetMapping = "DogListMapping"
//)
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
@Getter
@Setter
//@Entity
public class DogListVo {


    private int id;


    /**
     * 狗狗名字
     */
    private String name;




    /**
     * 生日
     */
    private Date birthday;

    /**
     * 性别， 0：未知, 1：公，2：母
     */
    private int sex;

    /**
     * 体型， 1：小型犬，2：中型犬，3：大型犬
     */
    private int size;

    /**
     * 毛发长度，1：无毛，2：短毛，3：长毛
     */
    private int hairLength;


    /**
     * 主图链接
     */
    private String url;

    /**
     * 主图宽度
     */
    private Integer width;

    /**
     * 主图高度
     */
    private Integer height;

//    public DogListVo(int id, String name, Date birthday, int sex, int size, int hairLength, String url) {
//        this.id = id;
//        this.name = name;
//        this.birthday = birthday;
//        this.sex = sex;
//        this.size = size;
//        this.hairLength = hairLength;
//        this.url = url;
//    }
}


