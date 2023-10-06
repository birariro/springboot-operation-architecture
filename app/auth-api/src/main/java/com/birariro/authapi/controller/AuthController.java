package com.birariro.authapi.controller;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.birariro.authapi.controller.LoginDto;
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

  /**
   * 인증, 인가, 토큰 같은 비지니스는 생략한다
   * 데이터 베이스에 있는 정보만 넘기면 성공
   */
  private final MemberRepository memberRepository;

  @PostMapping("/login")
  public ResponseEntity login(@Valid @RequestBody LoginDto loginDto)  {

    log.debug("login request : ", loginDto);

    Optional<Member> memberActor = memberRepository.findByLoginId(loginDto.getId());

    if(!memberActor.isPresent())
      return ResponseEntity.notFound().build();

    Member member = memberActor.get();

    if(!member.match(loginDto.getPwd())){
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().body(member);
  }

  @GetMapping("/check")
  public ResponseEntity check(@RequestParam("id") Long id){

    log.debug("member check id : " + id);

     memberRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException());

     return ResponseEntity.ok().build();
  }

}
