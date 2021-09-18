package com.venda.dao;

import java.sql.*;

public class Conexao {

    private final String SERVIDOR = "127.0.0.1";
    private final String PORTA = "5432";
    private final String BANCO_DE_DADOS = "dbsistemavenda";
    private final String USUARIO = "postgres";
    private final String SENHA = "#abc123#";
    private final String URL = "jdbc:postgresql://" + SERVIDOR + ":" + PORTA + "/" + BANCO_DE_DADOS;

    private Connection conexao;

    /**
 * Classe responsável por abrir a conexão com o banco de dados
 *
 * 
 */

    public Conexao() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            conexao.setAutoCommit(false);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new ClassNotFoundException("O driver de conexão não foi adicionado ao projeto.\n" + e.getMessage());
        } catch (SQLException e) {
            throw new SQLException("Problemas nos parâmetros de conexão.\n" + e.getMessage());
        }
    }

    public Connection getConexao() {
        return conexao;
    }

    public void confirmar() throws SQLException {
        try {
            conexao.commit();
        } catch (SQLException e) {
            throw new SQLException("Problemas na instrução SQL.\n" + e.getMessage());
        } finally {
            conexao.close();
        }
    }
}
