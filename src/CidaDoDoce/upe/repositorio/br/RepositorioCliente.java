package CidaDoDoce.upe.repositorio.br;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import CidaDoDoce.upe.interfeces.br.InterfeceGeral;
import CidaDoDoce.upe.negocio.br.Cliente;
import CidaDoDoce.upe.telas.br.AlteraCadastro;
import CidaDoDoce.upe.telas.br.Cadastro2;
import CidaDoDoce.upe.telas.br.Clientes;

public class RepositorioCliente implements InterfeceGeral<Cliente> {

	Conexao conexao = new Conexao();
	
	public ArrayList<Cliente> recoverAll(String S) {
		
		ArrayList<Cliente> clientes = new ArrayList<>();
		
		ResultSet rs =  conexao.selectSQL(S);
		ResultSet rs2 =  null;
		String nome = "";
		String endereco="";
		int id_cliente = 0;
		ArrayList<String> fone = null;
		DefaultTableModel m = null;
		int x = 0;
		
//-----------------------------------------------------------------------------------------------		
		//LOOP DE REPETICÃO ADICIONANDO AOS CLIENTES DO BANCO AO ARRAY PRINCIPAL 
		try {
			while(rs.next()){
				
				nome = rs.getString(1);
				endereco = rs.getString(2);
				id_cliente = rs.getInt(3);

				rs2 = conexao.selectSQL("select numero from telefone where codigo_cliente = "+id_cliente);
				
				fone = new ArrayList<>();
				fone.add("");
				fone.add("");
								
				while(rs2.next()){
					fone.set(x,rs2.getString(1));
					x++;
				}
				x = 0;
					
				clientes.add(new Cliente(nome,endereco, fone, id_cliente));
								
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clientes;
		
	}

	@Override
	public void update(Cliente t) {
		//BOM PEGUEI O ENDEREÇO DA TABELA PORQUE DEMORARIA MUITO PEGA CADA CAMPO E JUNTA PARA FORMA O ENDEREÇO
		
		String s = "update cliente set nome = '"+t.getNome()+"' ,endereço = '"+AlteraCadastro.getEndereco().getText()+"' where id_cliente = "+t.getId_cliente();
		conexao.executeSQL(s);
		s  = "select id_telefone from telefone where codigo_cliente = "+t.getId_cliente();
		ResultSet r = conexao.selectSQL(s);
		String []id_telefone = {"",""};
		int x = 0;

		try {
			while(r.next()){
				id_telefone[x] = r.getString(1);
				x++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		s = "update telefone set numero = '"+t.getFone().get(0)+"' where id_telefone = "+id_telefone[0];
		conexao.executeSQL(s);
		s = "update telefone set numero = '"+t.getFone().get(1)+"' where id_telefone = "+id_telefone[1];
		conexao.executeSQL(s);
		

	}

	@Override
	public void delete(Cliente t) {
		
		String s2 = "DELETE FROM Telefone WHERE codigo_cliente = "+t.getId_cliente();
		conexao.executeSQL(s2);
		String s = "DELETE FROM cliente WHERE id_cliente = "+t.getId_cliente();
		conexao.executeSQL(s);
		JOptionPane.showMessageDialog(null, "Registro Deletado");

	}
	
	
	
	
	@Override
	public ArrayList<Cliente> all() {
ArrayList<Cliente> clientes = new ArrayList<>();
		
		ResultSet rs =  conexao.selectSQL("select nome,endereço,id_cliente from cliente");
		ResultSet rs2 =  null;
		String nome = "";
		String endereco="";
		int id_cliente = 0;
		ArrayList<String> fone = null;
		DefaultTableModel m = null;
		int x = 0;
		
		try {
			while(rs.next()){
				
				nome = rs.getString(1);
				endereco = rs.getString(2);
				id_cliente = rs.getInt(3);

				rs2 =  conexao.selectSQL("select numero from telefone where codigo_cliente = "+id_cliente);
				
				// DEVO INICIAR COM ALGO POIS PODE DA PROBLEMA CASO NÃO EXISTA NUMEROS CADASTRADOS 
				fone = new ArrayList<>();
				fone.add("");
				fone.add("");
								
				while(rs2.next()){
					fone.set(x,rs2.getString(1));
					x++;
				}
				x = 0;
				clientes.add(new Cliente(nome,endereco,fone, id_cliente));
								
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clientes;
	}

	@Override
	public void insert(Cliente t) {
		
		
		String endereco = t.Endereco();
		
		conexao.executeSQL("insert into cliente(endereço,nome) values('" + endereco + "','"
				+ t.getNome() + "')");
		int id_cliente = 0;
		
		ResultSet r = conexao.selectSQL("select id_cliente from cliente");
		try {
			while(r.next()){
				id_cliente  = r.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String s = "insert into telefone(numero,codigo_cliente) values('"+t.getFone().get(0)+"',"+id_cliente+")";
		conexao.executeSQL(s);
		s = "insert into telefone(numero,codigo_cliente) values('"+t.getFone().get(1)+"',"+id_cliente+")";
		conexao.executeSQL(s);
	}
	
	//ISSO FUNCIONA QUANDO VAI CADASTRO POIS ELE PEGA O NOVO REGISTRO
	public int buscaUltimoIDCliente(){
		
		ResultSet r = conexao.selectSQL("select id_cliente from cliente");
		int id = 0;
		try {
			while(r.next()){
				id = r.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id+1;
	}	
}
