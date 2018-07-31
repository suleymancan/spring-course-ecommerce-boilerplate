package com.kodgemisi.course.ecommerce.cart;

import com.kodgemisi.course.ecommerce.product.Product;
import com.kodgemisi.course.ecommerce.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    private final ProductService productService;

    @GetMapping
    public String show(Model model){
        List<CartItem> cartItems = cartService.getAllItems();
        cartItems.stream().forEach(item -> {
            Product product = productService.findById(item.getProductId());
            item.setProduct(product);
        });
        model.addAttribute("cartItems",cartItems);
        return "cart";
    }

    @PostMapping("/new")
    public String addNewProduct(CartItem cartItem){
        cartService.addNewItem(cartItem);
        return "redirect:/products/"+cartItem.getProductId();
    }

    @PostMapping("/update")
    public String updateItem(CartItem cartItem){
        cartService.updateItem(cartItem);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeCart(Long productId, HttpSession session){
        cartService.removeItem(productId);
        return "redirect:/cart";
    }

}
