package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.Connection;

import action.TratadorErros;
import model.Cerveja;
public class CervejaDAO {
	private Connection conexao;

	public CervejaDAO() throws IOException {
		this.conexao = Conexao.getConexao();
	}
	
	public LinkedList<Cerveja> selectCerveja(){
		LinkedList ls = new LinkedList<>();
		String sql = "SELECT * FROM cerveja ORDER BY nome";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			String linha = "";
			while (rs.next()) {
				Cerveja ceva = new Cerveja();
				ceva.setIdCerveja(rs.getInt("id_cerveja"));
				ceva.setNome(rs.getString("nome"));
				ceva.setPais(rs.getString("pais"));
				ceva.setIbu(rs.getInt("ibu"));
				ceva.setTeorAlcolico(rs.getDouble("teor_alcolico"));
				ceva.setTipo(rs.getString("tipo"));
				ls.add(ceva);
			}
		}catch(SQLException e) {
			TratadorErros.mensagemErro("Erro Listagem;Erro listar cervejas: " + e.getMessage());
		}
		return ls;
	}
}
