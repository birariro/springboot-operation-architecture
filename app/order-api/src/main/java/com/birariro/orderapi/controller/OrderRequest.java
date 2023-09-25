package com.birariro.orderapi.controller;

import lombok.Getter;

@Getter
public class OrderRequest {
  private Long memberId;
  private Long productId;
  private Long count;
}
