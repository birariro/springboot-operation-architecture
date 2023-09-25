package com.birariro.orderapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "member_id")
  private Long memberId;
  @Column(name = "product_id")
  private Long productId;
  @Column(name = "payment_price")
  private Long paymentPrice;

  public Order(Long memberId, Long productId, Long paymentPrice) {
    this.memberId = memberId;
    this.productId = productId;
    this.paymentPrice = paymentPrice;
  }
}
