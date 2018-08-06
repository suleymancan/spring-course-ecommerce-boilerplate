package com.kodgemisi.course.ecommerce.product;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created on August, 2018
 *
 * @author sc
 */
public class ProductSpecification implements Specification<Product> {

	private ProductFilterCriteria criteria;

	ProductSpecification(ProductFilterCriteria criteria) {
	this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		if(criteria.getOperation().equals("="))
		{
			if(criteria.getKey().contains(".")){
				String[] columns = criteria.getKey().split("\\.");
				return criteriaBuilder.equal(root.join(columns[0]).get(columns[1]), criteria.getValue().toString());
			}
			else {
				return  criteriaBuilder.like(root.get(criteria.getKey()),"%"+criteria.getValue().toString()+"%");
			}
		}
		else if(criteria.getOperation().equals(">")){
			return criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()),criteria.getValue().toString());
		}
		else if (criteria.getOperation().equals("<")){
			return criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
		}
		return null;
	}
}
