package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import com.mysql.jdbc.Connection;

import action.TratadorErros;
import model.Usuario;

public class UsuarioDAO {
	private Connection conexao;

	public UsuarioDAO() throws IOException {
		this.conexao = Conexao.getConexao();
	}// BCrypt.hashpw(senhaCriptografar, BCrypt.gensalt());
		// BCrypt.checkpw(senha, senhaCript);

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
					} else
						return false;
				} else
					return false;
			}
			return false;
		} catch (SQLException e) {
			TratadorErros.mensagemErro("Erro Verificação;Erro verificação do usuário: " + e.getMessage());
		}
		return false;
	}

	public boolean alterarUsuario(Usuario user) {
		String sql = "UPDATE usuario SET nome = ?, email = ? WHERE id_usuario = ?";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, user.getNome());
			ps.setString(2, user.getEmail());
			ps.setInt(3, user.getIdUsuario());
			ps.execute();
			return true;
		} catch (SQLException e) {
			TratadorErros.mensagemErro("Erro Alteração;Erro alteração do usuário: " + e.getMessage());
		}
		return false;
	}

	public boolean alterarUsuarioSenha(Usuario user) {
		String sql = "UPDATE usuario SET nome = ?, email = ?, senha  WHERE id_usuario = ?";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, user.getNome());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getSenha());
			ps.setInt(4, user.getIdUsuario());
			ps.execute();
			return true;
		} catch (SQLException e) {
			TratadorErros.mensagemErro("Erro Alteração;Erro alteração do usuário: " + e.getMessage());
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
		} catch (SQLException e) {
			TratadorErros.mensagemErro("Erro Cadastro;Erro cadastro do usuário: " + e.getMessage());
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
				} else
					return false;
			}
			return false;
		} catch (SQLException e) {
			TratadorErros.mensagemErro("Erro Verificação;Erro verificação do email: " + e.getMessage());
		}
		return false;
	}

	public Usuario selectUsuario(String email) {
		String sql = "SELECT * FROM usuario WHERE email = ?";
		Usuario us = new Usuario();
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				us.setIdUsuario(rs.getInt("id_usuario"));
				us.setNome(rs.getString("nome"));
				us.setEmail(rs.getString("email"));
				us.setSenhaHash(rs.getString("senha"));
			}
		} catch (SQLException e) {
			TratadorErros.mensagemErro("Erro Seleção;Erro deletar de usuários: " + e.getMessage());
		}
		return us;
	}

	public boolean apagarUsuario(Usuario user) {
		String sql = "DELETE FROM usuario WHERE id_usuario = ?";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, user.getIdUsuario());
			ps.execute();
			return true;
		} catch (SQLException e) {
			TratadorErros.mensagemErro("Erro Deletar;Erro deletar usuário: " + e.getMessage());
		}
		return false;
	}

	public boolean verificarSenha(Usuario user, String senhaV) {
		String sql = "SELECT senha FROM usuario WHERE id_usuario = ?";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, user.getIdUsuario());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String senha = rs.getString("senha");
				return BCrypt.checkpw(senhaV, senha);
			}
			return false;
		} catch (SQLException e) {
			TratadorErros.mensagemErro("Erro Verificação;Erro verificação de senha: " + e.getMessage());
		}
		return false;
	}
}
