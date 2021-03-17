package zaliczenie.Hosting_photos.model;

import com.vaadin.flow.component.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Image  implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String imageAdress;

    @ManyToOne
    @JoinColumn(name = "galleria_id")
    private Galleria galleria;


    @OneToMany (mappedBy = "image", orphanRemoval = true, cascade={CascadeType.ALL}, targetEntity = Komentarz.class)
    private List<Komentarz> komentarze;

    @OneToMany (mappedBy = "image", orphanRemoval = true, cascade={CascadeType.ALL}, targetEntity = Opis.class)
    private List<Opis> opis;

    public Image(String imageAdress, List<Opis>  opis) {
        this.imageAdress=imageAdress;
        this.galleria=galleria;
        this.opis=opis;

    }

    public List<Komentarz> getKomentarze() {
        return komentarze;
    }

    public void setKomentarze(List<Komentarz> komentarze) {
        this.komentarze = komentarze;
    }

    public Image(String imageAdress) {
    }
    public Image() {
    }
public String daj_nazwe_galerii(){
       return getGalleria().getNazwa();

}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageAdress() {
        return imageAdress;
    }

    public void setImageAdress(String imageAdress) {
        this.imageAdress = imageAdress;
    }

    public Galleria getGalleria() {
        return galleria;
    }

    public void setGalleria(Galleria galleria) {
        this.galleria = galleria;
    }

    public List<Opis> getOpis2(){return opis;}
    public String getOpis() {
        return opis.get(0).getOpis();
    }
    public String getAutor() { return opis.get(0).getAutor(); }
    public String getMiejsce() { return opis.get(0).getMiejsce_zdjecia(); }


    public void setOpis(List<Opis> opis) {
        this.opis = opis;
    }

    public void addKomentarz(Komentarz komentarz) {
        if (komentarze == null)
            komentarze = new ArrayList<>();
        komentarz.setImage(this);
        komentarze.add(komentarz);
    }

}
