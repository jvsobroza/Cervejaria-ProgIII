package controller;

import java.io.IOException;

import com.mysql.jdbc.Connection;

public class CervejaDAO {
	private Connection conexao;

	public CervejaDAO() throws IOException {
		this.conexao = Conexao.getConexao();
	}
}
