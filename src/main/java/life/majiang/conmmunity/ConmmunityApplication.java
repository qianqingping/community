package life.majiang.conmmunity;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("life.majiang.conmmunity.mapper")
public class ConmmunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConmmunityApplication.class, args);
    }

}
