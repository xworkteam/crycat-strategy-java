package com.xteam.crycat;

import com.xteam.crycat.thrift.ThriftServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrycatStrategyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrycatStrategyApplication.class, args);
		try {
			ThriftServer.startServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
