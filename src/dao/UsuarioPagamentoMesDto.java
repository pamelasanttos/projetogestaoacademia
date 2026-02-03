/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.UsuarioPagamentoMesModel;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 *
 * @author joaov
 */
public class UsuarioPagamentoMesDto implements IUsuarioPagamentoMesDto {
        private final IConexao connection;

    public UsuarioPagamentoMesDto(IConexao connection) {
        this.connection = connection;
    }

    @Override
    public void criarTabela() {
        String sql = """
            CREATE TABLE IF NOT EXISTS UsuarioPagamentoMes (
                IdPagamento INTEGER PRIMARY KEY AUTOINCREMENT,
                MesPagamento TEXT NOT NULL,
                IdUsuario INTEGER NOT NULL
            );
        """;

        this.connection.conectar();
        this.connection.Execute(sql);
    }

    @Override
    public void inserirPagamento(String mesPagamento, int idUsuario) {

        String sql = """
            INSERT INTO UsuarioPagamentoMes (MesPagamento, IdUsuario)
            VALUES (?, ?);
        """;

        try (Connection c = this.connection.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, mesPagamento);
            ps.setInt(2, idUsuario);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UsuarioPagamentoMesModel> listarPagamentos() {
        List<UsuarioPagamentoMesModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM UsuarioPagamentoMes;";

        try (Connection c = this.connection.conectar();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                UsuarioPagamentoMesModel p = new UsuarioPagamentoMesModel();
                p.setIdPagamento(rs.getInt("IdPagamento"));
                p.setMesPagamento(rs.getString("MesPagamento"));
                p.setIdUsuario(rs.getInt("IdUsuario"));

                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public List<UsuarioPagamentoMesModel> listarPorUsuario(int idUsuario) {
        List<UsuarioPagamentoMesModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM UsuarioPagamentoMes WHERE IdUsuario = ?;";

        try (Connection c = this.connection.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UsuarioPagamentoMesModel p = new UsuarioPagamentoMesModel();
                p.setIdPagamento(rs.getInt("IdPagamento"));
                p.setMesPagamento(rs.getString("MesPagamento"));
                p.setIdUsuario(rs.getInt("IdUsuario"));

                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
