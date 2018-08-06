package com.kodgemisi.course.ecommerce.api;

import com.kodgemisi.course.ecommerce.product.Product;
import com.kodgemisi.course.ecommerce.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created on August, 2018
 *
 * @author suleymancan
 */

@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductApiController {

	private final ProductService productService;


	@GetMapping
	Page<Product> getAllProducts(Pageable pageable){
		return productService.findAll(pageable);
	}

//	//@RestController = Controller + @ResponseBody
//	@GetMapping("/{id}")
//	Product getProduct(@PathVariable Long id){
//		return  productService.findById(id);
//	}


	@GetMapping("/get-gist")
	GithubGist getGist(@RequestParam String gistId){
		RestTemplate restTemplate = new RestTemplate();
		GithubGist githubGist = restTemplate.getForObject("https://api.github.com/gists/" + gistId, GithubGist.class);
		return githubGist;
	}

	    @GetMapping(value = "/{id}")
		ResponseEntity<Product> getProductResponseEntity(@PathVariable Long id) {
	        return ResponseEntity.ok(productService.findById(id));
	    }
}
