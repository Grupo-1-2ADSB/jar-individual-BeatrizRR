package sptech.api.dao;

import sptech.api.model.ConexaoBanco;
import sptech.api.model.usuario.Usuario;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.SQLException;

public class UsuarioDAO {
    public Usuario retornaUsuario(String userVerificar, String senhaVerificar) throws SQLException {

        ConexaoBanco conexaoBanco = new ConexaoBanco();
        JdbcTemplate conexao = conexaoBanco.getJdbcTemplate();

        Usuario usuario = null;
        try {
            usuario = conexao.queryForObject("SELECT * FROM usuario WHERE nomeUser = '%s' AND senha = '%s'".formatted(userVerificar, senhaVerificar), new BeanPropertyRowMapper<>(Usuario.class));
        } catch (Exception e) {
            return usuario;
        }

        if (usuario != null) {
            return usuario;
        }

        return null;
    }
}
