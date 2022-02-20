package com.sanjib.cart.cartRepositires;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanjib.cart.cartEntities.CartDetailEntity;


@Repository
public interface CartDetailRepository extends JpaRepository<CartDetailEntity, Integer> {

	public CartDetailEntity findByProductId(Integer productId);

	public List<CartDetailEntity> findByUserId(Integer userId);
}
