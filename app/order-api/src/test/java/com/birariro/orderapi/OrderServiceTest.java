package com.birariro.orderapi;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.birariro.orderapi.domain.OrderRepository;
import com.birariro.orderapi.domain.ProductRepository;
import com.birariro.orderapi.service.AuthClient;
import com.birariro.orderapi.service.OrderService;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

  @Mock
  private ProductRepository productRepository;
  @Mock
  private OrderRepository orderRepository;
  @Mock
  private AuthClient authClient;

  private OrderService orderService;

  @BeforeEach
  public void init(){
    orderService = new OrderService(productRepository,orderRepository,authClient);
  }


  @Test
  @DisplayName("member id 가 null 일 수 없다.")
  public void memberIdNotNull(){
    Assertions.assertThrows(RuntimeException.class, () -> {
      orderService.save(null,10L,10L);
    });
  }
  @Test
  @DisplayName("product id 가 null 일 수 없다.")
  public void productIdNotNull(){
    Assertions.assertThrows(RuntimeException.class, () -> {
      orderService.save(10L,null,10L);
    });
  }

  @Test
  @DisplayName("count 가 null 일 수 없다.")
  public void countNotNull(){
    Assertions.assertThrows(RuntimeException.class, () -> {
      orderService.save(10L,10L,null);
    });
  }
  @Test
  @DisplayName("count 가 음수 혹은 0 일 수 없다.")
  public void countNotNegativeOrZero(){
    Assertions.assertThrows(RuntimeException.class, () -> {
      orderService.save(10L,10L,-1L);
    });
    Assertions.assertThrows(RuntimeException.class, () -> {
      orderService.save(10L,10L,0L);
    });
  }

  @Test
  @DisplayName("존재 하지 않는 맴버")
  public void existNotMember(){

    long memberId = 1000L;

    Mockito.when(authClient.validMember(memberId)).thenReturn(false);

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      orderService.save(memberId,10L,10L);
    });
  }

  @Test
  @DisplayName("존재 하지 않는 상품")
  public void existNotProduct(){

    long memberId = 1000L;
    long productId = 10L;

    Mockito.when(authClient.validMember(memberId)).thenReturn(true);
    Mockito.when(productRepository.findById(productId)).thenReturn(Optional.ofNullable(null));

    Assertions.assertThrows(NotFoundException.class, () -> {
      orderService.save(memberId, productId,10L);
    });
  }
}
