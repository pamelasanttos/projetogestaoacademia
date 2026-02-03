/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author joaov
 */
public class SexoModel {
    private int IdDominio;
    private String Descricao;
    
    public SexoModel()
    {
    }
    
    public SexoModel(int IdDominio, String Descricao) {
        this.IdDominio = IdDominio;
        this.Descricao = Descricao;
    }

    public int getIdDominio() {
        return IdDominio;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setIdDominio(int IdDominio) {
        this.IdDominio = IdDominio;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }
      
}
