package zaliczenie.Hosting_photos.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import zaliczenie.Hosting_photos.model.Galleria;
import zaliczenie.Hosting_photos.model.Image;

public interface GalleryRepo extends JpaRepository<Galleria,Long> {



}
