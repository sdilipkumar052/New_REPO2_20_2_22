package com.sanjib.cart.cartServices;

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
import com.sanjib.cart.cartRepositires.CartDetailRepository;
import com.sanjib.cart.cartRepositires.CartRepository;

@SpringBootTest
public class CartServiceTest {

	@InjectMocks
	CartService cartService;

	@Mock
	private CartDetailRepository cartDetailRepository;

	@Mock
	private CartRepository cartRepositires;

	CartDetailsModel cartModel;

	CartEntity cartEntity;

	CartDetailEntity cartDetailEntity;
	
	List<CartDetailEntity> listCartDetailEntities=new ArrayList<>();;
	
	@BeforeEach
	protected void setUp() throws Exception {
		cartModel = new CartDetailsModel();
		cartModel.setUserId(1007);
		cartModel.setProductId(1);
		cartModel.setProductName("sony tv");
		cartModel.setQuntity(1);
		
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
	@DisplayName(value = "testing for saveProductToCart() API:: Posetive Senerio ")
	public void testSaveProductToCart() {
		when(cartRepositires.existsByUserId(cartModel.getUserId())).thenReturn(true);
		when(cartRepositires.findByUserId(cartModel.getUserId())).thenReturn(cartEntity);
		when(cartDetailRepository.findByProductId(cartModel.getProductId())).thenReturn(cartDetailEntity);
		when(cartDetailRepository.save(cartDetailEntity)).thenReturn(cartDetailEntity);

		ResponseEntity<List<Object>> responseEntity = cartService.saveProductToCart(cartModel);
		List<Object> list = new ArrayList<>();
		list = responseEntity.getBody();
		list.get(0);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Success cart is updated", list.get(0));
		assertEquals(cartDetailEntity, list.get(1));
	}// testSaveProductToCart()

	@Test
	@DisplayName(value = "testing for saveProductToCart1() API:: positive Senerio ")
	public void testSaveProductToCart1() {
		when(cartRepositires.existsByUserId(cartModel.getUserId())).thenReturn(true);
		when(cartRepositires.findByUserId(cartModel.getUserId())).thenReturn(cartEntity);
		when(cartDetailRepository.findByProductId(cartModel.getProductId())).thenReturn(null);
		when(cartDetailRepository.save(cartDetailEntity)).thenReturn(cartDetailEntity);

		ResponseEntity<List<Object>> responseEntity = cartService.saveProductToCart(cartModel);
		List<Object> list = new ArrayList<>();
		list = responseEntity.getBody();
		list.get(0);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		// assertEquals(cartDetailEntity, list.get(1));
		assertEquals("Successfully added to cart", list.get(0));
	}// testSaveProductToCart()
	
	@Test
	@DisplayName(value = "testing for saveProductToCart2() API:: positive Senerio ")
	public void testSaveProductToCart2() {
		when(cartRepositires.existsByUserId(cartModel.getUserId())).thenReturn(false);
		when(cartRepositires.findByUserId(cartModel.getUserId())).thenReturn(cartEntity);
		when(cartDetailRepository.findByProductId(cartModel.getProductId())).thenReturn(null);
		when(cartDetailRepository.save(cartDetailEntity)).thenReturn(cartDetailEntity);

		ResponseEntity<List<Object>> responseEntity = cartService.saveProductToCart(cartModel);
		List<Object> list = new ArrayList<>();
		list = responseEntity.getBody();
		list.get(0);
		// assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		// assertEquals("Success cart is updated", list.get(0));
		// assertEquals(cartDetailEntity, list.get(1));
		assertEquals("Successfully added to cart", list.get(0));
	}// testSaveProductToCart()

	@Test
	@DisplayName(value = "testing for getCartDetailsByUserID() API:: Posetive Senerio ")
	public void testGetCartDetailsByUserID() {
		when(cartRepositires.findByUserId(1007)).thenReturn(cartEntity);

		// event
		ResponseEntity<List<CartDetailEntity>> responseEntity = cartService.getCartDetailsByUserID(1007);

		// outcome
		List<CartDetailEntity> actualCartDetailLIst = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(listCartDetailEntities, actualCartDetailLIst);

	}// testGetCartDetailsByUserID()

	@Test
	@DisplayName(value = "testing for getCartDetailsByUserID1() API:: Negative Senerio ")
	public void testGetCartDetailsByUserID1() {
		when(cartRepositires.findByUserId(1007)).thenReturn(null);

		// event
		ResponseEntity<List<CartDetailEntity>> responseEntity = cartService.getCartDetailsByUserID(1007);

		// outcome
		List<CartDetailEntity> actualCartDetailLIst = responseEntity.getBody();
		List<CartDetailEntity> expectedCartDetailLIst = new ArrayList<>();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(expectedCartDetailLIst, actualCartDetailLIst);
	}// testGetCartDetailsByUserID()


}// class