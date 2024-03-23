package org.example.mmall;

import jakarta.annotation.Resource;
import org.example.mmall.model.User;
import org.example.mmall.util.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MmallApplicationTests {

    @Test
    void contextLoads() {
    }

/*    JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
    @Test
    void tokenTest(){
        User user = new User(1l,"13333922211","123123");
        System.out.println(jwtTokenUtil.getToken(user));
    }*/


}
