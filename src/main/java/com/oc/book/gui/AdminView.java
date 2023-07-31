package com.oc.book.gui;

import com.oc.book.entities.Book;
import com.oc.book.services.BookService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import jakarta.annotation.security.RolesAllowed;
import org.vaadin.crudui.crud.impl.GridCrud;

@Route("admin")
@RolesAllowed("ADMIN")
public class AdminView extends VerticalLayout {

    public AdminView(BookService service) {
        var crud = new GridCrud<>(Book.class, service);
        crud.getGrid().setColumns("title", "published", "rating");
        crud.getCrudFormFactory().setVisibleProperties("title", "published", "rating");
        crud.setAddOperationVisible(false);
        crud.getCrudLayout().addToolbarComponent(new RouterLink("Nuevo libro", NewView.class));
        crud.getCrudLayout().addToolbarComponent(new RouterLink("Reporte", ReportView.class));
        add(
                new H1("Admin Libros"), crud
        );
    }

}
