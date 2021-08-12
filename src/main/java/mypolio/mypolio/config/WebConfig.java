//package mypolio.mypolio.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//@Configuration
//@EnableWebSecurity
//public class WebConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .httpBasic()
//                .and()
//                .exceptionHandling()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/user/signup").permitAll()
//                .antMatchers("/user/login").permitAll()
//                .antMatchers("/user/verify/**").permitAll()
//                .antMatchers("/oauth/**").permitAll()
//                .antMatchers("/test/user").hasRole("USER")
//                .antMatchers("/test/admin").hasRole("ADMIN")
//                .anyRequest().authenticated();
//    }
//}