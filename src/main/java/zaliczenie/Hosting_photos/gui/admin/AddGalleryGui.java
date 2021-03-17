package zaliczenie.Hosting_photos.gui.admin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import zaliczenie.Hosting_photos.model.Galleria;
import zaliczenie.Hosting_photos.repo.ImageRepo;
import zaliczenie.Hosting_photos.service.GalleryUploader;

@Route("admin/dodaj_galerie")
public class AddGalleryGui   extends VerticalLayout {
    private GalleryUploader galleryUploader;

    @Autowired
    public AddGalleryGui( GalleryUploader galleryUploader) {
        this.galleryUploader=galleryUploader;
        Label label= new Label();
        TextField textField= new TextField();
        textField.setLabel("Nazwa galerii");
        Button button= new Button("Dodaj galerie");
        button.addClickListener(buttonClickEvent ->{
            Galleria galleria= new Galleria();
            galleria.setNazwa(textField.getValue());
            galleryUploader.save(galleria);
            label.setText("Udało się dodać galerie");
            add(label);
                }
        );

        Anchor logout = new Anchor("logout", "Log out");

        add(textField);
        add(button);
    add(logout);
    }


}
