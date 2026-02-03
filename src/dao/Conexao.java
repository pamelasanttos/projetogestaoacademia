/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author joaov
 */
public class Conexao implements IConexao {
    
    private static final String URL = "jdbc:sqlite:gestaoacademia.db";
    private final Connection conexao; 

    public Conexao(){
        this.conexao = this.conectar();
    }
    @Override
    public Connection conectar() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão", e);
        }
    }

    @Override
    public void Execute(String sql) {
        try {
            Statement st = this.conexao.createStatement();
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
