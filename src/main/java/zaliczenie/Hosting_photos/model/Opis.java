package zaliczenie.Hosting_photos.model;

import javax.persistence.*;

@Entity
public class Opis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String opis;

    private  String autor;

    private  String miejsce_zdjecia;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;

    public Opis() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getMiejsce_zdjecia() {
        return miejsce_zdjecia;
    }

    public void setMiejsce_zdjecia(String miejsce_zdjecia) {
        this.miejsce_zdjecia = miejsce_zdjecia;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Opis(String opis, String autor, String miejsce_zdjecia) {
        this.autor=autor;
        this.opis=opis;
        this.miejsce_zdjecia=miejsce_zdjecia;
    }

    @Override
    public String toString() {
        return "Opis: '" + opis + '\'' +
                ", Autor: '" + autor + '\'' +
                ", Miejsce: '" + miejsce_zdjecia + '\'';
    }
}
