package oop.clubsv3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("oop.clubsv3.data")
public class Application
{
	
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}
	
}
