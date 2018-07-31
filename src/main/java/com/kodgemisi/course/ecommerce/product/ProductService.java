package com.kodgemisi.course.ecommerce.product;

import com.kodgemisi.course.ecommerce.category.Category;
import com.kodgemisi.course.ecommerce.category.CategoryService;
import com.kodgemisi.course.ecommerce.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    public void saveAll(List<Product> productList){
         productRepository.saveAll(productList);
    }

    public Page<Product> findAll(Pageable pageable){
      return   productRepository.findAll(pageable);
    }

    public void save(Product product){
        productRepository.save(product);
    }

    public Product save(ProductDto productDto){

        Category category = categoryService.findById(productDto.getCategoryId());
        Product product = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .stock(productDto.getStock())
                .price(productDto.getPrice())
                .url(productDto.getUrl())
                .creationDate(LocalDate.now())
                .category(category)
                .build();
        save(product);
        return product;
    }

    public Product findById(Long id){
        Optional<Product> productOptional=productRepository.findById(id);
        return productOptional.orElseThrow(()->{
        log.error("product not found by id {}",id);
        return new ResourceNotFoundException();
        });
    }

    public Product findByIdAndEnabled(Long id, boolean enabled){
        Optional<Product> productOptional=productRepository.findByIdAndEnabled(id,enabled);
        return productOptional.orElseThrow(()->{
            log.error("product not found by id {}",id);
            return new ResourceNotFoundException();
        });
    }

    @Transactional
    public void setEnabled(Long id, boolean status){
        Product product = this.findById(id);
        product.setEnabled(status);
    }

    public Page<Product> findAllByEnabled(boolean enabled, Pageable pageable){
    	return productRepository.findAllByEnabled(enabled,pageable);
	}

}
