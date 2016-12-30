package project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void registerAuthenticationManager(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(getShaPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] pagesForGrandAdmins = {"/showAdmins", "/addAdmin"};

        String[] pagesForAllAdmins = {"/admin", "/showCallbacks", "/showOrders", "/showServices", "/addService",
        "/showProductTypes", "/addProductType", "/showProductFirms", "/addProductFirm", "/showProducts", "/addProduct"};

        String[] pagesForAll = {"/", "/login"};

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(pagesForAll).permitAll()
                .antMatchers(pagesForAllAdmins).hasAnyRole("GRAND_ADMIN", "ADMIN")
                .antMatchers(pagesForGrandAdmins).hasAnyRole("GRAND_ADMIN")
                .and()
        .exceptionHandling().accessDeniedPage("/accessDenied")
                .and()
        .formLogin()
                .failureUrl("/login?error")
                .defaultSuccessUrl("/admin")
                .loginPage("/")
                .loginProcessingUrl("/j_spring_security_check")
                .usernameParameter("j_login")
                .passwordParameter("j_password")
                .permitAll()
                .and()
        .logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true);
    }

    @Bean
    public ShaPasswordEncoder getShaPasswordEncoder() {
        return new ShaPasswordEncoder();
    }
}
