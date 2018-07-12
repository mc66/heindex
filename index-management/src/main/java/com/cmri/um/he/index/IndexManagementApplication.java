package com.cmri.um.he.index;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author lch
 */
@SpringBootApplication
//@MapperScan(basePackages = "com.cmri.um.he.index.operations.mapper")
public class IndexManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndexManagementApplication.class, args);
	}
}
