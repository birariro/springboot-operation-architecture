package com.birariro.orderapi.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthClient {

  public boolean validMember(Long memberId){

    String url = "http://host.docker.internal:11801/check?id=" + memberId;
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Object> forEntity = restTemplate.getForEntity(url, Object.class);

    HttpStatus statusCode = forEntity.getStatusCode();
    log.debug("checkMember response http statue code : "+ statusCode);

    return statusCode.is2xxSuccessful();
  }
}
