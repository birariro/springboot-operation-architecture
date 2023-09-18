package com.birariro.authapi;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.birariro.authapi.domain.MemberRepository;

import lombok.RequiredArgsConstructor;

@RestController
@Transactional
@RequiredArgsConstructor
public class AuthController {

  private final MemberRepository memberRepository;

  @PostMapping("/login")
  public ResponseEntity login(){

    return ResponseEntity.ok().build();
  }
}
