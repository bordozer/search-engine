package com.bordozer.searchengine.repository;

import com.bordozer.searchengine.entity.DocumentTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends JpaRepository<DocumentTokenEntity, Long>, JpaSpecificationExecutor<DocumentTokenEntity> {

}
