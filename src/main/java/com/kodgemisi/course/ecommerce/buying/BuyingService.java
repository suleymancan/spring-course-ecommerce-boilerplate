package com.kodgemisi.course.ecommerce.buying;

import com.kodgemisi.course.ecommerce.cart.CartItem;
import com.kodgemisi.course.ecommerce.exception.ResourceNotFoundException;
import com.kodgemisi.course.ecommerce.product.Product;
import com.kodgemisi.course.ecommerce.product.ProductService;
import com.kodgemisi.course.ecommerce.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created on August, 2018
 *
 * @author suleymancan
 */
@Service
@AllArgsConstructor
public class BuyingService {

	private final ProductService productService;

	private final BuyingRepository buyingRepository;

	private final SellingProductRepository sellingProductRepository;


	void save(Buying buying){
		buyingRepository.save(buying);
	}

	Buying createNewBuying(User user, Set<SellingProduct> sellingProducts, PaymentType paymentType) throws ResourceNotFoundException{
		Buying buying = new Buying(user,sellingProducts,paymentType);
		this.save(buying);
		return buying;
	}

	Set<SellingProduct> createSellingProducts(List<CartItem> items){
		return items.stream().map(item->{
			Product product = productService.findById(item.getProductId());
			if(product.getStock()<item.getCount()){
				throw new ResourceNotFoundException();
			}
			SellingProduct selling = new SellingProduct(item.getCount(), product);
			sellingProductRepository.save(selling);
			return  selling;
		})
				.collect(Collectors.toSet());
	}

}
