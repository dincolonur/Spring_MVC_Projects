package com.onur.pdf;

import com.onur.model.PhoneNumber;
import com.onur.model.User;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.NavigableSet;

public class UserPDFView extends AbstractPdfView {

  protected void buildPdfDocument(
      Map<String, Object> model,
      Document document,
      PdfWriter pdfWriter,
      HttpServletRequest request,
      HttpServletResponse response)
      throws Exception {
    Map<String, User> userData = (Map<String, User>) model.get("userData");

    Table table = new Table(3);
    table.addCell("User Id");
    table.addCell("User Name");
    table.addCell("User Phone Numbers");

    for (Map.Entry<String, User> entry : userData.entrySet()) {
      table.addCell(entry.getKey());
      User user = entry.getValue();
      table.addCell(user.getFirstName() + " " + user.getLastName());
      NavigableSet<PhoneNumber> phoneNumbers = user.getPhoneNumbers();
      String phoneNumbersStr = "";
      for (PhoneNumber phoneNumber : phoneNumbers) {
        phoneNumbersStr += " (" + phoneNumber.getPhoneNumber() + ") ";
      }
      table.addCell(phoneNumbersStr);
    }
    document.add(table);
  }
}
