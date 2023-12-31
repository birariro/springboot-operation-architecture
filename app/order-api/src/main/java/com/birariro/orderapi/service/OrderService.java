package com.birariro.orderapi.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.birariro.orderapi.domain.Order;
import com.birariro.orderapi.domain.OrderRepository;
import com.birariro.orderapi.domain.Product;
import com.birariro.orderapi.domain.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrderService {
  private final ProductRepository productRepository;
  private final OrderRepository orderRepository;
  private final AuthClient authClient;

  public List<Order> find(){
    return orderRepository.findAll();
  }

  public void save(Long memberId, Long productId, Long count) throws NotFoundException {

    Assert.notNull(memberId, "memberId cannot be null");
    Assert.notNull(productId, "productId cannot be null");
    Assert.notNull(count, "count cannot be null");
    Assert.isTrue(count > 0 ,"count cannot be NegativeOrZero");

    if(!authClient.validMember(memberId)){
      log.debug("not exist member "+ memberId);
      throw new IllegalArgumentException("not exist member "+ memberId);
    }

    Product product = productRepository.findById(productId)
        .orElseThrow(() -> {
          log.warn("not exist product " + productId);
          return new NotFoundException();
        });

    Long paymentPrice = product
              .calculationPaymentPrice(count);

    log.debug(String.format("product '%s' %d order payment price = %d", product.getName(),count, paymentPrice));
    Order order = new Order(memberId, productId, paymentPrice);

    log.debug("order : "+ order);

    orderRepository.save(order);
  }


}
