package com.birariro.orderapi.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long price;
    private Long count;

    public Long calculationPaymentPrice(Long orderCount){

        if(this.count - orderCount < 0) throw new IllegalStateException("재고 부족");

        this.count -= orderCount;
        return this.price * orderCount;
    }
}
