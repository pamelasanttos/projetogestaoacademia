/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.SexoModel;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;


/**
 *
 * @author joaov
 */
public class DominioDto implements IDominio {
    
    private final IConexao connection;
    
    public DominioDto(IConexao c)
    {
        this.connection = c;
    }
    
    public void CriarTabelas()
    {
        criarTabelaSexo();
    }
    
    public void CargaTabelas()
    {
        cargaTabelaSexo();
    }
    
    
    private void criarTabelaSexo() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Sexo (
                IdSexo INTEGER  PRIMARY KEY AUTOINCREMENT,
                Descricao TEXT UNIQUE NOT NULL
                );
            )
        """;
        
        this.connection.conectar();
        this.connection.Execute(sql);
        
    }

    private void cargaTabelaSexo() {
        String sql = """
            INSERT OR IGNORE INTO Sexo (Descricao)
            VALUES ('Masculino'), ('Feminino');
        """;
        this.connection.conectar();
        this.connection.Execute(sql);
    }
    
    public List<SexoModel> BuscarSexo()
    {
       String sql = """
        SELECT 
            IdSexo,
            Descricao
        FROM Sexo
        """; 
       List<SexoModel> lista = new ArrayList<>();
       try
       {
           var c = this.connection.conectar();
           
          PreparedStatement ps = c.prepareStatement(sql);
          
          ResultSet rs = ps.executeQuery();
          
          
          
          while (rs.next()) {
            SexoModel a = new SexoModel();
            a.setIdDominio(rs.getInt("IdSexo"));
            a.setDescricao(rs.getString("Descricao"));
            
            lista.add(a);
        }   
       }catch(Exception e)
       {
            e.printStackTrace();
       }
   
       return lista;
    }
    
}
