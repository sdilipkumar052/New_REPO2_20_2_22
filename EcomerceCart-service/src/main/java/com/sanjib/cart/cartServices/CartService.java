package com.sanjib.cart.cartServices;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sanjib.cart.cartEntities.CartDetailEntity;
import com.sanjib.cart.cartEntities.CartEntity;
import com.sanjib.cart.cartModel.CartDetailsModel;
import com.sanjib.cart.cartRepositires.CartDetailRepository;
import com.sanjib.cart.cartRepositires.CartRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@SuppressWarnings("unused")
public class CartService {

	@Autowired
	private CartDetailRepository cartDetailRepository;

	@Autowired
	private CartRepository cartRepositires;

	public ResponseEntity<List<Object>> saveProductToCart(CartDetailsModel cartModal) {

		List<Object> body = new LinkedList<>();
		CartDetailEntity cartDetailEntity = new CartDetailEntity();
		
		boolean flag=cartRepositires.existsByUserId(cartModal.getUserId());
		 CartEntity cart;
		if (flag==false) {
			CartEntity cartEntityNew = new CartEntity();
			cartEntityNew.setUserId(cartModal.getUserId());
			cart = cartRepositires.save(cartEntityNew);
		} // if
		cart = cartRepositires.findByUserId(cartModal.getUserId());
		CartDetailEntity cartDatialsEntity = cartDetailRepository.findByProductId(cartModal.getProductId());
		if (cartDatialsEntity != null) {
			if (cartDatialsEntity.getProductId() == cartModal.getProductId()) {
				cartDatialsEntity.setQuantity(cartDatialsEntity.getQuantity() + (cartModal.getQuntity()));
				CartDetailEntity cartreturns = cartDetailRepository.save(cartDatialsEntity);
				body.add("Success cart is updated");
				body.add(cartreturns);
				return new ResponseEntity<>(body, HttpStatus.OK);
			} // if

		} // if
		cartDetailEntity.setCartId(cart.getCartId());
		cartDetailEntity.setUserId(cartModal.getUserId());
		cartDetailEntity.setProductId(cartModal.getProductId());
		cartDetailEntity.setProductName(cartModal.getProductName());
		cartDetailEntity.setQuantity(cartModal.getQuntity());
		body.add("Successfully added to cart");
		body.add(cartDetailRepository.save(cartDetailEntity));
		return new ResponseEntity<>(body, HttpStatus.OK);
	}

	public ResponseEntity<List<CartDetailEntity>> getCartDetailsByUserID(Integer userId) {
		CartEntity cartEntity = cartRepositires.findByUserId(userId);
		if (cartEntity!=null) {
			List<CartDetailEntity> cartDetailEntitylist = cartEntity.getCartDetailEntity();
			return new ResponseEntity<>(cartDetailEntitylist, HttpStatus.OK);
		}
		List<CartDetailEntity> emptyList=new ArrayList<>();
		return new ResponseEntity<>(emptyList, HttpStatus.OK);
	}

}//class