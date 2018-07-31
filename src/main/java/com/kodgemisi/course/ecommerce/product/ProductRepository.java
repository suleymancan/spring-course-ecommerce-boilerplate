package com.kodgemisi.course.ecommerce.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

	Page<Product> findAllByEnabled(@Param("enabled") boolean enabled, Pageable pageable);

	Optional<Product> findByIdAndEnabled(@Param("id") Long id, @Param("enabled") boolean enabled);
}
