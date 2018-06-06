package com.example.ch8_2.specs;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by frank on 2018-06-06.
 */
public class CustomerSpecs {
    public static <T> Specification<T> byAuto(final EntityManager entityManager, final T exmaple) {
        final Class<T> type = (Class<T>) exmaple.getClass();

        return new Specification<T>() {
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();

                EntityType<T> entity = entityManager.getMetamodel().entity(type);
                for (Attribute<? super T, ?> attr : entity.getAttributes()) {
                    Object attrValue = getValue(exmaple, attr);
                    if (attrValue != null) {
                        if (attr.getJavaType() == String.class) {
                            if (!StringUtils.isEmpty(attrValue)) {
                                predicates.add(criteriaBuilder.like(root.get(attribute(entity, attr.getName(), String.class)), pattern((String) attrValue)));
                            }
                        } else {
                            predicates.add(criteriaBuilder.equal(root.get(attribute(entity, attr.getName(), attr.getClass())), attrValue));
                        }
                    }
                }
                return predicates.isEmpty() ? criteriaBuilder.conjunction() : criteriaBuilder.and(toArray(predicates, Predicate.class));
            }

            private Predicate toArray(List<Predicate> predicates, Class<Predicate> predicateClass) {
                return null;
            }


            private Object getValue(T exmaple, Attribute<? super T, ?> attr) {
                return ReflectionUtils.getField((Field) attr.getJavaMember(), exmaple);
            }

            private <E, T> SingularAttribute<T, E> attribute(EntityType<T> entity, String fieldName, Class<E> fieldClass) {
                return entity.getDeclaredSingularAttribute(fieldName, fieldClass);
            }

        };
    }

    private static String pattern(String str) {
        return "%" + str + "%";
    }
}
