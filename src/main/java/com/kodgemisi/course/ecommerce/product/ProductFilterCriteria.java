package com.kodgemisi.course.ecommerce.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created on August, 2018
 *
 * @author sc
 */
@Getter
@Setter
@AllArgsConstructor
public class ProductFilterCriteria {

	private String key;

	private String operation;

	private Object value;

}
