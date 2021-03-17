package zaliczenie.Hosting_photos.model;

import javafx.event.Event;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.dao.DataAccessException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



@Entity
public class Galleria {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String nazwa;

    @OneToMany (mappedBy = "galleria", orphanRemoval = true, cascade={CascadeType.ALL}, targetEntity = Image.class)
    private List<Image> images;

    public Galleria() {
    }

    public Galleria(  String nazwa) {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void addImage(Image image) {
        if ( image == null)
            images = new ArrayList<>();
        image.setGalleria(this);
        images.add(image);
    }


}
