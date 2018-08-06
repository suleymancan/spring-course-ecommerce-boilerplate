package com.kodgemisi.course.ecommerce.buying;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Created on August, 2018
 *
 * @author suleymancan
 */
@Embeddable
@Getter
@Setter
public class PaymentInfo {


	@NotBlank(message = "bos birakilamaz")
	private String firstName;

	@NotBlank(message = "bos birakilamaz")
	private String lastName;

	@Pattern(regexp ="[0-9\\s]{11}", message = "phone hatalı.")
	private String phone;

	@Email(message = "hatali e-mail")
	private String email;

	@NotBlank(message = "bos birakilamaz")
	private String address;

	@Transient
	private String creditCardName;

	@Transient
	private Long creditCardNumber;

	@Transient
	private String creditCardExpiration;

	@Transient
	private int creditCardCVV;



}
