package com.epam.rest;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

@Service
public class UserPdfViewResolver implements ViewResolver {
  @Override
  public View resolveViewName(String s, Locale locale) throws Exception {
    UserNewPdfView pdfView = new UserNewPdfView();
    return pdfView;
  }
}
