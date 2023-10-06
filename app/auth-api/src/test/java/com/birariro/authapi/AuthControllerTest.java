package com.birariro.authapi;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.birariro.authapi.controller.AuthController;
import com.birariro.authapi.controller.LoginDto;
import com.birariro.authapi.domain.Member;
import com.birariro.authapi.domain.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(AuthController.class)
public class AuthControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MemberRepository memberRepository;

  private ObjectMapper objectMapper = new ObjectMapper();

  @Test
  @DisplayName("id 에는  null, 빈값, 공백 을 넘길 수 없다")
  public void checkIdParamNull() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.post("/login")
        .content(objectMapper.writeValueAsString(new LoginDto(null, "pwd")))
        .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    mockMvc.perform(MockMvcRequestBuilders.post("/login")
            .content(objectMapper.writeValueAsString(new LoginDto("", "pwd")))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    mockMvc.perform(MockMvcRequestBuilders.post("/login")
            .content(objectMapper.writeValueAsString(new LoginDto("   ", "pwd")))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  @DisplayName("pwd 에는  null, 빈값, 공백 을 넘길 수 없다")
  public void checkPwdParamNull() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.post("/login")
            .content(objectMapper.writeValueAsString(new LoginDto("id", null)))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    mockMvc.perform(MockMvcRequestBuilders.post("/login")
            .content(objectMapper.writeValueAsString(new LoginDto("id", "")))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    mockMvc.perform(MockMvcRequestBuilders.post("/login")
            .content(objectMapper.writeValueAsString(new LoginDto("id", "   ")))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  @DisplayName("id 에 해당하는 유저가 없는경우")
  public void notMember() throws Exception {

    Mockito.when(memberRepository.findByLoginId("id")).thenReturn(Optional.ofNullable(null));

    String content = new ObjectMapper().writeValueAsString(new LoginDto("id", "pwd"));

    mockMvc.perform(MockMvcRequestBuilders.post("/login")
            .content(content)
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  @DisplayName("패스워드가 일치하지 않는 경우")
  public void notMatchPwd() throws Exception {

    Mockito.when(memberRepository.findByLoginId("id"))
        .thenReturn(Optional.ofNullable(new Member("id","notpwd","name")));

    String content = new ObjectMapper().writeValueAsString(new LoginDto("id", "pwd"));

    mockMvc.perform(MockMvcRequestBuilders.post("/login")
            .content(content)
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }
}
