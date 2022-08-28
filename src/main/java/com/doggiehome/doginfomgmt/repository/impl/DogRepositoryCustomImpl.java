package com.doggiehome.doginfomgmt.repository.impl;

import com.doggiehome.doginfomgmt.pojo.Dog;
import com.doggiehome.doginfomgmt.repository.DogRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//
////@Component
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
//@Component
//@NamedNativeQuery(
//        name = "DogListPage",
//        query = "select d.id, d.name, d.birthday, d.sex, d.size, d.hair_length, i.url from dog d left join dog_img i  on  i.dog_id = d.id and i.is_main = 1 where d.id in :dogIds",
//
////        query = "select d.id, d.name, d.birthday, d.sex, d.size, d.hairLength, i.url from Dog d left join DogImg i  on  i.dogId = d.id and i.isMain = 1 where d.id in :dogIds",
//        resultSetMapping = "DogListMapping"
//)
//

@Repository
public class DogRepositoryCustomImpl implements DogRepositoryCustom {


//    private String dogListSql = "select d.id, d.name, d.birthday, d.sex, d.size, d.hair_length, i.url from dog d left join dog_img i  on  i.dog_id = d.id and i.is_main = 1 where d.id = 1";

    @PersistenceContext
    EntityManager em;

//    @Override
//    public List<Dog> findDogs(int sex, List<Integer> range, int size, int hairLength, int pageNum, int pageSize) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Dog> cq = cb.createQuery(Dog.class);
//        Root<Dog> dog = cq.from(Dog.class);
//        List<Predicate> predicates = new ArrayList<>();
//        if (sex >= 0){
//            predicates.add(cb.equal(dog.get("sex"), sex));
//        }
//        if (size >= 0){
//            predicates.add(cb.equal(dog.get("size"), size));
//        }
//        if (hairLength >= 0){
//            predicates.add(cb.equal(dog.get("hairLength"), hairLength));
//        }
//        cq.where(predicates.toArray(new Predicate[0]));
//
//
//        TypedQuery<Dog> typedQuery = em.createQuery(cq);
//        typedQuery.setFirstResult(pageNum);
//        typedQuery.setMaxResults(pageSize);
//        List<Dog> dogs = typedQuery.getResultList();
////        while (pageNumber < count.intValue()) {
////            typedQuery.setFirstResult(pageNumber - 1);
////            typedQuery.setMaxResults(pageSize);
////            System.out.println("Current page: " + typedQuery.getResultList());
////            pageNumber += pageSize;
////        }
//
////        Pageable pageable = new OffsetBasedPageRequest(offset, limit);
////        return this.dataServices.findAllInclusive(pageable);
//        return dogs;
//    }

//        public void findDogList(List<Integer> dogIds){
//
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Dog> cq = cb.createQuery(Dog.class);
//        Metamodel m = em.getMetamodel();
//        EntityType<Dog> Dog_ = m.entity(Dog.class);
//        EntityType<DogImg> DogImg_ = m.entity(DogImg.class);
////        EntityType<DogImg> DogImg_ = m.entity(DogImg.class);
//        Root<Dog> dog = cq.from(Dog.class);
////        Join<Dog, DogImg> dogs = dog.jo//(Dog_.owners);
////            Join<Dog, DogImg> dogDogImgJoin = cq.join(Dog_.id).join(DogImg_.dogId);
//                    Query  query = new JPAQuery(entityManager)
//                .from(QCocktail.cocktail)
//                .join(QRecipe.recipe)
//                .on(QCocktail.cocktail.name.eq(QRecipe.recipe.cocktail))
////        Query  query = new JPAQuery(entityManager)
////                .from(QCocktail.cocktail)
////                .join(QRecipe.recipe)
////                .on(QCocktail.cocktail.name.eq(QRecipe.recipe.cocktail))
//
//    }

//    public void findDogList(List<Integer> dogIds){
//
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Dog> cq = cb.createQuery(Dog.class);
//        Metamodel m = em.getMetamodel();
//        EntityType<Dog> Dog_ = m.entity(Dog.class);
////        EntityType<DogImg> DogImg_ = m.entity(DogImg.class);
//        Root<Dog> dog = cq.from(Dog.class);
//        Join<Dog, DogImg> dogs = dog.jo//(Dog_.owners);
//        Query  query = new JPAQuery(entityManager)
//                .from(QCocktail.cocktail)
//                .join(QRecipe.recipe)
//                .on(QCocktail.cocktail.name.eq(QRecipe.recipe.cocktail))
//
//    }
//    @Autowired
//    private List<Integer> dogid;

//    @Override
//    public List<DogListVo> findDogList(List<Integer> dogIds){
//        TypedQuery<DogListVo> query = (TypedQuery<DogListVo>) em.createNativeQuery(dogListSql, "DogListMapping");
//
////        TypedQuery<DogListVo> query = em.createNamedQuery("DogListPage", DogListVo.class);
//        query.setParameter("dogIds", dogIds);
//        List<DogListVo> dogListVoList = query.getResultList();
//        return dogListVoList;
//    }
    @Override
    public List<Dog> findDogIds(Integer sex, List<Integer> ageRange, Integer size, Integer hairLength) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Dog> cq = cb.createQuery(Dog.class);

        Root<Dog> dog = cq.from(Dog.class);
//        cq.multiselect(dog.get("id"), dog.get(""));
        cq.select(dog.get("id"));
        List<Predicate> predicates = new ArrayList<>();
        if (null != sex){
            predicates.add(cb.equal(dog.get("sex"), sex));
        }
        if (null != ageRange){
            ZonedDateTime zny = ZonedDateTime.now(ZoneId.of("UTC+8"));
            LocalDateTime ldtNow = zny.toLocalDateTime();
            LocalDateTime ldtEnd = ldtNow.minusMonths(ageRange.get(0));
            LocalDateTime ldtStart = ldtNow.minusMonths(ageRange.get(1));
//            List<LocalDateTime> localDateTimeList = List.of(ldtStart, ldtEnd);
            predicates.add(cb.greaterThanOrEqualTo(dog.<LocalDateTime>get("birthday"), ldtStart));
            predicates.add(cb.lessThanOrEqualTo(dog.<LocalDateTime>get("birthday"), ldtEnd));
        }

        if (null != size){
            predicates.add(cb.equal(dog.get("size"), size));
        }
        if (null != hairLength){
            predicates.add(cb.equal(dog.get("hairLength"), hairLength));
        }
        cq.where(predicates.toArray(new Predicate[0]));


//        TypedQuery<Dog> typedQuery = em.createQuery(cq);
//        typedQuery.select(typedQuery.get(Orders_.id));
//        typedQuery.setFirstResult(pageNum);
//        typedQuery.setMaxResults(pageSize);
        List<Dog> dogIds = em.createQuery(cq).getResultList();
//        List<Integer> ids = (List<Integer>)dogIds;
        return dogIds;
    }







}
