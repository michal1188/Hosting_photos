package zaliczenie.Hosting_photos.gui.admin;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Receiver;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
//import com.vaadin.server.Page;
import zaliczenie.Hosting_photos.model.Galleria;
import zaliczenie.Hosting_photos.model.Image;
import zaliczenie.Hosting_photos.model.Komentarz;
import zaliczenie.Hosting_photos.model.Opis;
import zaliczenie.Hosting_photos.service.GalleryUploader;
import zaliczenie.Hosting_photos.service.ImageUploader;
import zaliczenie.Hosting_photos.service.OpisUploader;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Route("admin/ListaZdjec")
public class ListaZdjecGui extends VerticalLayout {

    private ImageUploader imageUploader;
    private GalleryUploader galleryUploader;
    private OpisUploader opisUploader;

    @Autowired
    public ListaZdjecGui(ImageUploader imageUploader, GalleryUploader galleryUploader,OpisUploader opisUploader) {
        this.imageUploader = imageUploader;
        this.galleryUploader = galleryUploader;
        this.opisUploader =opisUploader;
        Label label = new Label("Lista Zdjęć");
        Anchor logout = new Anchor("logout", "Log out");
        add(label);

        List<Image> lista_zdj = imageUploader.lista_zdjec();

//        Grid<Image> grid = new Grid<>(Image.class);
//        grid.setItems(lista_zdj);
//       // grid.addColumn(image -> image.getGalleria().getNazwa()).setHeader("Nazwa galerii");
//       // grid.addColumn(new ComponentRenderer<>(image -> {
//       //     com.vaadin.flow.component.html.Image image1 = new com.vaadin.flow.component.html.Image(image.getImageAdress(), image.getImageAdress());
//       //     return image1;
//       // })).setHeader("Image");
//        grid.removeColumnByKey("imageAdress");
//
//        grid.removeColumnByKey("galleria");
//
//        grid.addColumn(new ComponentRenderer<>(image -> {
//
//            // button for saving the name to backend
//
//            Button update = new Button("Update", event -> {
//
//            });
//
//            // button that removes the item
//            Button remove = new Button("Remove", event -> {
//
//            });
//
//            HorizontalLayout buttons = new HorizontalLayout(update, remove);
//            return new VerticalLayout(buttons);
//        })).setHeader("Actions");






        for (int i = 0; i < lista_zdj.size(); i++) {
            Button button1 = new Button("Usuń");
            Button button2 = new Button("Edytuj");
            Label label1 = new Label();
            Label label2 = new Label();
            Label label3 = new Label();
            Label label4 = new Label();

            com.vaadin.flow.component.html.Image image = new com.vaadin.flow.component.html.Image(lista_zdj.get(i).getImageAdress(), "BRAK");
            Long a=lista_zdj.get(i).getId();
            List<Opis> opis =opisUploader.lista_ops().stream().filter(opi -> opi.getImage().getId()==a).collect(Collectors.toList());
            for(Opis op:opis) {
                label1.setText("Opis : " + op.getOpis());
                label2.setText("Autor : " + op.getAutor());
                label3.setText("Miejsce : " + op.getMiejsce_zdjecia());
            }
            label4.setText("Galeria :" + lista_zdj.get(i).getGalleria().getNazwa());
            int finalI = i;
            button1.addClickListener(buttonClickEvent -> {
                        imageUploader.delete_item(finalI);
                        UI.getCurrent().getPage().reload();
                        label.setText("Usuń to zdjęcie");
                        add(label);
                    }
            );
            int finalI2 = i;
            button2.addClickListener(buttonClickEvent -> {
                 removeAll();
                        AtomicInteger id = new AtomicInteger();
                        com.vaadin.flow.component.html.Image image1 = new com.vaadin.flow.component.html.Image(lista_zdj.get(finalI2).getImageAdress(), "nie ma obrazka :(");

                        List<String> lista2 = galleryUploader.lista_gallerii();
                        List<Galleria> lista3 = galleryUploader.lista_gallerii2();
                        Select<String> select = new Select<>();
                        select.setLabel("Lista Galerii");
                        select.setItems(lista2);

                        TextField textField2 = new TextField();
                        textField2.setLabel("Opis");
                        textField2.setValue(opis.get(0).getOpis());

                        TextField textField3 = new TextField();
                        textField3.setLabel("Autor");
                        textField3.setValue(opis.get(0).getAutor());

                        TextField textField4 = new TextField();
                        textField4.setLabel("Miejsce");
                        textField4.setValue(opis.get(0).getMiejsce_zdjecia());
                        add(image1);
                        add(select);
                        add(textField2);
                        add(textField3);
                        add(textField4);
                        Button button3 = new Button("zapisz");
                        add(button3);


                select.addValueChangeListener(event -> {
                            String value1;
                            value1 = event.getValue();

                            for (int j = 0; j < lista2.size(); j++) {
                                if (value1 == lista2.get(j)) {
                                    id.set(j);
                                }
                            }
                        });

                        button3.addClickListener(bCE -> {
                            Galleria galleria = lista3.get(id.get());
                            imageUploader.update(lista_zdj.get(finalI2).getId(),galleria,textField2.getValue(),textField3.getValue(),textField4.getValue());
                            UI.getCurrent().getPage().reload();
                        });

                    }
            );

            add(image);
            add(label1);
            add(label2);
            add(label3);
            add(label4);
            add(button2);
            add(button1);
        }

        add(logout);

    }

}

