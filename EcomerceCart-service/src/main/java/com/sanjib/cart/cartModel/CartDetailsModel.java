package com.sanjib.cart.cartModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CartDetailsModel {

	@NotNull(message = "Please Provide User id")
	private Integer userId;
	
	@NotNull(message = "Please Provide product id")
	private Integer productId;
	
	@NotBlank(message = "Please Provide product Name")
	private String productName;
	
	@NotNull(message = "Please Provide product Quantity")
	private Integer quntity;
	
	
}
