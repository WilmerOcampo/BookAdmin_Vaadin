package com.oc.book;

import com.oc.book.gui.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class BookApplication extends VaadinWebSecurity {

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    //@Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new InMemoryUserDetailsManager(
                User.withUsername("Wilmer").password("{noop}p").roles("ADMIN").build(),
                User.withUsername("Luz").password("{noop}p").roles("ADMIN").build()

        );
    }
}
