package com.kodgemisi.course.ecommerce.dashboard;

import com.kodgemisi.course.ecommerce.product.Product;
import com.kodgemisi.course.ecommerce.product.ProductService;
import com.kodgemisi.course.ecommerce.user.Role;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class DashboardController {

    private final ProductService productService;
    @GetMapping
    public  String dashboard(Model model, Pageable pageable, SecurityContextHolderAwareRequestWrapper requestWrapper){
		Page<Product> productPage;
    	if(requestWrapper.isUserInRole(Role.ADMIN.getRoleName().name())) {
			productPage = productService.findAll(pageable);
		}
		else{
			productPage = productService.findAllByEnabled(true, pageable);
		}
        model.addAttribute("products",productPage);
        return "dashboard/index";

    }



    //method security


}
