package footprints.footprints.controller.security;

import footprints.footprints.controller.security.jwt.JwtAuthenticationFilter;
import footprints.footprints.controller.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    // 암호화에 필요한 PasswordEncoder 를 Bean 등록합니다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // authenticationManager를 Bean 등록합니다.
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//        .headers().frameOptions().disable()
//        .and()
        .httpBasic().disable() // rest api 만을 고려하여 기본 설정은 해제하겠습니다.
        .csrf().disable() // csrf 보안 토큰 disable처리.
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증이므로 세션 역시 사용하지 않습니다.
        .and()
        .authorizeRequests() // 요청에 대한 사용권한 체크
            .antMatchers("/token/**").hasRole("USER")
//                .antMatchers("/token/**").hasRole("ADMIN")
//            .antMatchers("/delivery/post/**").hasRole("USER")
            .antMatchers("/delivery/post/new-post").hasRole("USER")
//            .antMatchers("/delivery/post/{post_id}/amend").hasRole("USER")
            .antMatchers("/user/**").hasRole("USER")
            .antMatchers("/chat/**").hasRole("USER")
            .antMatchers("/notice/new-notice").hasRole("ADMIN")
//            .antMatchers("/post/**").hasRole("USER")
            .anyRequest().permitAll() // 그외 나머지 요청은 누구나 접근 가능
        .and()
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),UsernamePasswordAuthenticationFilter.class);
// JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다
        http
        .exceptionHandling()
        .authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.sendRedirect("/forbidden");
            }
        });
    }
}