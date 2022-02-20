package com.sanjib.cart.cartEntities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "cart")
public class CartEntity {
	
	@Id
	@GeneratedValue
	private Integer cartId;
	private Integer userId;
	
	@OneToMany(targetEntity = CartDetailEntity.class, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "cartid_fk", referencedColumnName = "cartId")
	private List<CartDetailEntity> cartDetailEntity;
	

}
