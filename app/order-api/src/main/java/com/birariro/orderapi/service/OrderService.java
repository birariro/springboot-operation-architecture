package com.birariro.orderapi.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

  public List<Order> find(){
    return orderRepository.findAll();
  }

  public void save(Long memberId, Long productId, Long count) throws NotFoundException {

    if(!validMember(memberId)) {
      log.warn("not exist member "+ memberId);
      throw new NotFoundException();
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

  public boolean validMember(Long memberId){

    String url = "http://host.docker.internal:11801/check?id=" + memberId;
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Object> forEntity = restTemplate.getForEntity(url, Object.class);

    HttpStatus statusCode = forEntity.getStatusCode();
    log.debug("checkMember response http statue code : "+ statusCode);

    return statusCode.is2xxSuccessful();
  }
}
