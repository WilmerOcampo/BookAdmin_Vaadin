package com.oc.book.gui;

import com.oc.book.entities.Book;
import com.oc.book.services.BookService;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import jakarta.annotation.security.RolesAllowed;
import org.vaadin.reports.PrintPreviewReport;

@Route("report")
@RolesAllowed("ADMIN")
public class ReportView extends VerticalLayout {

    public ReportView(BookService service) {
        var report = new PrintPreviewReport<>(Book.class, "title", "published", "rating");
        report.setItems(service.findAll());
        report.getReportBuilder().setTitle("Libros");

        StreamResource pdf = report.getStreamResource("Reporte libros.pdf", service::findAll, PrintPreviewReport.Format.PDF);
        StreamResource docx = report.getStreamResource("Reporte libros.docx", service::findAll, PrintPreviewReport.Format.DOCX);

        add(
                new HorizontalLayout(
                        new Anchor(pdf, "PDF"),
                        new Anchor(docx, "DOCX")
                ),
                report
        );
    }
}
