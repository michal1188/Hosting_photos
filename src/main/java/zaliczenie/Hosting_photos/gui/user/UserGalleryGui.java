package zaliczenie.Hosting_photos.gui.user;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.PushConfiguration;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Parameter;
import zaliczenie.Hosting_photos.model.Galleria;
import zaliczenie.Hosting_photos.model.Image;
import zaliczenie.Hosting_photos.model.Opis;
import zaliczenie.Hosting_photos.service.GalleryUploader;
import zaliczenie.Hosting_photos.service.ImageUploader;
import zaliczenie.Hosting_photos.service.OpisUploader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Route("user/gallery")
public class UserGalleryGui extends VerticalLayout{
    private ImageUploader imageUploader;
    private GalleryUploader galleryUploader;
    private OpisUploader opisUploader;


@Autowired
    public UserGalleryGui(ImageUploader imageUploader, GalleryUploader galleryUploader,OpisUploader opisUploader) {
        this.imageUploader=imageUploader;
        this.galleryUploader = galleryUploader;
        this.opisUploader =opisUploader;

        List<Image>lista_zdjec=imageUploader.lista_zdjec();
        List<String> lista_gal_str = galleryUploader.lista_gallerii();
        List<Galleria> lista_gal = galleryUploader.lista_gallerii2();

        Select<String> select = new Select<>();
        select.setLabel("Lista Galerii");
        select.setItems(lista_gal_str);

        select.addValueChangeListener(event -> {
            removeAll();
            add(select);
            AtomicInteger id = new AtomicInteger();
            String value1;
            value1 = event.getValue();

            for (int i = 0; i < lista_gal_str.size(); i++) {
                if (value1 == lista_gal_str.get(i)) {
                    id.set(i);
                }
            }
        Galleria galleria = lista_gal.get(id.get());

            List<Image>collect= lista_zdjec.stream()
                    .filter(image -> image.getGalleria().getId()== galleria.getId())
                    .collect(Collectors.toList());

            for(int i=0;i<collect.size();i++) {
                com.vaadin.flow.component.html.Image image1 = new com.vaadin.flow.component.html.Image(collect.get(i).getImageAdress(), "nie ma obrazka :(");
                add(image1);
                Long a=collect.get(i).getId();
                List<Opis> ops =opisUploader.lista_ops().stream().filter(opi -> opi.getImage().getId()==a).collect(Collectors.toList());
                add(new Label(ops.get(0).toString()));

                add(new Label("Galeria:  " + collect.get(i).getGalleria().getNazwa()));

            }
            Anchor logout = new Anchor("logout", "Log out");
            add(logout);
        });
    Anchor logout = new Anchor("logout", "Log out");
    add(logout);
        add(select);
    }
}
/*
@Route("user/gallery")
public class UserGalleryGui extends VerticalLayout implements HasUrlParameter<String> {
    private ImageUploader imageUploader;


    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        Location location = event.getLocation();
        QueryParameters queryParameters = location
                .getQueryParameters();
        Map<String, List<String>> parametersMap = queryParameters.getParameters();
    }
    public String setParameter1(BeforeEvent event, @OptionalParameter String parameter) {
        Location location = event.getLocation();
        QueryParameters queryParameters = location
                .getQueryParameters();
        Map<String, List<String>> parametersMap = queryParameters.getParameters();
        return parametersMap.get("galeria").toString();
    }
//    String str = setParameter1();
    public UserGalleryGui(ImageUploader imageUploader) {
        this.imageUploader = imageUploader;

        Label label = new Label("Zdjęcia z galerii");
        Anchor logout = new Anchor("logout", "Log out");
        add(label);
        List<Image> lista_zdj = imageUploader.lista_zdjec();
        for (int i = 0; i < lista_zdj.size(); i++) {

            Label label1 = new Label();
            Label label2 = new Label();
            com.vaadin.flow.component.html.Image image = new com.vaadin.flow.component.html.Image(lista_zdj.get(i).getImageAdress(), "BRAK");
            label1.setText("Opis :" + lista_zdj.get(i).getOpis());
            label2.setText("Galeria :" + lista_zdj.get(i).getGalleria().getNazwa());
            add(image);
            add(label1);
            add(label2);
        }

        add(logout);
    }


}*/