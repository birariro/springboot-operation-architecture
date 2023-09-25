package com.birariro.orderapi.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  public void order(Long memberId, Long productId, Long count) throws NotFoundException {

    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new NotFoundException());

    Order order = new Order(memberId, productId,
        product.calculationPaymentPrice(count));

    orderRepository.save(order);
  }
}
