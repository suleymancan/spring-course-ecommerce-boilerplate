package com.kodgemisi.course.ecommerce.category;

import com.kodgemisi.course.ecommerce.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class CategoryService {

    private  final CategoryRepository categoryRepository;

    public void saveAll(List<Category> categoryList){
        categoryRepository.saveAll(categoryList);
    }


    public void save(Category category){
        categoryRepository.save(category);

    }

    public Category findById(Long id){
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        return categoryOptional.orElseThrow(()->{
                log.error("category not found by id {}",id);
                return new ResourceNotFoundException();
                }
                );
    }

    public List<Category> findAll(){
        return (List) categoryRepository.findAll();
    }

    public boolean existsByName(String name){
        return categoryRepository.existsByName(name);
    }

}
