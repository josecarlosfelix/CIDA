package CidaDoDoce.upe.repositorio.br;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import CidaDoDoce.upe.interfeces.br.InterfeceGeral;
import CidaDoDoce.upe.negocio.br.Fisico;

public class RepositorioFisico implements InterfeceGeral<Fisico> {
	
	Conexao conexao = new Conexao();
	
	@Override
	public void insert(Fisico t) {
		int codigo_cliente = 0;
		
		ResultSet resultSet = conexao.selectSQL("select id_cliente from cliente");
		try {
			while (resultSet.next()) {
				codigo_cliente = resultSet.getInt(1);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		conexao.executeSQL("insert into Fisico(cpf,codigo_cliente) values('" + t.getCPF()
				+ "','" + codigo_cliente + "')");
	}

	@Override
	public ArrayList<Fisico> recoverAll(String S) {
		ArrayList<Fisico> fisicos = new ArrayList<>();
		ResultSet r = conexao.selectSQL(S);
		String cpf = "";
		int id_cliente = 0;
		try {
			while(r.next()){
				cpf = r.getString(1);
				id_cliente = r.getInt(2);
				fisicos.add(new Fisico(null, null, null, null, 0, null, null, 0, cpf, id_cliente));
			}
		} catch (Exception e) {
		
		}
		
		return fisicos;
	}

	@Override
	public void update(Fisico t) {

		//DEVO COMPARA SE EXISTE ESSE CLIENTE FISICO -----------------------------------
		String s = "select codigo_cliente from fisico where codigo_cliente = "+t.getId_cliente();
		
		ResultSet r = conexao.selectSQL(s);
		int id_cliente = -1;
		try {
			while(r.next()){
				id_cliente = r.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//CASO TENHA EXECUTE O UPDATE --------------------------------------------
		if(id_cliente == t.getId_cliente()){
			s ="update fisico set cpf = '"+t.getCPF()+"' where codigo_cliente = "+t.getId_cliente();
			conexao.executeSQL(s);
		}else{//CASO NÃO ADICONA ELE AO CLIENTE FISICO E DELELA ELE NO CLIENTE JURIDICO --------------
			insert(t);
			s = "delete from juridica where codigo_cliente = "+t.getId_cliente();
			conexao.executeSQL(s);
		}
	}

	@Override
	public void delete(Fisico t) {
		String s = "DELETE FROM fisico WHERE codigo_cliente = "+t.getId_cliente();
		conexao.executeSQL(s);
		
		
	}

	@Override
	public ArrayList<Fisico> all() {
		// TODO Auto-generated method stub
		return null;
	}

}
