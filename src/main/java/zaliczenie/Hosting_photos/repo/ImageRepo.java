package zaliczenie.Hosting_photos.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpMethod;
import zaliczenie.Hosting_photos.model.Image;

public interface  ImageRepo  extends JpaRepository<Image,Long>  {



}
