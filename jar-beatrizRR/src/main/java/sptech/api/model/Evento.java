package sptech.api.model;

public class Evento {

    private Integer id;

    private String graviade;

    private String descricao;

    private Integer FkRegistro;

    public Evento(Integer id, String graviade, String descricao, Integer fkRegistro) {
        this.id = id;
        this.graviade = graviade;
        this.descricao = descricao;
        FkRegistro = fkRegistro;
    }

    public Evento() {
    }

    public Integer getId() {
        return id;
    }

    public String getGraviade() {
        return graviade;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getFkRegistro() {
        return FkRegistro;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGraviade(String graviade) {
        this.graviade = graviade;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setFkRegistro(Integer fkRegistro) {
        FkRegistro = fkRegistro;
    }
}
