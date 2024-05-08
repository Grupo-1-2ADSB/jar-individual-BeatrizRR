package sptech.com.Model;

public class Usuario {
    private Integer idusuario;

    private String nomeUser;

    private String email;

    private String senha;

    public Usuario(Integer idusuario, String nomeUser, String email, String senha) {
        this.idusuario = idusuario;
        this.nomeUser = nomeUser;
        this.email = email;
        this.senha = senha;
    }

    public Usuario() {
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return """
                ---------------------------------------
                USUÁRIO
                IdUsuario: %d
                Nome de usuário: %S
                E-mail: %s
                Senha: %s
                ---------------------------------------
                """.formatted(idusuario, nomeUser, email, senha);
    }
}
