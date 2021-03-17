package zaliczenie.Hosting_photos.gui.admin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import zaliczenie.Hosting_photos.model.Galleria;
import zaliczenie.Hosting_photos.model.Opis;
import zaliczenie.Hosting_photos.service.GalleryUploader;
import zaliczenie.Hosting_photos.service.ImageUploader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Route("admin/dodaj_obrazek")
public class UploadGui extends VerticalLayout {
    private ImageUploader imageUploader;
    private GalleryUploader galleryUploader;

    @Autowired
    public UploadGui(ImageUploader imageUploader, GalleryUploader galleryUploader) {

        this.galleryUploader = galleryUploader;
        this.imageUploader = imageUploader;
        Label label = new Label();


        List<String> lista = galleryUploader.lista_gallerii();
        List<Galleria> lista2 = galleryUploader.lista_gallerii2();
        Select<String> select = new Select<>();
        select.setLabel("Lista Galerii");
        select.setItems(lista);
        AtomicInteger id = new AtomicInteger();


        select.addValueChangeListener(event -> {
                    String value1;
                    value1 = event.getValue();

                    for (int i = 0; i < lista.size(); i++) {
                        if (value1 == lista.get(i)) {
                            id.set(i);
                        }
                    }

                }
        );


        TextField textField = new TextField();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();
        textField4.setLabel("Miejsce zdjęcia");
        textField3.setLabel("Autor");
        textField2.setLabel("Opis");
        textField.setLabel("ścieżka");
        Button button = new Button("Dodaj obrazek");


        button.addClickListener(buttonClickEvent -> {
                    String uploadedimage = imageUploader.uploadFileAndSaveToDb(textField.getValue());
                    Image image = new Image(uploadedimage, "nie ma obrazka :(");
                    String opis = textField2.getValue();
                    String autor = textField3.getValue();
                    String miejsce = textField4.getValue();
                    Opis opis1=new Opis(opis, autor, miejsce);
                    List<Opis> ops = new ArrayList<>();
                    ops.add(opis1);

                    zaliczenie.Hosting_photos.model.Image image2 = new zaliczenie.Hosting_photos.model.Image(uploadedimage, ops);
                    opis1.setImage(image2);
                    Galleria galleria = lista2.get(id.get());
                    image2.setGalleria(galleria);
                    label.setText("Udało sie zaimportować obraz");
                    add(label);
                    add(image);
                    imageUploader.save(image2);


                }
        );


        Anchor logout = new Anchor("logout", "Log out");
        add(textField);
        add(textField2);
        add(textField3);
        add(textField4);
        add(select);
        add(button);
        add(logout);
    }
}
