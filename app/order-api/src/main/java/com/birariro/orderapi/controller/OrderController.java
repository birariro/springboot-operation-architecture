package com.birariro.orderapi.controller;

import javax.validation.Valid;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.birariro.orderapi.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j

public class OrderController {

  private final OrderService orderService;

  @PostMapping("/order")
  public ResponseEntity order(@Valid @RequestBody OrderRequest orderRequest) throws NotFoundException {


    orderService.save(orderRequest.getMemberId(), orderRequest.getProductId(), orderRequest.getCount());
    return ResponseEntity.ok().build();
  }

  @GetMapping("/order")
  public ResponseEntity order()  {

    return ResponseEntity.ok().body(orderService.find());
  }
}
