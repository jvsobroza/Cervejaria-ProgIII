package controller;

import com.mysql.jdbc.Connection;

public class UsuarioCervejaDAO {
	private Connection conexao;

	public UsuarioCervejaDAO() {
		this.conexao = Conexao.getConexao();
	}
}
