package com.bordozer.searchengine.repository;

import com.bordozer.searchengine.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {

    DocumentEntity findByKey(String key);
}
