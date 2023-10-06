package com.birariro.orderapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.birariro.orderapi.controller.OrderController;
import com.birariro.orderapi.controller.OrderRequest;
import com.birariro.orderapi.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private OrderService orderService;

  @Test
  @DisplayName("주문 요청시 count 가 0 이거나 음수 일 수 없다.")
  public void countNotPositive() throws Exception {

    OrderRequest content = OrderRequest.builder().memberId(1L).productId(1L).count(0L).build();

    mockMvc.perform(MockMvcRequestBuilders.post("/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(content)))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  @DisplayName("주문 요청시 모든 요청값이 양수이면 성공.")
  public void success() throws Exception {

    OrderRequest content = OrderRequest.builder().memberId(1L).productId(1L).count(100L).build();

    mockMvc.perform(MockMvcRequestBuilders.post("/order")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(content)))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
  }

}
