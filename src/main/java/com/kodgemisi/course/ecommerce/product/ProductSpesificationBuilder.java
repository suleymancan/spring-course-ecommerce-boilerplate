package com.kodgemisi.course.ecommerce.product;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on August, 2018
 *
 * @author sc
 */

public class ProductSpesificationBuilder {

	private final List<ProductFilterCriteria> params;

	ProductSpesificationBuilder() {
		params = new ArrayList<ProductFilterCriteria>(	);
	}

	ProductSpesificationBuilder with(String key, String operation, Object value){
		if(value!=null && !value.toString().equals("")){
		params.add(new ProductFilterCriteria(key,operation,value));
		}
		return this;
	}

	Specification<Product> build(){
		if(params.size()==0){
			return  null;
		}

		List<Specification<Product>> specs = new ArrayList<Specification<Product>>();
		for(ProductFilterCriteria param : params){
			specs.add(new ProductSpecification(param));
		}
		Specification<Product> result =specs.get(0);
		for(int i = 1; i<specs.size(); i++){
			result = Specification.where(result).and(specs.get(i));
		}
		return result;
	}
}
