package autorizador.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class SecurityConfig extends ResourceServerConfigurerAdapter {


    @Override
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors().and() // responsavel por desabilitar o cors
                .csrf().disable()
                .authorizeRequests()
                //.antMatchers("/cartoes/**").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .and()
                .httpBasic();

    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // do not require a resource id in AccessToken.
        resources.resourceId(null);
    }
}