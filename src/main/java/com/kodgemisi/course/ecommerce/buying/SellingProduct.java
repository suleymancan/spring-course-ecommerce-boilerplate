package com.kodgemisi.course.ecommerce.buying;

import com.kodgemisi.course.ecommerce.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created on August, 2018
 *
 * @author suleymancan
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SellingProduct implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private int count;

	@OneToOne
	@NotNull
	private Product product;

	public SellingProduct(@NotNull int count, @NotNull Product product) {
		this.count = count;
		this.product = product;
	}
}
