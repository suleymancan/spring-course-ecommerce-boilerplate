package com.kodgemisi.course.ecommerce.buying;

import com.kodgemisi.course.ecommerce.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * Created on August, 2018
 *
 * @author suleymancan
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Buying implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private User user;

	@OneToMany
	private Set<SellingProduct> sellingProduct;

	@Embedded
	private PaymentInfo paymentInfo;

	@Enumerated(EnumType.STRING)
	@NotNull
	private PaymentType paymentType;

	@Enumerated(EnumType.STRING)
	@NotNull
	private BuyingStatus buyingStatus;

	public Buying(User user, Set<SellingProduct> sellingProduct, @NotNull PaymentType paymentType) {
		this.user = user;
		this.sellingProduct = sellingProduct;
		this.paymentType = paymentType;
		this.buyingStatus = BuyingStatus.PREPARING;
	}
}
