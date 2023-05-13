package indi.gd.pcmw.controller;

import indi.gd.pcmw.dao.HospNoticeDao;
import indi.gd.pcmw.domain.HospNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/common")
@CrossOrigin
public class CommonController {
    @Value("${UserQualPath}")
    private String usersQualPath;

    @Value("${DoctorQualPath}")
    private String doctorsQualPath;

    @Value("${HospitalQualPath}")
    private String hospitalsQualPath;

    @Autowired
    private HospNoticeDao hospNoticeDao;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
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
    @GetMapping("/getRecentHospNotice")
    public List<HospNotice> getRecentHospNotice(){
        String key = "recentNotice";
        List<HospNotice> hospNotices = (List<HospNotice>) redisTemplate.opsForValue().get(key);
        if (hospNotices != null){
            return hospNotices;
        }
        redisTemplate.opsForValue().set(key,hospNoticeDao.getRecentHospNotice(),60, TimeUnit.MINUTES);
        return hospNoticeDao.getRecentHospNotice();
    }
}
