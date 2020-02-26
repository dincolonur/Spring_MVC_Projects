package com.onur.converter;

import com.onur.model.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.DocumentException;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.List;

@Service
public class PDFUserConstructor {

  public OutputStream constructUser(List<User> userList, OutputStream outputStream)
      throws Exception {
    try {
      return processTicketConstructing(userList, outputStream);
    } catch (DocumentException e) {
      throw new Exception(e);
    }
  }

  private OutputStream processTicketConstructing(List<User> userList, OutputStream outputStream)
      throws DocumentException {
    Document userDocument = new Document();
    try {
      PdfWriter.getInstance(userDocument, outputStream);
    } catch (com.itextpdf.text.DocumentException e) {
      e.printStackTrace();
    }

    userDocument.open();

    if (userList.isEmpty()) {
      constructEmptyListCorrectAnswer(userDocument);
      userDocument.close();
      return outputStream;
    }

    for (User user : userList) {
      constructUser(user, userDocument);
    }
    userDocument.close();

    return outputStream;
  }

  private void constructEmptyListCorrectAnswer(Document userDocument) throws DocumentException {
    try {
      userDocument.add(new Phrase("Users not found!"));
    } catch (com.itextpdf.text.DocumentException e) {
      e.printStackTrace();
    }
  }

  private void constructUser(User user, Document userDocument) {
    PdfPTable table = new PdfPTable(1);
    PdfPCell cell = new PdfPCell();
    cell.setBorder(Rectangle.TOP);
    cell.addElement(new Phrase("User id: " + user.getUserId()));
    cell.addElement(new Phrase("User Name: " + user.getUsername()));
    cell.addElement(new Phrase("User Address: " + user.getUserAddress()));
    cell.addElement(new Phrase("User Phone Numbers: " + user.getPhoneNumbers()));
    cell.addElement(new Phrase("User Password: " + user.getPassword()));
    cell.addElement(new Phrase("User Roles: " + user.getRoles()));
    cell.setBorder(Rectangle.BOTTOM);
    table.addCell(cell);
    try {
      userDocument.add(table);
    } catch (com.itextpdf.text.DocumentException e) {
      e.printStackTrace();
    }
  }
}
