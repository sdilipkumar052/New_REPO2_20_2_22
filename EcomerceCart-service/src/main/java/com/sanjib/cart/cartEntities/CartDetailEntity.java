package com.sanjib.cart.cartEntities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "cartdetails")
public class CartDetailEntity {

	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "cartid_fk")
	private Integer cartId;
	private Integer userId;
    private Integer productId;
    private String productName;
    private Integer quantity;
}
