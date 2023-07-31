package com.oc.book.gui;

import com.oc.book.entities.Book;
import com.oc.book.services.BookService;
import com.vaadin.collaborationengine.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Route("new")
@RolesAllowed("ADMIN")
public class NewView extends VerticalLayout {

    // Los atributos tienen que ser iguales a la clase de la entidad
    private TextField title = new TextField("Titulo");
    private DatePicker published = new DatePicker("Publicación");
    private IntegerField rating = new IntegerField("Calificación");

    public NewView(BookService service) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        var userInfo = new UserInfo(username, username);

        var binder = new CollaborationBinder<>(Book.class, userInfo);
        binder.bindInstanceFields(this);
        binder.setTopic("new-book", () -> new Book());

        var msgList = new CollaborationMessageList(userInfo, "new-book");

        add(
                new H1("Nuevo Libro"),

                new HorizontalLayout(
                        new VerticalLayout(
                                new FormLayout(title, published, rating),
                                new Button("Guardar", event -> {
                                    var book = new Book();
                                    binder.writeBeanIfValid(book);
                                    service.add(book);
                                    Notification.show("Libro guardado.");
                                    binder.reset(new Book());
                                })
                        ),
                        new VerticalLayout(
                                msgList, new CollaborationMessageInput(msgList)
                        )
                )



        );
    }
}
