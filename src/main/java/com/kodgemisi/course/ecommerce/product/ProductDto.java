package com.kodgemisi.course.ecommerce.product;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {

    private Long id;

    private String name;

    private String description;

    @NotNull
    @Min(0)
    private BigDecimal price;

    @Min(0)
    private int stock;


    @Pattern(regexp = "^http.*")
    private String url;

    @NotNull
    private Long categoryId;
}
