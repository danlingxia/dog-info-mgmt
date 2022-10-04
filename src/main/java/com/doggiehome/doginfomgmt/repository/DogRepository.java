package com.doggiehome.doginfomgmt.repository;

import com.doggiehome.doginfomgmt.pojo.Dog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends JpaRepository<Dog, Integer> {
    Dog findDogById(int id);


    List<Dog> findAllByCageId(int cageId);

    int deleteAllById(int id);
//    @Modifying
//    @Query("update dog d set ")
//    int updateDogById();




//    @Query(value = "", nativeQuery = true)
//    int saveIgnoreIfExist(Dog dog);

    @Query(value = "select dog.id, dog.name, dog.birthday, dog.sex, dog.size, dog.hair_length, dog_img.url, dog_img.width, dog_img.height from dog left join dog_img on  dog_img.dog_id = dog.id and dog_img.is_main = 1 where dog.id in :dogIds ", nativeQuery = true)
    List<Object[]> getDogList(@Param("dogIds") List<Integer> dogIds);



    @Query(value = "select d.id ,d.yard_id, d.cage_id, d.identifier, d.name, d.birthday, d.sex, d.size, d.sterilization, i.url  from dog d left join dog_img i on  i.dog_id = d.id and i.is_main = 1 where d.yard_id = :yardId and d.cage_id = :cageId ",
            countQuery = "select count(*) from dog d left join dog_img i on  i.dog_id = d.id and i.is_main = 1 where d.yard_id = :yardId and d.cage_id = :cageId ", nativeQuery = true)
    Page<Object[]> getDogManageList(@Param("yardId") int yardId, @Param("cageId") int cageId, Pageable pageable);

//    @Query(value = "select d.id , d.name, d.birthday, d.sex, d.size, d.sterilization, i.url  from dog d left join dog_img i on  i.dog_id = d.id and i.is_main = 1 where d.yard_id = :yardId and d.cage_id = :cageId ", nativeQuery = true)
//    Page<Object[]> getDogManageList(@Param("yardId") int yardId, @Param("cageId") int cageId, Pageable pageable);
//    @Query(value = "select dog.id, dog.name, dog.birthday, dog.sex, dog.size, dog.sterilization, dog_img.url  from dog left join dog_img on  dog_img.dog_id = dog.id and dog_img.is_main = 1 where dog.yard_id = :yardId and dog.cage_id = :cageId ", nativeQuery = true)
//    Page<Object[]> getDogManageList(@Param("yardId") int yardId, @Param("cageId") int cageId, Pageable pageable);



//    @Query(value = "select dog.id, dog.name, dog.birthday, dog.sex, dog.size, dog.sterilization, dog_img.url  from dog left join dog_img on  dog_img.dog_id = dog.id and dog_img.is_main = 1 where dog.yard_id = :yardId and dog.cage_id = :cageId ", nativeQuery = true)
//    List<Object[]> getDogManageList(@Param("yardId") int yardId, @Param("cageId") int cageId);



//
//    @Query(value = "select new com.doggiehome.doginfomgmt.pojo.vo.DogListVo(d.id, d.name, d.birthday, d.sex, d.size, d.hairLength, i.url) from Dog d left join DogImg i on  i.dogId = d.id and i.isMain = 1 where d.id in :dogIds ")
//    List<DogListVo> getDogList(@Param("dogIds") List<Integer> dogIds);


//    @Query(value = "select d.id , d.name, d.birthday, d.sex, d.size, d.sterilization, i.url  from dog d left join dog_img i on  i.dog_id = d.id and i.is_main = 1 where d.yard_id = :yardId and d.cage_id = :cageId ",
//            countQuery = "select count(*) from dog d left join dog_img i on  i.dog_id = d.id and i.is_main = 1 where d.yard_id = :yardId and d.cage_id = :cageId ", nativeQuery = true)
//    Page<Object[]> findDogManageList(@Param("yardId") int yardId, @Param("cageId") int cageId,
//                                     @Param("cageId") int cageId,
//                                     @Param("cageId") int cageId,
//                                     @Param("cageId") int cageId,Pageable pageable);

//
//    @Query(value = "select d.id ,d.yard_id, d.cage_id, c.name as cage_name, d.identifier, d.name, d.birthday, d.sex, d.size, d.sterilization, i.url  from dog d inner join cage c on d.cage_id = c.id inner join dog_img i on  i.dog_id = d.id and i.is_main = 1 where  d.yard_id = :yardId and (:cageId is null or d.cage_id = :cageId)  and (:cageName is null or c.name like CONCAT('%',cast(:cageName as char),'%')) and (:identifier is null or d.identifier = :identifier)  and (:dogName is null or d.name like CONCAT('%',:dogName,'%'))",
//            countQuery = "select count(*)  from dog d inner join cage c on d.cage_id = c.id inner join dog_img i on  i.dog_id = d.id and i.is_main = 1 where  d.yard_id = :yardId and (:cageId is null or d.cage_id = :cageId)  and (:cageName is null or c.name like CONCAT('%',cast(:cageName as char),'%')) and (:identifier is null or d.identifier = :identifier)  and (:dogName is null or d.name like CONCAT('%',:dogName,'%')) ",
//            nativeQuery = true)
//    Page<Object[]> getDogManageByTerm(int yardId, Integer cageId, String cageName, String identifier, String dogName, Pageable pageable);


//
    @Query(value = "select d.id ,d.yard_id, d.cage_id, c.name as cage_name, d.identifier, d.name, d.birthday, d.sex, d.size, d.sterilization, i.url  from dog d inner join cage c on d.cage_id = c.id inner join dog_img i on  i.dog_id = d.id and i.is_main = 1 where  d.yard_id = :yardId and (:cageId is null or d.cage_id = :cageId)  and (:cageName is null or c.name like CONCAT('%',:cageName,'%')) and (:identifier is null or d.identifier = :identifier)  and (:dogName is null or d.name like CONCAT('%',:dogName,'%'))",
            countQuery = "select count(*)  from dog d inner join cage c on d.cage_id = c.id inner join dog_img i on  i.dog_id = d.id and i.is_main = 1 where  d.yard_id = :yardId and (:cageId is null or d.cage_id = :cageId)  and (:cageName is null or c.name like CONCAT('%',:cageName,'%')) and (:identifier is null or d.identifier = :identifier)  and (:dogName is null or d.name like CONCAT('%',:dogName,'%')) ",
            nativeQuery = true)
    Page<Object[]> getDogManageByTerm(int yardId, Integer cageId, String cageName, String identifier, String dogName, Pageable pageable);
}
