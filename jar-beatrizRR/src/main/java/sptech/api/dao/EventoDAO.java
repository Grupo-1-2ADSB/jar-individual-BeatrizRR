package sptech.api.dao;

import sptech.api.model.ConexaoBanco;
import sptech.api.model.Evento;

import java.sql.Connection;

public class EventoDAO {

    private final ConexaoBanco conexaoMysql;

    public EventoDAO(ConexaoBanco conexaoBanco) {
        this.conexaoMysql = conexaoBanco;
    }

    public void inserirEvento(Connection conexao, Evento evento){

    }
}
