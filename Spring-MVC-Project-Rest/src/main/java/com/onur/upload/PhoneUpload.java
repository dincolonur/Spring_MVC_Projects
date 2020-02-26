package com.onur.upload;

import com.onur.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PhoneUpload {

  @Autowired PhoneService phoneService;

  public void uploadFile(MultipartFile file) throws IOException {
    String content = new String(file.getBytes(), "UTF-8");
    String lines[] = content.split("\\r?\\n");
    for (String line : lines) {
      System.out.println("line: " + line);
      phoneService.savePhone(line);
    }
  }
}
