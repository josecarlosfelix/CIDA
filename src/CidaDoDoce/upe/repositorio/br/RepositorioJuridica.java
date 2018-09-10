package CidaDoDoce.upe.repositorio.br;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import CidaDoDoce.upe.interfeces.br.InterfeceGeral;
import CidaDoDoce.upe.negocio.br.Fisico;
import CidaDoDoce.upe.negocio.br.Juridica;

public class RepositorioJuridica implements InterfeceGeral<Juridica> {
	Conexao conexao = new Conexao();
	@Override
	public void insert(Juridica t) {
		
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

		conexao.executeSQL("insert into Juridica(cnpj,codigo_cliente) values('" + t.getCNPJ()
				+ "','" + codigo_cliente + "')");
		
	}

	@Override
	public ArrayList<Juridica> recoverAll(String S) {
		ArrayList<Juridica> juridicas = new ArrayList<>();
		ResultSet r = conexao.selectSQL(S);
		String cnpj = "";
		int id_cliente = 0;
		try {
			while(r.next()){
				cnpj = r.getString(1);
				id_cliente = r.getInt(2);
				juridicas.add(new Juridica(null, null, null, null, 0, null, null, 0, cnpj, id_cliente));
			}
		} catch (Exception e) {
		
		}
		
		return juridicas;
		
	}

	@Override
	public void update(Juridica t) {
		
		//DEVO COMPARA SE EXISTE ESSE CLIENTE JURIDICO ----------------------------------------
				String s = "select codigo_cliente from juridica where codigo_cliente = "+t.getId_cliente();
				
				ResultSet r = conexao.selectSQL(s);
				int id_cliente = -1;
				try {
					while(r.next()){
						id_cliente = r.getInt(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//CASO TENHA EXECUTE O UPDATE -------------------------------------------------------
				if(id_cliente == t.getId_cliente()){
					s = "update Juridica set cnpj = '"+t.getCNPJ()+"' where codigo_cliente = "+t.getId_cliente();
					conexao.executeSQL(s);
				}else{//ADICONA ELE AO CLIENTE JURIDICO E DELELA ELE NO CLIENTE FISICA ---------------------------------------
					insert(t);
					s = "delete from fisico where codigo_cliente = "+t.getId_cliente();
					conexao.executeSQL(s);
				}
		
	}

	@Override
	public void delete(Juridica t) {
		String s = "DELETE FROM Juridica WHERE codigo_cliente = "+t.getId_cliente();
		conexao.executeSQL(s);
		
		
	}

	@Override
	public ArrayList<Juridica> all() {
		// TODO Auto-generated method stub
		return null;
	}

}
