package controller;

import com.mysql.jdbc.Connection;

public class UsuarioDAO {
	private Connection conexao;

	public UsuarioDAO() {
		this.conexao = Conexao.getConexao();
	}
}
