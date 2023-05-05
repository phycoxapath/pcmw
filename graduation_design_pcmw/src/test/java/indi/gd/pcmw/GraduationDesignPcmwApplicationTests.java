package indi.gd.pcmw;

import indi.gd.pcmw.dto.ApplyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.List;
import java.util.Set;

@SpringBootTest
class GraduationDesignPcmwApplicationTests {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    @Test
    void contextLoads() {
    }
    @Test
    public void simpleTest(){
        redisTemplate.opsForValue().set("addr","tw");


    }
}
