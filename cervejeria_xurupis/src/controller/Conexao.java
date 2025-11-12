package controller;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class Conexao {
private Conexao() {}
	
	public static Connection getConexao() {
        try {
            return (Connection) DriverManager.getConnection("jdbc:mysql://localhost/cervejeria", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
}
