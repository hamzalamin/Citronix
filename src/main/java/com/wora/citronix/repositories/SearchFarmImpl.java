package com.wora.citronix.repositories;

import com.wora.citronix.models.entities.Farm;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SearchFarmImpl implements CriteriaAPI{
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Farm> searchFarms(String name, String localization, Double surface, LocalDate creationDate) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Farm> cq = cb.createQuery(Farm.class);
        Root<Farm> farmRoot = cq.from(Farm.class);
        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()){
            predicates.add(cb.like(cb.lower(farmRoot.get("name")), "%" + name.toLowerCase() + "%"));
        }
        if (localization != null && !localization.isEmpty()){
            predicates.add(cb.like(cb.lower(farmRoot.get("localization")), "%" + localization.toLowerCase() + "%"));
        }
        if (surface != null){
            predicates.add(cb.equal(farmRoot.get("surface"), surface));
        }
        if (creationDate != null){
            predicates.add(cb.equal(farmRoot.get("creationDate"), creationDate));
        }
        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        return entityManager.createQuery(cq).getResultList();
    }
}
