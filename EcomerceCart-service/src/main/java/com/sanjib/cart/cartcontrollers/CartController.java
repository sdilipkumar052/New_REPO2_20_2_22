package com.sanjib.cart.cartcontrollers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanjib.cart.cartEntities.CartDetailEntity;
import com.sanjib.cart.cartModel.CartDetailsModel;
import com.sanjib.cart.cartRepositires.CartRepository;
import com.sanjib.cart.cartServices.CartService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cart")
@Slf4j
@SuppressWarnings("unused")
@Validated
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CartRepository carRepo;

	@PostMapping("/save")
	public ResponseEntity<List<Object>> saveToCart(@Valid @RequestBody CartDetailsModel cartInfo) {
		return cartService.saveProductToCart(cartInfo);
	}//
	
	@GetMapping("/get/{userid}")
	public ResponseEntity<List<CartDetailEntity>> getCartDetailsByUserID(@PathVariable("userid") Integer userId) {
		return cartService.getCartDetailsByUserID(userId);
		
	}//getCartDetailsByUserID(-)
	
	@GetMapping(value = "/deletecart/{userid}")
	public Integer deleteCart(@PathVariable("userid") Integer userId) {
		return carRepo.deleteByUserId(userId);
		
	}//deleteCart(-)
	
}//class