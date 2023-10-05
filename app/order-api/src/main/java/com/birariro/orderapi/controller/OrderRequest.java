package com.birariro.orderapi.controller;

import javax.validation.constraints.NegativeOrZero;

import com.sun.istack.NotNull;

import lombok.Getter;

@Getter
public class OrderRequest {

  @NotNull
  private Long memberId;

  @NotNull
  private Long productId;

  @NotNull
  @NegativeOrZero
  private Long count;
}
