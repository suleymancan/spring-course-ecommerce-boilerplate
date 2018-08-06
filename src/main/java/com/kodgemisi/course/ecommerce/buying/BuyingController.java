package com.kodgemisi.course.ecommerce.buying;

import com.kodgemisi.course.ecommerce.cart.CartItem;
import com.kodgemisi.course.ecommerce.cart.CartService;
import com.kodgemisi.course.ecommerce.exception.ResourceNotFoundException;
import com.kodgemisi.course.ecommerce.product.ProductService;
import com.kodgemisi.course.ecommerce.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * Created on August, 2018
 *
 * @author suleymancan
 */
@Controller
@RequestMapping("/buy")
@AllArgsConstructor
public class BuyingController {

	private final CartService cartService;

	private final BuyingService buyingService;

	private final ProductService productService;

	@GetMapping("/checkout")
	String checkout(Model model){
		List<CartItem> cartItems = cartService.getAllItems();
		BigDecimal total = BigDecimal.valueOf(0);
		for(CartItem cartItem : cartItems){
			BigDecimal itemTotal = cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getCount()));
			total = total.add(itemTotal);
		}
		model.addAttribute("buying",new Buying());
		model.addAttribute("cartItems",cartItems);
		model.addAttribute("cartTotal",total);
		return "buying/checkout";
	}

	@PostMapping
	String proceedPayment(@AuthenticationPrincipal User user, @Valid Buying buying, BindingResult bindingResult, RedirectAttributes redirectAttributes){

		List<CartItem> cartItems = cartService.getAllItems();

		try{
			buying.setUser(user);
			Set<SellingProduct> sellingProducts = buyingService.createSellingProducts(cartItems);
			buying.setSellingProduct(sellingProducts);
			buying.setPaymentType(PaymentType.CREDIT_CARD);
			productService.updateStockCounts(sellingProducts);

		}
		catch(ResourceNotFoundException e){
			redirectAttributes.addFlashAttribute("errorMessage","shopping error! stock not enough.");
			return "redirect:/";
		}

		buying.setBuyingStatus(BuyingStatus.APPROVED);
		buyingService.save(buying);
		cartService.removeAllItems();

		redirectAttributes.addFlashAttribute("successMessage","shopping success!");

		return "redirect:/";
	}

}
