package com.epam;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.io.File;

public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  private int maxUploadSizeInMb = 10 * 1024 * 1024; // Maximum Upload size 10 MB

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] {PhoneBookConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] {"/"};
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return null;
  }

  @Override
  protected void customizeRegistration(ServletRegistration.Dynamic registration) {

    // temp file will be uploaded here
    File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

    // register a MultipartConfigElement
    MultipartConfigElement multipartConfigElement =
        new MultipartConfigElement(
            uploadDirectory.getAbsolutePath(),
            maxUploadSizeInMb,
            maxUploadSizeInMb * 2,
            maxUploadSizeInMb / 2);

    registration.setMultipartConfig(multipartConfigElement);
  }
}
