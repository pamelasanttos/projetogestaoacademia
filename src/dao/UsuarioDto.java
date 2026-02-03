/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.util.List;
import model.UsuarioInserirModel;
import model.UsuarioModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.UsuarioAlterarModel;

/**
 *
 * @author joaov
 */
public class UsuarioDto implements IUsuarioDto {

    private final IConexao connection;

    public UsuarioDto(IConexao connection) {
        this.connection = connection;
    }

    @Override
    public void criarTabela() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Usuario (
                IdUsuario INTEGER PRIMARY KEY AUTOINCREMENT,
                Nome TEXT NOT NULL,
                CPF TEXT NOT NULL,
                Email TEXT NOT NULL,
                Telefone TEXT,
                IdSexo INTEGER NOT NULL,
                DataNascimento TEXT
            );
        """;

        this.connection.conectar();
        this.connection.Execute(sql);
    }

    @Override
    public void InserirUsuario(UsuarioInserirModel model) {

        String sql = """
            INSERT INTO Usuario (Nome, CPF, Email, Telefone, IdSexo, DataNascimento)
            VALUES (?, ?, ?, ?, ?, ?);
        """;

        try (Connection c = this.connection.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, model.getNome());
            ps.setString(2, model.getCPF());
            ps.setString(3, model.getEmail());
            ps.setString(4, model.getTelefone());
            ps.setInt(5, model.getSexo());
            ps.setString(6, model.getDataNascimento());

            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void AlterarUsuario(UsuarioAlterarModel model) {
        String sql = """
            UPDATE Usuario
            SET Nome = ?, Email = ?, Telefone = ?, IdSexo = ?, DataNascimento = ?
            WHERE IdUsuario = ?;
        """;

        try (Connection c = this.connection.conectar();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, model.getNome());
            ps.setString(2, model.getEmail());
            ps.setString(3, model.getTelefone());
            ps.setInt(4, model.getIdSexo());
            ps.setString(5, model.getDataNascimento());
            ps.setInt(6, model.getIdUsuario());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UsuarioModel> ListarUsuario() {
        List<UsuarioModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM Usuario;";

        try (Connection c = this.connection.conectar();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                UsuarioModel u = new UsuarioModel();
                u.setIdUsuario(rs.getInt("IdUsuario"));
                u.setNome(rs.getString("Nome"));
                u.setCPF(rs.getString("CPF"));
                u.setEmail(rs.getString("Email"));
                u.setTelefone(rs.getString("Telefone"));
                u.setSexo(rs.getInt("IdSexo"));
                u.setDataNascimento(rs.getString("DataNascimento"));

                lista.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public UsuarioModel PesquisarUsuarioPorId(int idUsuario) {
        String sql = "SELECT * FROM Usuario WHERE IdUsuario = ?;";
        UsuarioModel usuario = null;

        try (Connection c = this.connection.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new UsuarioModel();
                usuario.setIdUsuario(rs.getInt("IdUsuario"));
                usuario.setNome(rs.getString("Nome"));
                usuario.setCPF(rs.getString("CPF"));
                usuario.setEmail(rs.getString("Email"));
                usuario.setTelefone(rs.getString("Telefone"));
                usuario.setSexo(rs.getInt("IdSexo"));
                usuario.setDataNascimento(rs.getString("DataNascimento"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }
    
    @Override
    public UsuarioModel PesquisarUsuarioPorCPF(String cpf) {
        String sql = "SELECT * FROM Usuario WHERE CPF = ?;";
        UsuarioModel usuario = null;

        try (Connection c = this.connection.conectar();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new UsuarioModel();
                usuario.setIdUsuario(rs.getInt("IdUsuario"));
                usuario.setNome(rs.getString("Nome"));
                usuario.setCPF(rs.getString("CPF"));
                usuario.setEmail(rs.getString("Email"));
                usuario.setTelefone(rs.getString("Telefone"));
                usuario.setSexo(rs.getInt("IdSexo"));
                usuario.setDataNascimento(rs.getString("DataNascimento"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }
    @Override
    public void ExcluirUsuario(int idUsuario) {
        String sql = "DELETE FROM Usuario WHERE IdUsuario = ?;";

        try (Connection c = this.connection.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

