package zaliczenie.Hosting_photos.gui.user;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import zaliczenie.Hosting_photos.model.Galleria;
import zaliczenie.Hosting_photos.model.Image;
import zaliczenie.Hosting_photos.service.GalleryUploader;
import zaliczenie.Hosting_photos.service.ImageUploader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route("user")


public class UserGui extends VerticalLayout {
    private GalleryUploader galleryUploader;

    @Autowired
    public UserGui(GalleryUploader galleryUploader) {
        this.galleryUploader = galleryUploader;

        Button button_galery= new Button("Zobacz zdjęcia z konkretnej galerii");
        Button button_photo= new Button("Zobacz zdjęcie i komentuj z innymi użytkownikami ");

        button_galery.addClickListener(buttonClickEvent -> UI.getCurrent().navigate("user/gallery")  );
        button_photo.addClickListener(buttonClickEvent -> UI.getCurrent().navigate("user/photo")  );
        add(button_photo);
        add(button_galery);
     /*
        List<Galleria> lista=galleryUploader.lista_gallerii2();
        Label label= new Label("Lista Galerii");
        add(label);
        lista.forEach(galeria->{
            Label label2= new Label();
            Button button= new Button("Wybierz");
            Map<String, List<String>> parameters = new HashMap<String, List<String>>();
            List<String> wybrana= new ArrayList<>();
            wybrana.add(galeria.getNazwa());
            parameters.put("galeria",wybrana);
            QueryParameters queryParameters = new QueryParameters(parameters);
            button.addClickListener( e-> {
                button.getUI().ifPresent(ui -> ui.navigate("user/gallery", queryParameters));
            });
            label2.setText(galeria.getNazwa());

            add(label2);
            add(button);

        });

        */
        Anchor logout = new Anchor("logout", "Log out");
        add(logout);


    }

}
