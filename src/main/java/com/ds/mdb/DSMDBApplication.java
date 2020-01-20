package com.ds.mdb;

import com.ds.mdb.components.DataBaseSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DSMDBApplication {

    @Autowired
    private DataBaseSchema dataBaseSchema;

    public static void main(String[] args) {
        SpringApplication.run(DSMDBApplication.class, args);
    }

    @PostConstruct
    private void ensureDBSchema() {
        dataBaseSchema.ensureSchema();
    }
}
