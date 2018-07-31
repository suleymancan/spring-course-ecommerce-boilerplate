package com.kodgemisi.course.ecommerce.cart;

import com.kodgemisi.course.ecommerce.product.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {

    private Long productId;

    private int count;

    private Product product;


}
