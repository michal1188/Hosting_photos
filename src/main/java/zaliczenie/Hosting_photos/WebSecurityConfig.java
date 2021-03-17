package zaliczenie.Hosting_photos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import zaliczenie.Hosting_photos.model.AppUser;
import zaliczenie.Hosting_photos.repo.AppUserRepo;
import zaliczenie.Hosting_photos.service.UserDetailsServiceImpl;

@Configuration
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {


    private UserDetailsServiceImpl userDetailsService;
    private AppUserRepo appUserRepo;


    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService,AppUserRepo appUserRepo) {
        this.userDetailsService = userDetailsService;
        this.appUserRepo = appUserRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //   auth.inMemoryAuthentication().withUser(new User("Michal", passwordEncoder().encode("Michal123"), Collections.singleton(new SimpleGrantedAuthority("user"))));
    auth.userDetailsService(userDetailsService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/admin/dodaj_galerie").hasRole("ADMIN")
                .antMatchers("/admin/dodaj_obrazek").hasRole("ADMIN")
                .antMatchers("/admin/lista_galerii").hasRole("ADMIN")
                .antMatchers("/admin/ListaZdjec").hasRole("ADMIN")
                .antMatchers("/user").permitAll()
                .antMatchers("/user/galery").permitAll()
                .and()
                .formLogin().permitAll()
                .and()
                .csrf().disable()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {return  new BCryptPasswordEncoder();
    }


//   @EventListener(ApplicationReadyEvent.class)
    public void get(){

        AppUser appUserUser=new AppUser("Michal", passwordEncoder().encode("Kowal"),"ROLE_USER");
        AppUser appUserAdmin=new AppUser("Adam", passwordEncoder().encode("123"),"ROLE_ADMIN");
        appUserRepo.save(appUserUser);
       appUserRepo.save(appUserAdmin);

    }

}
