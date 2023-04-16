package indi.gd.pcmw.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

@RestController
@RequestMapping("/common")
public class CommonController {
    @Value("${UserQualPath}")
    private String usersQualPath;

    @Value("${DoctorQualPath}")
    private String doctorsQualPath;

    @Value("${HospitalQualPath}")
    private String hospitalsQualPath;

    @GetMapping("/downloadImg")
    public void downloadImg(@RequestParam("fileName") String fileName,HttpServletResponse response){
        File f = new File(fileName);
      try  (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
      ){
          response.setContentType("image/jpeg");
          byte[] buffer = new byte[1024];
          int ch = 0;
         while ((ch = bis.read(buffer)) != -1){
            bos.write(buffer,0,ch);
            bos.flush();
         }
      }catch (Exception e){
          e.printStackTrace();
      }
    }
}
