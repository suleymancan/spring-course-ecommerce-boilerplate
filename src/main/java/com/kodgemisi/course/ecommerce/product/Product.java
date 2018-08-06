package com.kodgemisi.course.ecommerce.product;

import com.kodgemisi.course.ecommerce.category.Category;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@XmlRootElement
public class Product implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	private String name;

	private String description;

	@Min(0)
	private int stock;

	@Min(0)
	@NotNull
	private BigDecimal price;

	private LocalDate creationDate;

	private boolean enabled = true;

	@Pattern(regexp = "^http.*")
	private String url;

	@NotNull
	@OneToOne
	private Category category;

}
