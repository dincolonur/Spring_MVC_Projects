package com.epam.rest;

import com.epam.model.User;
import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class UserNewPdfView extends AbstractPdfView {
  @Override
  protected void buildPdfDocument(
      Map<String, Object> map,
      Document document,
      PdfWriter pdfWriter,
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse)
      throws Exception {

    User user = (User) map.get("userPDF");

    float[] columnWidths = {1.5f, 2f};
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(90f);

    PdfPCell cell = null;
    cell = new PdfPCell(new Phrase("User ID"));
    table.addCell(cell);
    cell = new PdfPCell(new Phrase(Integer.toString(user.getUserId())));
    table.addCell(cell);
    cell = new PdfPCell(new Phrase("User Name"));
    table.addCell(cell);
    cell = new PdfPCell(new Phrase(user.getUsername()));
    table.addCell(cell);
    cell = new PdfPCell(new Phrase("User Address"));
    table.addCell(cell);
    cell = new PdfPCell(new Phrase(user.getUserAddress()));
    table.addCell(cell);
    cell = new PdfPCell(new Phrase("User Roles"));
    table.addCell(cell);
    cell = new PdfPCell(new Phrase(user.getRoles()));
    table.addCell(cell);

    document.add(table);
  }
}
