package com.epam.controller;

import com.epam.upload.CompanyUpload;
import com.epam.upload.PhoneUpload;
import com.epam.upload.UserUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.StringJoiner;

@Controller
@RequestMapping("/")
public class UploadController {

  @Autowired UserUpload userUpload;

  @Autowired PhoneUpload phoneUpload;

  @Autowired CompanyUpload companyUpload;

  @RequestMapping
  public String handleRequest() {
    return "index";
  }

  @RequestMapping(value = "/uploadMultiPage", method = RequestMethod.POST)
  public String multiFileUpload(
      @RequestParam("files") MultipartFile[] files, RedirectAttributes redirectAttributes) {

    StringJoiner sj = new StringJoiner(" , ");

    for (MultipartFile file : files) {

      if (file.isEmpty()) {
        continue;
      }

      try {
        uploadFile(file);
        sj.add(file.getOriginalFilename());

      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    String uploadedFileName = sj.toString();
    if (StringUtils.isEmpty(uploadedFileName)) {
      redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
    } else {
      redirectAttributes.addFlashAttribute(
          "message", "You successfully uploaded '" + uploadedFileName + "'");
    }
    return "redirect:uploadStatus";
  }

  @GetMapping("/uploadMultiPage")
  public String uploadMultiPage() {
    return "uploadMulti";
  }

  @GetMapping("/uploadStatus")
  public String uploadStatus() {
    return "uploadStatus";
  }

  public void uploadFile(MultipartFile file) throws IOException {
    if (file.getOriginalFilename().startsWith("user")) {
      userUpload.uploadFile(file);
    } else if (file.getOriginalFilename().startsWith("phone")) {
      phoneUpload.uploadFile(file);
    } else if (file.getOriginalFilename().startsWith("compan")) {
      companyUpload.uploadFile(file);
    }
  }
}
