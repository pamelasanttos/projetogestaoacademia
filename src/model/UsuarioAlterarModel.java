/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author joaov
 */
public class UsuarioAlterarModel {
    private int IdUsuario;
    private String Nome;
    private String Email;
    private String Telefone;
    private int IdSexo;
    private String DataNascimento; 

    public UsuarioAlterarModel(int IdUsuario, String Nome, String Email, String Telefone, int IdSexo, String DataNascimento) {
        this.IdUsuario = IdUsuario;
        this.Nome = Nome;
        this.Email = Email;
        this.Telefone = Telefone;
        this.IdSexo = IdSexo;
        this.DataNascimento = DataNascimento;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public int getIdSexo() {
        return IdSexo;
    }

    public void setIdSexo(int IdSexo) {
        this.IdSexo = IdSexo;
    }

    public String getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(String DataNascimento) {
        this.DataNascimento = DataNascimento;
    }
    
    
}
