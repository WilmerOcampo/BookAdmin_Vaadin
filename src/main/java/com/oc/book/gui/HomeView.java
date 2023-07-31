package com.oc.book.gui;

import com.oc.book.entities.Book;
import com.oc.book.services.BookService;
import com.vaadin.flow.component.cookieconsent.CookieConsent;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.vaadin.crudui.crud.impl.GridCrud;

@Route("")
@AnonymousAllowed
public class HomeView extends VerticalLayout {

    public HomeView(BookService service) {
        add(
                new H1("Home - Inicio"),
                new HorizontalLayout(
                        new RouterLink("Admin Libros", AdminView.class),
                        new RouterLink("Reporte", ReportView.class),
                        new RouterLink("Salir", LoginView.class)
                )
                //new CookieConsent()
        );
    }
}
