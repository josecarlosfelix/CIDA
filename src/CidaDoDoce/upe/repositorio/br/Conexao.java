package CidaDoDoce.upe.repositorio.br;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
	private Connection con;
	private String url;
	private String senha;
	private String usuario;

	public Conexao() {

		url = "jdbc:postgresql://localhost:5432/Cida";
		usuario = "postgres";
		senha = "root";

		try {

			Class.forName("org.postgresql.Driver");

			con = DriverManager.getConnection(url, usuario, senha);
			// System.out.println("CONEXÃO REALIZADA");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("erro class");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("erro conexao");
		}

	}

	public int executeSQL(String sql) {
		try {
			Statement stm = con.createStatement();
			int res = stm.executeUpdate(sql);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public ResultSet selectSQL(String sql) {
		try {
			Statement stm = con.createStatement();
			ResultSet resultset = stm.executeQuery(sql);
			return resultset;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
