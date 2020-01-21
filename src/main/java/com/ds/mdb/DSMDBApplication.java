package com.ds.mdb;

import com.ds.mdb.service.DataBaseSchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DSMDBApplication {

    @Autowired
    private DataBaseSchemaService dataBaseSchemaService;

    public static void main(String[] args) {
        SpringApplication.run(DSMDBApplication.class, args);
    }

    @PostConstruct
    private void ensureDBSchema() {
        dataBaseSchemaService.ensureSchema();
    }
}
