/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import model.UsuarioPagamentoMesModel;

/**
 *
 * @author joaov
 */
public interface IUsuarioPagamentoMesDto {
    void criarTabela();
    void inserirPagamento(String mesPagamento, int idUsuario);
    List<UsuarioPagamentoMesModel> listarPagamentos();
    List<UsuarioPagamentoMesModel> listarPorUsuario(int idUsuario);  
}
