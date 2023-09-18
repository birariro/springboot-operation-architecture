package com.birariro.authapi;


import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.birariro.authapi.domain.Member;
import com.birariro.authapi.domain.MemberRepository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@RestController
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AuthController {

  private final MemberRepository memberRepository;

  @PostMapping("/login")
  public ResponseEntity login(LoginDto loginDto)  {

    log.debug("login request : ", loginDto);

    Member member = memberRepository.findByLoginId(loginDto.id)
        .orElseThrow(() -> new IllegalArgumentException());


    return ResponseEntity.ok().body(member);
  }
  @GetMapping("/hello")
  public ResponseEntity hello()  {
    return ResponseEntity.ok().body("hello");
  }

  @NoArgsConstructor
  @Getter
  @ToString
  class LoginDto{
    private String id;
    private String pwd;
  }
}
