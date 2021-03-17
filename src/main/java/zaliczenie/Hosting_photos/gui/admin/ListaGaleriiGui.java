package zaliczenie.Hosting_photos.gui.admin;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import zaliczenie.Hosting_photos.model.Galleria;
import zaliczenie.Hosting_photos.model.Image;
import zaliczenie.Hosting_photos.service.GalleryUploader;
import zaliczenie.Hosting_photos.service.ImageUploader;

import java.util.List;

@Route("admin/lista_galerii")
public class ListaGaleriiGui  extends VerticalLayout {


    private ImageUploader imageUploader;
    private GalleryUploader galleryUploader;

    @Autowired
    public ListaGaleriiGui(GalleryUploader galleryUploader,ImageUploader imageUploader) {
        this.galleryUploader = galleryUploader;
        this.imageUploader=imageUploader;
        List<String>lista=galleryUploader.lista_gallerii();
  ///     List<Image> lista_zdj= imageUploader.lista_zdjec();
        Label label= new Label("Lista Galerii");
        add(label);

        for (int i=0;i<lista.size();i++){
            Label label2= new Label();
            Button button= new Button("Usuń");
            int finalI = i;
            button.addClickListener(buttonClickEvent ->{

                 //    for(Image im:lista_zdj){
                   //      if(im.getGalleria().getId().equals(finalI)){
                         //    imageUploader.delete_item(im.getId().intValue());
                      //   }
                   //  }
                     galleryUploader.delete_item(finalI);
                        UI.getCurrent().getPage().reload();
                        label.setText("Usunięto galerie");
                        add(label);
                    }
            );
            label2.setText(lista.get(i));

            add(label2);
            add(button);

        }
        Anchor logout = new Anchor("logout", "Log out");
        add(logout);

    }
}
