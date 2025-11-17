package controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;

public class Conexao {
private Conexao() {}
	
	public static Connection getConexao() throws IOException {
		Properties propriedades = Manipulator.getProp();
        try {
            return (Connection) DriverManager.getConnection("jdbc:" + propriedades.getProperty("sgbd") + "://" + propriedades.getProperty("host") + 
            		"/" + propriedades.getProperty("bd") + "", propriedades.getProperty("login") , propriedades.getProperty("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
}
