package CidaDoDoce.upe.repositorio.br;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import CidaDoDoce.upe.interfeces.br.InterfeceGeral;
import CidaDoDoce.upe.negocio.br.Format;
import CidaDoDoce.upe.negocio.br.Venda;

public class RepositorioVenda implements InterfeceGeral<Venda> {
	
	Conexao conexao = new Conexao();
	
	public void insert(Venda t) {
		String s = "insert into venda (data,valor,pago,codigo_doce,codigo_cliente,quantidade) "
				+ "values(to_date('"+t.getData()+"','dd/mm/yyyy'),"+t.getValor()+",'"+t.getPagou()+"',"+t.getCodigo_doce()+","+t.getCodigo_cliente()+","+t.getQuantidade()+")";
		
		conexao.executeSQL(s);
	}

	@Override
	public ArrayList<Venda> recoverAll(String S) {
		ArrayList<Venda> venda = new ArrayList<>();
		ResultSet r = conexao.selectSQL(S);
		
		int id_venda = 0;
		String data = "";
		double valor = 0; 
		String pagou = "";
		int quantidade= 0;
		int codigo_cliente = 0;
		int codigo_doce = 0;
		
		try {
			while(r.next()){
				id_venda = r.getInt(1);
				data = Format.formato1(r.getString(2));
				valor = r.getDouble(3);
				pagou = r.getString(4);
				quantidade = r.getInt(5);
				codigo_cliente = r.getInt(6);
				codigo_doce = r.getInt(7);
				venda.add(new Venda(id_venda, data, valor, pagou, quantidade, codigo_cliente, codigo_doce));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return venda;
	}

	@Override
	public void update(Venda t) {
		String sql = "update venda set  data = '"+Format.formato2(t.getData())+"', valor = "+t.getValor()+",pago = '"+t.getPagou()+"',quantidade = "+t.getQuantidade()+" where id_venda = "+t.getId_Venda();
		
		conexao.executeSQL(sql);
		
	}

	@Override
	public void delete(Venda t) {
		int id_venda = t.getId_Venda();
		conexao.executeSQL("delete from venda where id_venda = "+id_venda);
	}

	@Override
	public ArrayList<Venda> all() {
		ArrayList<Venda> venda = new ArrayList<>();
		String sql = "select id_venda, data, valor, pago, quantidade, codigo_cliente, codigo_doce from venda order by id_venda";
		ResultSet r = conexao.selectSQL(sql);
		
		int id_venda = 0;
		String data = "";
		double valor = 0; 
		String pagou = "";
		int quantidade= 0;
		int codigo_cliente = 0;
		int codigo_doce = 0;
		
		try {
			while(r.next()){
				id_venda = r.getInt(1);
				data = Format.formato1(r.getString(2));
				valor = r.getDouble(3);
				pagou = r.getString(4);
				quantidade = r.getInt(5);
				codigo_cliente = r.getInt(6);
				codigo_doce = r.getInt(7);
				venda.add(new Venda(id_venda, data, valor, pagou, quantidade, codigo_cliente, codigo_doce));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return venda;
	}

}
