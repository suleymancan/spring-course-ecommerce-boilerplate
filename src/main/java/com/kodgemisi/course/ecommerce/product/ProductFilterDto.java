package com.kodgemisi.course.ecommerce.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created on August, 2018
 *
 * @author sc
 */
@Getter
@Setter
public class ProductFilterDto {

	private String keyword;

	private BigDecimal minPrice;

	private BigDecimal maxPrice;

	private String categoryName;

}
