package zaliczenie.Hosting_photos.gui.user;

import com.sun.javaws.JAuthenticator;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import zaliczenie.Hosting_photos.model.*;
import zaliczenie.Hosting_photos.model.Image;
import zaliczenie.Hosting_photos.repo.KomentarzRepo;
import zaliczenie.Hosting_photos.service.*;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Route("user/photo")
public class PhotoGui extends VerticalLayout {

    private ImageUploader imageUploader;
    private GalleryUploader galleryUploader;
    // AppUser appUser;
    private KomentarzUploader komentarzUploader;
    private UserDetailsServiceImpl userDetailsService;
    private OpisUploader opisUploader;


    @Autowired
    public PhotoGui(ImageUploader imageUploader, GalleryUploader galleryUploader, KomentarzUploader komentarzUploader,OpisUploader opisUploader, UserDetailsServiceImpl userDetailsService) {
        this.opisUploader=opisUploader;
        this.komentarzUploader=komentarzUploader;
        this.imageUploader = imageUploader;
        this.galleryUploader = galleryUploader;
        this.userDetailsService=userDetailsService;
        // this.appUser=appUser;

        Button button = new Button("Dodaj ");

        List<Image> lista_zdjec=imageUploader.lista_zdjec();
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

            List<Long>lista_id= new ArrayList<>();
            for (Image image:collect){
                lista_id.add(image.getId());
            }

            Select<Long> select2 = new Select<>();
            select2.setLabel("Lista Zdjęć");
            select2.setItems(lista_id);
            add(select2);
            select2.addValueChangeListener(event1 -> {
                removeAll();
                add(select);
                add(select2);
                AtomicInteger id2 = new AtomicInteger();
                List<Image>wybrane_zdj=collect.stream()
                        .filter(image -> image.getId()==select2.getValue())
                        .collect(Collectors.toList());


                com.vaadin.flow.component.html.Image ima = new com.vaadin.flow.component.html.Image(wybrane_zdj.get(0).getImageAdress(),"nie ma obrazka :(");
                List<Image> obrazz = lista_zdjec.stream()
                        .filter(image -> image.getId()== select2.getValue().intValue())
                        .collect(Collectors.toList());
                List<Opis> ops = opisUploader.lista_ops().stream().filter(opi -> opi.getImage().getId()== obrazz.get(0).getId())
                        .collect(Collectors.toList());
                add(new Label(ops.get(0).toString()));
                TextArea textArea= new TextArea();
                textArea.setLabel("Dodaj swój komentarz");


                button.addClickListener(buttonClickEvent -> {
                    removeAll();
                    add(select);
                    add(select2);
                    add(ima);
                    add(textArea);
                    add(button);
                    Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    List<Image> obraz = lista_zdjec.stream()
                            .filter(image -> image.getId()== select2.getValue().intValue())
                            .collect(Collectors.toList());
                    Komentarz komentarz= new Komentarz(textArea.getValue(),userDetailsService.get_active(principal), LocalDate.now(),obraz.get(0) );
                    if(komentarz.getTresc()!=""){
                        komentarzUploader.save(komentarz);}
                    textArea.clear();

                    List<Komentarz>koment=komentarzUploader.lista_kom().stream()
                            .filter(komentarz1 -> komentarz1.getImage().getId()==select2.getValue())
                            .collect(Collectors.toList());
                    for(Komentarz kom:koment){
                        add(new Label(kom.getUser() +" : "+kom.getTresc()+"\n"+kom.getData()));

                    }
//
                    //UI.getCurrent().getPage().reload();
                });


                add(ima);
                add(textArea);
                add(button);
                List<Komentarz>koment=komentarzUploader.lista_kom().stream()
                        .filter(komentarz1 -> komentarz1.getImage().getId()==select2.getValue())
                        .collect(Collectors.toList());
                for(Komentarz kom:koment){
                    add(new Label(kom.getUser() +" : "+kom.getTresc()+"\n"+kom.getData()));

                }
            });

            Anchor logout = new Anchor("logout", "Log out");
            add(logout);

        });
        add(select);
        Anchor logout = new Anchor("logout", "Log out");
        add(logout);


    }


}