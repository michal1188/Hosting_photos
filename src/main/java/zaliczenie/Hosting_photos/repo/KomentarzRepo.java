package zaliczenie.Hosting_photos.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import zaliczenie.Hosting_photos.model.Image;
import zaliczenie.Hosting_photos.model.Komentarz;

public interface KomentarzRepo   extends JpaRepository<Komentarz,Long> {
}
