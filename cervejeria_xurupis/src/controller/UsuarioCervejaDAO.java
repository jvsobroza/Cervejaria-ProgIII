package controller;

import java.io.IOException;

import com.mysql.jdbc.Connection;

public class UsuarioCervejaDAO {
	private Connection conexao;

	public UsuarioCervejaDAO() throws IOException {
		this.conexao = Conexao.getConexao();
	}
}
