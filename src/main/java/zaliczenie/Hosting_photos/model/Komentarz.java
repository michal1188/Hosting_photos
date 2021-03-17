package zaliczenie.Hosting_photos.model;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Komentarz {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String tresc;


    private  String user;

    private LocalDate data;
    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;


    public  Komentarz(String tresc, String user, LocalDate data, Image image){


        this.tresc=tresc;
        this.user=user;
        this.data=data;
        this.image=image;
    }

    public Komentarz() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}