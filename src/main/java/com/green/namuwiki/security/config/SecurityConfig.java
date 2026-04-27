package com.green.namuwiki.security.config;

import com.green.namuwiki.security.jwt.JwtConfirmFilter;
import com.green.namuwiki.security.jwt.JwtUtil;
import com.green.namuwiki.security.jwt.LoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

// 인증 및 인가에 대한 설정 내용을 작성하는 클래스

@Configuration // 객체 생성 + 이 클래스는 설정내용을 작성된 파일임을 인지시킴
@EnableWebSecurity // 해당 파일이 security 설정 파일이며, security 설정을 활성화 시키겠다는 의미!
@RequiredArgsConstructor

// Controller 각 메서드 위에 어노테이션으로 인가 관리를 할 수 있게 세팅하는 어노테이션임!
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {
  private final JwtUtil jwtUtil;

  // 인증 및 인가 설정을 작성하는 메서드
  // 해당 메서드는 리턴타입과 매개변수는 정해져 있음!
  @Bean //메서드 실행 결과를 객체형태로 리턴
  public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration authConfig) throws Exception {
    // 로그인 처리를 실제 담당하는 authenticationManager 객체를 생성
    AuthenticationManager authenticationManager = authConfig.getAuthenticationManager();

    http
            // 로그인 인증절차(로그인 처리) 사용 안 할거야! 하는 거임!
            //CORS 설정. 아래 corsConfigurationSource() 메서드에서 정의한 Bean을 등록함.
            .cors(Customizer.withDefaults())
            //csrf disable  세션방식이 아니기 때문에 할 필요 없음
            .csrf(csrf -> csrf.disable())
            //form 로그인 방식 disable
            .formLogin(form -> form.disable())
            //http basic 인증 방식 disable
            .httpBasic(basic -> basic.disable())
            //세션을 STATELESS로 지정
            .sessionManagement(
                    session ->
                            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            // ★ 인증 및 인가에 대한 접근 설정 ★
            .authorizeHttpRequests(auth ->
                    auth.requestMatchers("/ws/**", "/ws/info").permitAll() // WebSocket 경로 허용 (dm 관련)
                    .anyRequest().permitAll() // 인증 및 인가를 받지 않아도 모든 곳에 접근 가능!!!
            );
    // 기존 로그인 처리를 담당하는 UsernamePasswordAuthenticationFilter를 우리가 만든 LoginFilter클래스로 대체
    http.addFilterAt(new LoginFilter(authenticationManager, jwtUtil), UsernamePasswordAuthenticationFilter.class);

//    로그인 검증 필터 전에 토큰 유무를 판단하는 필터를 추가
    http.addFilterBefore(new JwtConfirmFilter(jwtUtil), LoginFilter.class);

    return http.build();
  }

  //CORS 설정 Bean
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true); //쿠키 정보를 통신하기 위한 설정
    config.addAllowedOrigin("http://localhost:5173"); //리액트에서의 요청 허용
    config.addAllowedOrigin("http://localhost:8080"); //dm 관련 요청 허용
    config.addAllowedOrigin("http://192.168.30.77:5173");
    config.addAllowedOrigin("http://192.168.30.109:5173");
    config.addAllowedOrigin("http://192.168.30.116:5173");
    config.setAllowedOriginPatterns(List.of("*"));
    config.addAllowedHeader("*"); //모든 헤더 정보 허용
    config.addAllowedMethod("*"); //get, post, delete, put 등의 요청 메서드 허용

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
  }

  //비밀번호 암호화 기능을 제공하는 객체
  @Bean
  public PasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder(); // 어떤 문자열을 암호화 시키는 것
  }

}
