package com.oc.book.services;

import com.oc.book.entities.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService implements CrudListener<Book> {

    private final BookRepository brepos;

    @Override
    public List<Book> findAll() {
        return brepos.findAll();
    }

    @Override
    public Book add(Book book) {
        return brepos.save(book);
    }

    @Override
    public Book update(Book book) {
        return brepos.save(book);
    }

    @Override
    public void delete(Book book) {
        brepos.delete(book);
    }
}
