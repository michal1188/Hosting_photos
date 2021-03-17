package zaliczenie.Hosting_photos.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zaliczenie.Hosting_photos.model.AppUser;

@Repository
public interface AppUserRepo  extends JpaRepository<AppUser,Long > {
    AppUser findByUsername(String username);
}
