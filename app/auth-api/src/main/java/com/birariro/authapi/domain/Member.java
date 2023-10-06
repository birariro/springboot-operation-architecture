package com.birariro.authapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tb_member")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "login_id")
  private String loginId;
  @Column(name = "login_pwd")
  private String loginPWD;
  @Column(name = "nick_name")
  private String nickname;

  public Member(String loginId, String loginPWD, String nickname) {
    this.loginId = loginId;
    this.loginPWD = loginPWD;
    this.nickname = nickname;
  }

  public boolean match(String pwd){
    return loginPWD.equals(pwd);
  }
}
