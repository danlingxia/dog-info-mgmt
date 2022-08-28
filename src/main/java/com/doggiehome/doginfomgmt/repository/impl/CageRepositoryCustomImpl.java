package com.doggiehome.doginfomgmt.repository.impl;

import com.doggiehome.doginfomgmt.pojo.Cage;
import com.doggiehome.doginfomgmt.pojo.vo.CageVo;
import com.doggiehome.doginfomgmt.repository.CageRepository;
import com.doggiehome.doginfomgmt.repository.CageRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
public class CageRepositoryCustomImpl implements CageRepositoryCustom {


//    private String dogListSql = "select d.id, d.name, d.birthday, d.sex, d.size, d.hair_length, i.url from dog d left join dog_img i  on  i.dog_id = d.id and i.is_main = 1 where d.id = 1";

    @PersistenceContext
    EntityManager em;

    @Autowired
    CageRepository cageRepository;
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
    public Page<Cage> findCageByParam(int yardId, Integer cageId, String cageName, int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC,"id"));

//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Cage> cq = cb.createQuery(Cage.class);
//
//        Root<Cage> cage = cq.from(Cage.class);


//        cq.multiselect(dog.get("id"), dog.get(""));
//        cq.select(cage.get("id"));
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(cb.equal(cage.get("yardId"), yardId));
//        if (null != cageId){
//            predicates.add(cb.equal(cage.get("cageId"), cageId));
//        }
//        if (null != cageName){
//            predicates.add(cb.equal(cage.get("cageName"), cageName));
//        }
//        if (hairLength >= 0){
//            predicates.add(cb.equal(dog.get("hairLength"), hairLength));
//        }
//        cq.where(predicates.toArray(new Predicate[0]));

        return cageRepository.findAll((root, query, builder) -> {
            CriteriaBuilder cb = em.getCriteriaBuilder();
//            CriteriaQuery<Cage> cq = cb.createQuery(Cage.class);
//
//            Root<Cage> cage = cq.from(Cage.class);

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("yardId"), yardId));
            if (null != cageId){
                predicates.add(cb.equal(root.get("id"), cageId));
            }
//            if (null != cageName){
//                predicates.add(cb.equal(root.get("name"), cageName));
//            }
            if (null != cageName){
                predicates.add(cb.like(root.get("name"), "%" + cageName + "%"));
            }

//            List<Predicate> predicates = new ArrayList<>();
//            predicates.add(cb.equal(cage.get("yardId"), yardId));
//            if (null != cageId){
//                predicates.add(cb.equal(cage.get("id"), cageId));
//            }
//            if (null != cageName){
//                predicates.add(cb.equal(cage.get("name"), cageName));
//            }

            // More simple/complex conditions can be added to
            // predicates collection if needed.

            return builder.and(predicates.toArray(new Predicate[]{}));

        }, pageable);
//        cq.

//        TypedQuery<Dog> typedQuery = em.createQuery(cq);
//        typedQuery.select(typedQuery.get(Orders_.id));
//        typedQuery.setFirstResult(pageNum);
//        typedQuery.setMaxResults(pageSize);
//        List<Dog> dogIds = em.createQuery(cq).getResultList();
//        List<Integer> ids = (List<Integer>)dogIds;
//        return null;
    }

    @Override
    public List<CageVo> getCageNames(int yardId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CageVo> cq = cb.createQuery(CageVo.class);

        Root<Cage> cageRoot = cq.from(Cage.class);
//        cq.multiselect(dog.get("id"), dog.get(""));
//        List<Cage> f = List.of(cageRoot.get("id"), cageRoot.get("name"))
        cq.select(cb.construct(CageVo.class, cageRoot.get("id"), cageRoot.get("name")));
//                .select(cageRoot.get("name"));
        List<CageVo> cageVos = em.createQuery(cq).getResultList();

//        System.out.println("KKKKK");
//        System.out.println(list.get(0));
//
//        System.out.println("KKKKK");
//        System.out.println(list.get(0).toString());
//        System.out.println(list.get(0).getName());

        return cageVos;
    }


}
