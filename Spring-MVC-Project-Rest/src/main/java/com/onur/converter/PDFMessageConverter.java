package com.onur.converter;

import com.onur.model.User;
import com.lowagie.text.exceptions.UnsupportedPdfException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class PDFMessageConverter implements HttpMessageConverter {

  @Autowired private PDFUserConstructor pdfUserConstructor;

  @Override
  public boolean canRead(Class aClass, MediaType mediaType) {
    return false;
  }

  @Override
  public boolean canWrite(Class aClass, MediaType mediaType) {
    return mediaType == null || mediaType.equals(MediaType.parseMediaType("application/pdf"));
  }

  @Override
  public List<MediaType> getSupportedMediaTypes() {
    return Collections.singletonList(MediaType.parseMediaType("application/pdf"));
  }

  @Override
  public Object read(Class aClass, HttpInputMessage httpInputMessage)
      throws IOException, HttpMessageNotReadableException {
    throw new UnsupportedPdfException("");
  }

  @Override
  public void write(Object object, MediaType mediaType, HttpOutputMessage httpOutputMessage)
      throws IOException, HttpMessageNotWritableException {
    HttpHeaders headers = httpOutputMessage.getHeaders();
    headers.setContentType(MediaType.parseMediaType("application/pdf"));
    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=users.pdf");
    headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
    OutputStream outputStream = httpOutputMessage.getBody();

    List<User> userList = new ArrayList<>();

    if (object instanceof User) {
      userList.add((User) object);
    }

    if (object instanceof List) {
      userList.addAll((Collection<User>) object);
    }

    try {
      pdfUserConstructor.constructUser(userList, outputStream);
    } catch (Exception e) {
      e.printStackTrace();
    }

    outputStream.flush();
    outputStream.close();
  }
}
