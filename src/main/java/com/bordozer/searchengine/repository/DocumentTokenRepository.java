package com.bordozer.searchengine.repository;

import com.bordozer.searchengine.entity.DocumentTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*TODO: delete*/
@Repository
public interface DocumentTokenRepository extends JpaRepository<DocumentTokenEntity, Long> {

    List<DocumentTokenEntity> findByTokenIn(List<String> tokens);
}
