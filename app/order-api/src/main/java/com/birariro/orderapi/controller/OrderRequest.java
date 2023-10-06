package com.birariro.orderapi.controller;

import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

  @NotNull
  @Positive
  private Long memberId;

  @NotNull
  @Positive
  private Long productId;

  @NotNull
  @Positive
  private Long count;
}
