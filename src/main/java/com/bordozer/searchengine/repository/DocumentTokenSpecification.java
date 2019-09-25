package com.bordozer.searchengine.repository;

import com.bordozer.searchengine.entity.DocumentTokenEntity;
import com.bordozer.searchengine.model.SearchCriteria;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RequiredArgsConstructor
@ToString(of = "searchCriteria")
public class DocumentTokenSpecification implements Specification<DocumentTokenEntity> {
    private final SearchCriteria searchCriteria;

    @Override
    public Predicate toPredicate(final Root<DocumentTokenEntity> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
        final Predicate[] predicates = searchCriteria.getTokens().stream()
                .map(token -> builder.equal(root.<String>get("token"), token))
                .toArray(Predicate[]::new);
        return builder.or(predicates);
    }
}
