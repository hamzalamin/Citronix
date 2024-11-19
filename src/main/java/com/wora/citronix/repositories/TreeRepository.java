package com.wora.citronix.repositories;

import com.wora.citronix.models.entities.Tree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeRepository extends JpaRepository<Tree, Long> {
    int countByFieldId(Long id);
}
