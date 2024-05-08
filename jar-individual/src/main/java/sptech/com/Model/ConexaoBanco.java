package sptech.com.Model;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoBanco {
    private JdbcTemplate jdbcTemplate;


    public ConexaoBanco() {
        BasicDataSource bds = new BasicDataSource();

        bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bds.setUrl("jdbc:mysql://localhost:3306/medtech");
        bds.setPassword("#BDbea21");
        bds.setUsername("root");
        jdbcTemplate = new JdbcTemplate(bds);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
