/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.UsuarioAlterarModel;
import model.UsuarioInserirModel;
import model.UsuarioModel;

/**
 *
 * @author joaov
 */
public interface IUsuarioDto {
    public void criarTabela();
    public void InserirUsuario(UsuarioInserirModel model);
    public void AlterarUsuario(UsuarioAlterarModel model);
    public List<UsuarioModel> ListarUsuario();
    public UsuarioModel PesquisarUsuarioPorId(int idUsuario);
    public UsuarioModel PesquisarUsuarioPorCPF(String cpf);
    public void ExcluirUsuario(int idUsuario);
}
