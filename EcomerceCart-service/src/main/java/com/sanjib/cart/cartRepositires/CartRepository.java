package com.sanjib.cart.cartRepositires;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanjib.cart.cartEntities.CartEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {

	public CartEntity findByUserId(Integer userid);

	public Boolean existsByUserId(Integer userid);
	
	
	//DELETE ecomerce.cart,ecomerce.cartdetails FROM ecomerce.cart cart join ecomerce.cartdetails cartd on cart.cart_id=cartd.cartid_fk  WHERE user_id = 1007;
	@Transactional
	@Modifying
	//@Query("delete CartEntity c where c.userId=:userid")
	//@Query("DELETE CartEntity,CartDetailEntity from CartEntity cart join CartDetailEntity cartd on  ")
	public Integer deleteByUserId(Integer userid);

}
