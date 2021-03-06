package com.rubypaper.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity  //시큐리티 설정 파일임을 의미
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private BoardUserDetailsService boardUserDetailsService;

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        //각 주소별 권한 설정
        security.authorizeRequests().antMatchers("/", "/system/**","/h2-console/**","/profile").permitAll();
        security.authorizeRequests().antMatchers("/member/**").authenticated(); // 로그인 되면 접근 가능한 페이지
        security.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER");
        //security.authorizeRequests().antMatchers("/manager/**").hasRole("ADMIN");
        security.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

        //csrf 는 크로스사이트 위조 처리 관련 > restFull 을 사용하기 위해서는 disable()
        security.csrf().disable();

        //h2 콘솔사용 가능하게 설정
        security.headers().frameOptions().disable();

        //security.formLogin(); // 허용되지 않은 주소 접근시 디폴트 로그인 화면 보임
        //커스텀 로그인
        security.formLogin().loginPage("/login").defaultSuccessUrl("/loginSuccess", true); //사용자가 만든 로그인 보임
        security.exceptionHandling().accessDeniedPage("/accessDenied");  //접근 권한 없는 페이지로 이동할 경우 이동
        security.logout().invalidateHttpSession(true).logoutSuccessUrl("/login"); //로그아웃시 이동하는 주소

        //jpa 연동하기 - 주석 풀 경우, 하단의 authenticate 메서드 2개는 주석 처리해야 함
        //메모리인증/일반디비 인증 테스트 후, 디비는 truncate 처리 후 PasswordEncoderTest 를 돌려서 멤버 등록 후 테스트 진행해야 함
		security.userDetailsService(boardUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    //메모리 인증 -  콘솔에 찍힌 패스워드 말고 로그인 가능
/*    @Autowired
    public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("member")
                .password("{noop}member123")
                .roles("MEMBER");

        auth.inMemoryAuthentication()
                .withUser("manager")
                .password("{noop}manager123")
                .roles("MANAGER");

        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}admin123")
                .roles("ADMIN");
    }*/


    //437 직접 데이터베이스를 이용한 로그인 ============================================
	/*@Autowired
	private DataSource dataSource;
	@Autowired
	public void authenticate(AuthenticationManagerBuilder auth) throws Exception{
		//String query1 = "select id username, concat('{noop}', password) password, true enabled from member where id=?";
		String query1 = "select id username, password, true enabled from member where id=?";
		String query2 = "select id, role from member where id=?";
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery(query1)
		.authoritiesByUsernameQuery(query2);
	}*/

}
