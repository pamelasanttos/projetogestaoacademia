/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author joaov
 */
public class UsuarioInserirModel {
    private String Nome;
    private String CPF;
    private String Email;
    private String Telefone;
    private int Sexo;
    private String DataNascimento; 

    public UsuarioInserirModel() {
    }

    public UsuarioInserirModel(String Nome, String CPF, String Email, String Telefone, int Sexo, String DataNascimento) {
        this.Nome = Nome;
        this.CPF = CPF;
        this.Email = Email;
        this.Telefone = Telefone;
        this.Sexo = Sexo;
        this.DataNascimento = DataNascimento;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
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
    
    

    public int getSexo() {
        return Sexo;
    }

    public void setSexo(int Sexo) {
        this.Sexo = Sexo;
    }

    public String getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(String DataNascimento) {
        this.DataNascimento = DataNascimento;
    }
    
    
}
