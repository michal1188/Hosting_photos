package zaliczenie.Hosting_photos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zaliczenie.Hosting_photos.model.AppUser;
import zaliczenie.Hosting_photos.repo.AppUserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private AppUserRepo appUserRepo;


    @Autowired
    public UserDetailsServiceImpl(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return appUserRepo.findByUsername(s);

    }


    public String get_active(Object principal){
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

}
