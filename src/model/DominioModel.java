/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author joaov
 */
public class DominioModel {
    
    private final int IdDominio;
    private final String Descricao;
    
    public DominioModel(int IdDominio, String Descricao) {
        this.IdDominio = IdDominio;
        this.Descricao = Descricao;
    }

    public int getIdDominio() {
        return IdDominio;
    }

    public String getDescricao() {
        return Descricao;
    }

    
    
}
