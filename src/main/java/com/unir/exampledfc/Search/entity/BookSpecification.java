package com.unir.exampledfc.search.entity;


import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> hasAttribute(String attributeName, Object value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get(attributeName), value);
        };
    }
}
