package com.onur.pdf;

import com.onur.model.PhoneNumber;
import com.onur.model.User;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
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

    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    String username = "";
    Boolean superUser = false;

    if (principal instanceof UserDetails) {
      username = ((UserDetails) principal).getUsername();
      Collection<GrantedAuthority> roles =
          (Collection<GrantedAuthority>) ((UserDetails) principal).getAuthorities();
      for (GrantedAuthority role : roles) {
        if (role.getAuthority().equalsIgnoreCase("BOOKING_MANAGER")) {
          superUser = true;
        }
      }
    }

    System.out.println("Current user is " + username);

    Map<String, User> userData = (Map<String, User>) model.get("userData");

    Table table = new Table(3);
    table.addCell("User Id");
    table.addCell("User Name");
    table.addCell("User Phone Numbers");

    if (superUser) {
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
    } else {
      for (Map.Entry<String, User> entry : userData.entrySet()) {
        User user = entry.getValue();
        if (user.getFirstName().equalsIgnoreCase(username)) {
          table.addCell(entry.getKey());
          table.addCell(user.getFirstName() + " " + user.getLastName());
          NavigableSet<PhoneNumber> phoneNumbers = user.getPhoneNumbers();
          String phoneNumbersStr = "";
          for (PhoneNumber phoneNumber : phoneNumbers) {
            phoneNumbersStr += " (" + phoneNumber.getPhoneNumber() + ") ";
          }
          table.addCell(phoneNumbersStr);
        }
      }
    }

    document.add(table);
  }
}
