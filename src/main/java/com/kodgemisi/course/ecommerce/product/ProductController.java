package com.kodgemisi.course.ecommerce.product;

import com.kodgemisi.course.ecommerce.category.Category;
import com.kodgemisi.course.ecommerce.category.CategoryService;
import com.kodgemisi.course.ecommerce.user.Role;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private final CategoryService categoryService;
    @GetMapping
    public String filter(Model model, Pageable pageable, ProductFilterDto productFilterDto){
        Page<Product> products = productService.filter(productFilterDto,pageable);
        List<Category> categoryList = categoryService.findAll();

        model.addAttribute("products",products);
        model.addAttribute("productFilterDto",productFilterDto);
        model.addAttribute("categories",categoryList);
        return "product/index";
    }


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
