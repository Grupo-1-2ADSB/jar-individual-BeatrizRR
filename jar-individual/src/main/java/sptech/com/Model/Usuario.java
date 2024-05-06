package sptech.com.Model;

public class Usuario {
    private Integer idusuario;

    private String nomeUser;

    private String email;

    private String senha;

    public Usuario(Integer idusuario, String nomeUser, String emial, String senha) {
        this.idusuario = idusuario;
        this.nomeUser = nomeUser;
        this.email = email;
        this.senha = senha;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public String getEmial() {
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

    public void setEmial(String emial) {
        this.email = emial;
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
