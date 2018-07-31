package com.kodgemisi.course.ecommerce.product;

import com.kodgemisi.course.ecommerce.user.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model, SecurityContextHolderAwareRequestWrapper requestWrapper){
        if(requestWrapper.isUserInRole(Role.ADMIN.getRoleName().name())){
            model.addAttribute("product",productService.findById(id));

        }
        else {
            model.addAttribute("product", productService.findByIdAndEnabled(id, true));
        }
        return "product/show";

    }

}
