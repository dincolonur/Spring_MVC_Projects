package com.onur.db;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Service;

@Service
public class DerbyDataSource {

  public DataSource dataSource() {

    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    EmbeddedDatabase db =
        builder
            .setType(EmbeddedDatabaseType.DERBY)
            .addScript("derby-db-scripts/create_tables.sql")
            .build();
    return db;
  }
}
