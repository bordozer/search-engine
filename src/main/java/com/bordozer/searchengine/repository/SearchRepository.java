package com.bordozer.searchengine.repository;

import com.bordozer.searchengine.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends JpaRepository<DocumentEntity, Long> {
}
