package com.kodgemisi.course.ecommerce.admin;

import com.kodgemisi.course.ecommerce.category.Category;
import com.kodgemisi.course.ecommerce.category.CategoryService;
import com.kodgemisi.course.ecommerce.user.User;
import com.kodgemisi.course.ecommerce.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private CategoryService categoryService;

    @GetMapping
    public String adminDashboard(Model model){
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories",categories);
        return "admin/index";
    }

    @PostMapping("/categories")
    public String addNewCategory(Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(!bindingResult.hasErrors()){
            categoryService.save(category);
            redirectAttributes.addFlashAttribute("message","successfull");
        }
        return "redirect:/admin";
    }



}
