package controller;

import com.mysql.jdbc.Connection;

public class CervejaDAO {
	private Connection conexao;

	public CervejaDAO() {
		this.conexao = Conexao.getConexao();
	}
}
