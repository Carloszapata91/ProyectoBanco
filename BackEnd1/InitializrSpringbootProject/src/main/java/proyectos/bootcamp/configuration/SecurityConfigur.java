
package proyectos.bootcamp.configuration;

import static javafx.scene.input.KeyCode.T;
import javax.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.web.servlet.function.RouterFunctions.resources;
import proyectos.bootcamp.service.Impl.UsuarioServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfigur extends WebSecurityConfigurerAdapter{
    
            String[] resources = new String[]{
            "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**"
            };



        @Autowired
	private UsuarioServiceImpl userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
        @Bean
	static BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
        }
	
	
	@Bean
	static PasswordEncoder passwordEncoder2() {
		//BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return NoOpPasswordEncoder.getInstance();
        }	

       @Override
        protected void configure(HttpSecurity security) throws Exception
        {
          security.httpBasic().disable();

        }

        //@Override
        protected void configuren(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
	        .antMatchers(resources).permitAll()  
	        .antMatchers("/","/login","/newUser","/user").permitAll()
	        .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/home")
                .failureUrl("/errorUsuarioContrasena") 
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/login");
    }

       
       
}
