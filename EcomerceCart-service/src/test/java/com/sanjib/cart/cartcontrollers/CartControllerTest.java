package com.sanjib.cart.cartcontrollers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sanjib.cart.cartEntities.CartDetailEntity;
import com.sanjib.cart.cartEntities.CartEntity;
import com.sanjib.cart.cartModel.CartDetailsModel;
import com.sanjib.cart.cartRepositires.CartRepository;
import com.sanjib.cart.cartServices.CartService;

@SpringBootTest
public class CartControllerTest {

	@InjectMocks
	CartController cartController;

	@Mock
	private CartService cartService;

	@Mock
	private CartRepository carRepo;

	CartDetailsModel cartDetailModel;
	
	CartEntity cartEntity;

	CartDetailEntity cartDetailEntity;
	
	List<CartDetailEntity> listCartDetailEntities=new ArrayList<>();;


	@BeforeEach
	protected void setUp() throws Exception {
		cartDetailModel = new CartDetailsModel();
		cartDetailModel.setUserId(1007);
		cartDetailModel.setProductId(1);
		cartDetailModel.setProductName("sony tv");
		cartDetailModel.setQuntity(1);
		
		cartDetailEntity = new CartDetailEntity();
		cartDetailEntity.setId(1);
		cartDetailEntity.setCartId(1);
		cartDetailEntity.setProductId(1);
		cartDetailEntity.setProductName("sony tv");
		cartDetailEntity.setQuantity(1);
		cartDetailEntity.setUserId(1007);
		listCartDetailEntities.add(cartDetailEntity);

		cartEntity = new CartEntity();
		cartEntity.setCartId(1);
		cartEntity.setUserId(1007);
		cartEntity.setCartDetailEntity(listCartDetailEntities);

	}// setUp

	@Test
	@DisplayName(value = "testing for testSaveToCart() API:: Posetive Senerio ")
	public void testSaveToCart() {
		List<Object> listObj=new ArrayList<>();
		listObj.add(cartDetailModel);
		when(cartService.saveProductToCart(cartDetailModel)).thenReturn(new ResponseEntity<List<Object>>(listObj,HttpStatus.OK));
		
		//event
		ResponseEntity<List<Object>> responseEntity =cartController.saveToCart(cartDetailModel);
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}// testSaveToCart()
	
	@Test
	@DisplayName(value = "testing for testSaveToCart1() API:: Negative  Senerio ")
	public void testSaveToCart1() {
		List<Object> listObj=new ArrayList<>();
		listObj.add(cartDetailModel);
		when(cartService.saveProductToCart(cartDetailModel)).thenReturn(new ResponseEntity<>(null,HttpStatus.BAD_REQUEST));
		
		//event
		ResponseEntity<List<Object>> responseEntity =cartController.saveToCart(cartDetailModel);
		
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertEquals(null, responseEntity.getBody());
	}// testSaveToCart()

	@Test
	@DisplayName(value = "testing for testGetCartDetailsByUserID() API:: Posetive Senerio ")
	public void testGetCartDetailsByUserID() {
		
		when(cartService.getCartDetailsByUserID(1007)).thenReturn(new ResponseEntity<>(listCartDetailEntities, HttpStatus.OK));
		
		ResponseEntity<List<CartDetailEntity>> responseEntity =cartController.getCartDetailsByUserID(1007);
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(listCartDetailEntities, responseEntity.getBody());
		
	}//testGetCartDetailsByUserID()

	@Test
	@DisplayName(value = "testing for testGetCartDetailsByUserID1() API:: Negative Senerio ")
	public void testGetCartDetailsByUserID1() {
		
		when(cartService.getCartDetailsByUserID(1007)).thenReturn(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
		
		ResponseEntity<List<CartDetailEntity>> responseEntity =cartController.getCartDetailsByUserID(1007);
		
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertEquals(null, responseEntity.getBody());
		
	}//testGetCartDetailsByUserID()
	
	
	@Test
	@DisplayName(value = "testing for testDeleteCart() API:: Posetive Senerio ")
	public void testDeleteCart() {
		when(carRepo.deleteByUserId(1007)).thenReturn(1007);
		
		Integer userId = cartController.deleteCart(1007);
		
		assertEquals(1007, userId);
	}//deleteByUserId()
	
	@Test
	@DisplayName(value = "testing for testDeleteCart() API:: Negative Senerio ")
	public void testDeleteCart1() {
		when(carRepo.deleteByUserId(null)).thenReturn(null);
		
		Integer userId = cartController.deleteCart(null);
		
		assertEquals(null, userId);
	}//deleteByUserId1()
	
	
}

