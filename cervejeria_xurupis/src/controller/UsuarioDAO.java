package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import com.mysql.jdbc.Connection;

import model.Usuario;

public class UsuarioDAO {
	private Connection conexao;

	public UsuarioDAO() {
		this.conexao = Conexao.getConexao();
	}//BCrypt.hashpw(senhaCriptografar, BCrypt.gensalt());
	//BCrypt.checkpw(senha, senhaCript);
	
	public boolean verificarUsuario(String email, String senha) {
		String sql = "SELECT * FROM usuario WHERE email = ?";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString("email").equals(email)) {
					if (BCrypt.checkpw(senha, rs.getString("senha"))) {
						return true;
					}
					else return false;
				}
				else return false;
			}
			return false;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean inserirUsuario(Usuario user) {
		String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, user.getNome());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getSenha());
			ps.execute();
			return true;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean verificarEmail(String email) {
		String sql = "SELECT COUNT(*) FROM usuario WHERE email = ?";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) > 0) {
					return true;
				}
				else return false;
			}
			return false;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
