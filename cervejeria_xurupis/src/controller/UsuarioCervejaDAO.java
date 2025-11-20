package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;

import model.Degustacao;
import model.Usuario;
import model.Usuario_Cerveja;

public class UsuarioCervejaDAO {
	private Connection conexao;

	public UsuarioCervejaDAO() throws IOException {
		this.conexao = Conexao.getConexao();
	}

	public boolean inserirDegustacao(Usuario_Cerveja degu) throws ParseException {
		String sql = "INSERT INTO usuario_cerveja (id_usuario,id_cerveja,data_degustacao,local_degustacao,avaliacao,critica,foto,sugestao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, degu.getIdUsuario());
			ps.setInt(2, degu.getIdCerveja());
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			java.sql.Date data = new java.sql.Date(format.parse(degu.getDataDegustacao()).getTime());
			ps.setDate(3, data);
			ps.setString(4, degu.getLocalDegustacao());
			ps.setInt(5, degu.getAvaliacao());
			ps.setString(6, degu.getCritica());
			ps.setString(7, degu.getFoto());
			ps.setString(8, degu.getSugestao());
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public LinkedList<Degustacao> listarDegustacao(Usuario user){
		LinkedList<Degustacao> ls = new LinkedList<>();
		String sql = "SELECT usuario_cerveja.*, cerveja.nome FROM usuario_cerveja INNER JOIN cerveja ON cerveja.id_cerveja = usuario_cerveja.id_cerveja WHERE id_usuario = ?";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, user.getIdUsuario());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Degustacao degustacao = new Degustacao();
				degustacao.setId_usuario_cerveja(rs.getInt("id_cerveja_usuario"));
				degustacao.setNome_cerveja(rs.getString("cerveja.nome"));
				degustacao.setAvaliacao(rs.getInt("avaliacao"));
				degustacao.setData_degustacao(rs.getDate("data_degustacao"));
				degustacao.setLocal_degustacao(rs.getString("local_degustacao"));
				degustacao.setCritica(rs.getString("critica"));
				degustacao.setSugestao(rs.getString("sugestao"));
				degustacao.setFoto(rs.getString("foto"));
				System.out.println(degustacao.toString());
				ls.add(degustacao);
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return ls;
	}
}
