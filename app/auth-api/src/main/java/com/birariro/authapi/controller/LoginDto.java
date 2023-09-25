package com.birariro.authapi.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
class LoginDto{
  private String id;
  private String pwd;
}