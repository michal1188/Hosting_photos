package zaliczenie.Hosting_photos.gui.admin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("admin")
public class AdminGui extends VerticalLayout {


    @Autowired
    public AdminGui() {

        Button button1= new Button("Dodaj zdjęcie");
        button1.addClickListener(buttonClickEvent -> UI.getCurrent().navigate("admin/dodaj_obrazek")  );

        Button button2= new Button("Dodaj galerie");
        button2.addClickListener(buttonClickEvent -> UI.getCurrent().navigate("admin/dodaj_galerie")  );


        Button button3= new Button("Zobacz liste galerii");
        button3.addClickListener(buttonClickEvent -> UI.getCurrent().navigate("admin/lista_galerii")  );

        Button button4= new Button("Zobacz wszystkie zdjęcia");
        button4.addClickListener(buttonClickEvent -> UI.getCurrent().navigate("admin/ListaZdjec")  );

        Anchor logout = new Anchor("logout", "Log out");


        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(logout);

    }

}
