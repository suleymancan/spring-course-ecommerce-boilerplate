package com.kodgemisi.course.ecommerce.dashboard;

import com.kodgemisi.course.ecommerce.product.ProductService;
import com.kodgemisi.course.ecommerce.user.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public  String dashboard(Model model, Pageable pageable){
        model.addAttribute("products", productService.findAll(pageable));
        return "dashboard/index";

    }
}
