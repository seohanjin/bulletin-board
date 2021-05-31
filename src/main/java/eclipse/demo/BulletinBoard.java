package eclipse.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BulletinBoard {

	public static void main(String[] args) {
		SpringApplication.run(BulletinBoard.class, args);
	}


//	@Bean(name = "uploadPath")
//	public String uploadPath(){
//		return "d:/image/";
//	}

	@Bean(name = "uploadPath")
	public String uploadPath(){
		return "/home/image/";
	}

}
