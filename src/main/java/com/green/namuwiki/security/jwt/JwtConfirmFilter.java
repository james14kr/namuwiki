package com.green.namuwiki.security.jwt;

import com.green.namuwiki.member.dto.MemberDTO;
import com.green.namuwiki.security.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// 클라이언트가 서버로 요청 시 토큰을 검증하는 Filter
//OncePerRequestFilter 클래스 : 요청시 무조건 한번 실행되는 필터 기능을 가진 클래스
@RequiredArgsConstructor
public class JwtConfirmFilter extends OncePerRequestFilter {
  private final JwtUtil jwtUtil;

  //  모든 요청마다 무조건 한번 실행하는 메서드
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    System.out.println("JwtConfirmFilter 클래스의 doFilterInternal() 메서드 실행");

//    토큰 있는지 없는지 검사, 만료 여부도 검사
//    요청 헤더에 담긴 토큰 정보를 받기
    String authorization = request.getHeader("Authorization");

//    Authorization 정보가 없거나, 토큰이 Bearer로 시작하지 않으면
    if (authorization == null || ! authorization.startsWith("Bearer ")){
      System.out.println("토큰 정보가 없습니다.");

      //  필터 진행 후 다음 코드 이어서 진행하는 명령어
      filterChain.doFilter(request, response);

      return;
    }

//    요청 헤더에서 받아온 토큰 정보에서 Bearer 키위드를 제거
    String token = authorization.split(" ")[1];
    boolean isExpired = jwtUtil.isExpired(token);

//    만약 토큰 만료기간이 지났으면
    if (isExpired){
      System.out.println("토큰 만료");
      filterChain.doFilter(request, response);
      return;
    }

//    아래 코드는 토큰이 정상적일 때 실행
//    아래 전체 코드는 토큰이 존재하는 회원을 spring 한테 임시적으로 로그인 했다고 알려주는 코드
    System.out.println("토큰 검증 성공");

    //토큰에서 username과 role 획득
    String username = jwtUtil.getUsername(token);
    String role = jwtUtil.getRole(token);

    //userEntity를 생성하여 값 set
    MemberDTO member = new MemberDTO();
    member.setMemEmail(username);
    member.setMemRole(role);

    //UserDetails에 회원 정보 객체 담기
    CustomUserDetails customUserDetails = new CustomUserDetails(member);

    //스프링 시큐리티 인증 토큰 생성
    Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

    //세션에 사용자 저장 . 일시적으로 세션에 사용자 정보를 저장하는 이유는 유저의 권한 체크 때문이다.
    SecurityContextHolder.getContext().setAuthentication(authToken);

    filterChain.doFilter(request, response);
  }
}
