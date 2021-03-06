package com.kodgemisi.course.ecommerce.admin;

import com.kodgemisi.course.ecommerce.category.CategoryService;
import com.kodgemisi.course.ecommerce.product.Product;
import com.kodgemisi.course.ecommerce.product.ProductDto;
import com.kodgemisi.course.ecommerce.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/products")
public class AdminProductController {

    private final ProductService productService;

    private final CategoryService categoryService;

    @GetMapping
    public String index(Model model, Pageable pageable){
        model.addAttribute("products",productService.findAll(pageable));
        return "admin/product/index";

    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model){
        model.addAttribute("product",productService.findById(id));
        return "admin/product/show";
        //yok
    }

    @GetMapping("/new")
    public String newProduct(Model model){
        model.addAttribute("productDto",new ProductDto());
        model.addAttribute("categories", categoryService.findAll());
        return "admin/product/new";
    }

    @PostMapping
    public String create(@Valid ProductDto productDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin/product/new";

        }
        Product product =productService.save(productDto);
        return "redirect:/admin/products/"+product.getId();
    }

    @PatchMapping
    public String changeEnabledStatus(Long id, boolean enabled){
        productService.setEnabled(id,enabled);
        return "redirect:/admin/products";
    }

}
