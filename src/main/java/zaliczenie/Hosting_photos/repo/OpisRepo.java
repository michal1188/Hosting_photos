package zaliczenie.Hosting_photos.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import zaliczenie.Hosting_photos.model.Komentarz;
import zaliczenie.Hosting_photos.model.Opis;

public interface OpisRepo extends JpaRepository<Opis,Long> {
}
