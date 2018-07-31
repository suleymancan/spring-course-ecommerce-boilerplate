package com.kodgemisi.course.ecommerce.admin;

import com.kodgemisi.course.ecommerce.category.Category;
import com.kodgemisi.course.ecommerce.category.CategoryService;
import com.kodgemisi.course.ecommerce.category.CategoryValidator;
import com.kodgemisi.course.ecommerce.user.User;
import com.kodgemisi.course.ecommerce.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private CategoryService categoryService;

    private CategoryValidator categoryValidator;

    @InitBinder
    protected void categoryValid(WebDataBinder webDataBinder){
        webDataBinder.addValidators(categoryValidator);
    }

    @GetMapping
    public String adminDashboard(Model model){
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories",categories);
        return "admin/index";
    }

    //incele
    @PostMapping("/categories")
    public String addNewCategory(Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(!bindingResult.hasErrors()){
            categoryService.save(category);
            redirectAttributes.addFlashAttribute("successMessage","successfull");
        }
        else{
            redirectAttributes.addFlashAttribute("errorMessage", bindingResult.getFieldError("name").getDefaultMessage());
        }
        return "redirect:/admin";
    }


    @DeleteMapping("/categories/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes){
        categoryService.deleteCategory(id);
        redirectAttributes.addFlashAttribute("successMessage","successfull delete");
        return "redirect:/admin";
    }


}
