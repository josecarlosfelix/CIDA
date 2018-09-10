package CidaDoDoce.upe.repositorio.br;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import CidaDoDoce.upe.interfeces.br.InterfeceGeral;
import CidaDoDoce.upe.negocio.br.Doce;
import CidaDoDoce.upe.telas.br.Estoque;

public class RepositorioDoce implements InterfeceGeral<Doce> {
	
	Conexao conexao = new Conexao();
	public void insert(Doce t) {
		
		//ADICIONA OS VALORES EM DOCES MENOS A QUANTIDADE +++++++++++++++++++++++++++
		String instrucao = "insert into Doce(tipo,data_fabricação,peso,validade,preço,quantidade)"
					+ "values('"+t.getTipo()+"',to_date('"+t.getDataFabricacao()+"','dd/mm/yyyy'),"
						+ ""+t.getPeso()+",to_date('"+t.getDataDeValidade()+"','dd/mm/yyyy'),"+t.getPreço()+","+t.getQuantidade()+")";
		conexao.executeSQL(instrucao);
		
		
		//======================================================================================
		JOptionPane.showMessageDialog(null, "Registro dse Doce Adicionado");
	}
	
	
	@Override
	//ESSE SERVE PARA O RECOVE DOS DOCES NA TELA ESTOQUE
	public ArrayList<Doce> recoverAll(String S) {
		ArrayList<Doce> doces = new ArrayList<>();
		ResultSet r = conexao.selectSQL(S);
		
		String dataFabricacao,validade,tipo;
		int quantidade = 0,id_Doce = 0;
		double peso = 0, preco = 0;
		try {
			while(r.next()){
				 
				 tipo = r.getString(1);
				 dataFabricacao = r.getString(2);
				 peso = r.getDouble(3);
				 validade = r.getString(4);
				 preco = r.getDouble(5);
				 quantidade = r.getInt(6);
				 id_Doce = r.getInt(7);
				 doces.add(new Doce(id_Doce, tipo, dataFabricacao, peso, validade, preco, quantidade));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doces;
	}
	
	
	@Override
	public void update(Doce t) {
		
		String s = "update doce set tipo = '"+t.getTipo() +"' ,data_fabricação = '"+t.getDataFabricacao()+"' , peso = "+ t.getPeso()+", validade = '"+t.getDataDeValidade() +"',preço = "+t.getPreço()+", quantidade = "+t.getQuantidade()+" where id_doce = "+t.getId_Doce();

		conexao.executeSQL(s);
		
	}

	@Override
	public void delete(Doce t) {
		conexao.executeSQL("DELETE FROM doce WHERE id_doce = "+t.getId_Doce());
		JOptionPane.showMessageDialog(null, "Registro deletado");
	}

	@Override
	public ArrayList<Doce> all() {
		
		return null;
	}
//--------------------------------------------------------------------------------------
	//DELATA TODOS AS LINHA DA TABELA 
	public void deleterTabela(){
		DefaultTableModel   m =(DefaultTableModel)Estoque.table.getModel();
		m.setRowCount(0);
		
	}

}
