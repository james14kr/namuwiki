package com.green.namuwiki.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.namuwiki.member.dto.MemberDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Iterator;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
  /* 아주 중요한 객체!
   * 인증(로그인)을 처리하는 핵심 컴포넌트
   * 로그인 처리하는 기능있는 애 : AuthenticationManager
   * */
  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;

  // 생성자 의존성 주입 방식으로 멤버변수 authenticationManager를 생성
  public LoginFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil){
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;

    // 로그인 요청 url 변경
    setFilterProcessesUrl("/members/login");

    // 전달되는 아이디, 비번 key 값 변경
    setUsernameParameter("memEmail");
    setPasswordParameter("memPw");
  }


  // Spring Security의 기본 로그인 절차
  /*  1. 로그인 요청이 들어오면 UsernamePasswordAuthenticationFilter 클래스의
      attemptAuthentication 메서드가 실행되면서 로그인을 진행

  * 우리가 하고 싶은 방식 - JWT에서 토큰을 받아 로그인 진행
    1. 로그인 요청이 들어오면 LoginFilter 클래스의 attemptAuthentication() 메서드를 실행
    2. 등록을 해줘야 함!
        -> 우리가 만든 LoginFilter 클래스를 쓰겠다고 해야 함.
  * */

  //1. 로그인 요청이 들어오면 LoginFilter 클래스의 attemptAuthentication() 메서드를 실행
  @Override //부모에서 물려받은 메서드를 사용
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    System.out.println("LoginFilter 클래스의 attemptAuthentication() 메서드 실행");

    // 전달된 아이디 및 비번을 저장하기 위한 객체
    MemberDTO vo = new MemberDTO();

    // 로그인 요청 시 전달되는 아이디 및 비번을 받는 코드
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      //request : 요청에 대한 모든 정보를 가지고 있는 객체
      ServletInputStream inputStream = request.getInputStream();
      String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
      vo = objectMapper.readValue(messageBody, MemberDTO.class);
    } catch (IOException e){
      throw new RuntimeException(e);
    }

    // 전달받은 로그인 정보 확인
    System.out.println("전달받은 id : " + vo.getMemEmail());
    System.out.println("전달받은 pw : " + vo.getMemPw());

    // 전달받은 로그인 정보를 AuthenticationManager 객체에게 전달
    // 그러면 AuthenticationManager 객체로 로그인 검증을 시작

    // 1) 전달받은 로그인정보를 보안기능이 추가된 객체에 등록
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(vo.getMemEmail(), vo.getMemPw(), null);

    // 2) authenticationManager한테 로그인 정보가 담긴 상자(authToken) 전달
    //  = 보안기능이 빠방한 상자 안에 로그인 정보를 담아서 전달
    //    authenticationManager가 로그인 정보를 전달받으면 바로 로그인 검증 로직을 실행
    //    로그인 검증 로직을 실행하면 UserDetailsService 인터페이스 안에 정의된
    //    loadUserByUsername 메서드를 우선 호출한다.

    // 로그인 검증에 대한 결과 데이터가 authentication 객체에 담겨 있음
    Authentication authentication = authenticationManager.authenticate(authToken);

    // 검증 성공한 유저의 아이디(이메일)을 출력
    System.out.println("로그인 유저 : " + authentication.getName());

    // 로그인 검증 결과를 리턴
    // 결과에 따라 successfulAuthentication 혹은 unsuccessfulAuthentication 중 하나가 실행 됨.
    return authentication;
  }

  // 로그인 검증 성공 시 실행하는 메서드
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    System.out.println("로그인 검증 성공 - LoginFilter 클래스의 successfulAuthentication 메서드 실행");

//    토큰 생성 시 필요한 데이터(유저 이메일, 권한, 만료시간)
    //1. 로그인 검증 성공 유저의 이메일
    String userEmail = authResult.getName();
//  2. 로그인 검증 성공 유저의 권한
    Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
    Iterator <? extends GrantedAuthority> iterator = authorities.iterator();
    GrantedAuthority auth = iterator.next();
    String role = auth.getAuthority();

//    JWT 토큰을 생성
    String token = jwtUtil.createJwt(userEmail, role, 1000 * 60 * 120); //1000 = 1초

//    생성한 토큰을 응답 헤더에 담아 react에 전달
    response.setHeader("Access-Control-Expose-Headers", "Authorization");
    response.setHeader("Authorization", "Bearer " + token); // token쓸 때 암묵적인 약속으로 앞에 Bearer 붙임
    response.setStatus(HttpStatus.OK.value());
  }

  // 로그인 검증 실패 시 실행하는 메서드
  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
    System.out.println("로그인 검증 실패 - LoginFilter 클래스의 unsuccessfulAuthentication 메서드 실행");

    //401 : 인증 안됨
    response.setStatus(401);
//    response.setStatus(HttpStatus.UNAUTHORIZED.value()); // 위와 동일한 401
  }


}
