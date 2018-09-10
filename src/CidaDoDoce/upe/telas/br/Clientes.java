package CidaDoDoce.upe.telas.br;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import CidaDoDoce.upe.negocio.br.Cliente;
import CidaDoDoce.upe.negocio.br.Fisico;
import CidaDoDoce.upe.negocio.br.Juridica;
import CidaDoDoce.upe.repositorio.br.Formatando;
import CidaDoDoce.upe.repositorio.br.RepositorioCliente;
import CidaDoDoce.upe.repositorio.br.RepositorioFisico;
import CidaDoDoce.upe.repositorio.br.RepositorioJuridica;

public class Clientes extends JFrame {

	private JPanel contentPane;
	public  static JTable table;
	private JTextField nomeC;
	private RepositorioCliente repositorioCliente = new RepositorioCliente();
	private RepositorioFisico repositorioFisico = new RepositorioFisico();
	private RepositorioJuridica repositorioJuridica = new RepositorioJuridica();
	private DefaultTableModel m = null;
	
	public static void mostraCliente() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes frame = new Clientes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Clientes() {
		setTitle("Buscar clientes");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 108, 660, 309);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Nome", "CPF/CNPJ", "Endere\u00E7o", "Celular", "Telefone"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(38);
		table.getColumnModel().getColumn(1).setPreferredWidth(83);
		table.getColumnModel().getColumn(2).setPreferredWidth(95);
		table.getColumnModel().getColumn(3).setPreferredWidth(398);
		scrollPane.setViewportView(table);
		
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(10, 45, 46, 14);
		contentPane.add(lblNome);
		
		nomeC = new JTextField();
		nomeC.setDocument(new Formatando(45));
		nomeC.setBounds(55, 43, 516, 20);
		contentPane.add(nomeC);
		nomeC.setColumns(10);
		
		
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				//ADICIONA OS VALORES DA TABLE CLIENTE A TELA CLIENTES -----------------------------------------
				if(nomeC.getText().equals("")){
					deleterTabela();
					ArrayList<Cliente> clientes = repositorioCliente.all();
					ArrayList<Fisico> fisicos = repositorioFisico.recoverAll("select cpf,codigo_cliente from fisico");
					ArrayList<Juridica>juridicas = repositorioJuridica.recoverAll("select cnpj,codigo_cliente from juridica");
					
					m =(DefaultTableModel)Clientes.table.getModel();
					
					for (Cliente cliente : clientes) {
						String fone = cliente.getFone().get(0);
						String fone_2 = cliente.getFone().get(1);
						
						for(int i =0 ; i < fisicos.size();i++){
							if(fisicos.get(i).getId_cliente() == cliente.getId_cliente()){
								String[] linha = {cliente.getId_cliente()+"",cliente.getNome(),fisicos.get(i).getCPF(),cliente.getEndereco(),fone,fone_2};
								m.addRow(linha);
							}
						}
						for (int i = 0; i < juridicas.size(); i++) {
							if(juridicas.get(i).getId_cliente() == cliente.getId_cliente()){
								String[] linha = {cliente.getId_cliente()+"",cliente.getNome(),juridicas.get(i).getCNPJ(),cliente.getEndereco(),fone,fone_2};
								m.addRow(linha);
							}
						}
						
					}
				}
				
				//BUSCA PERSONALIZADA --------------------------------------------------------------------------------- ---
				else {
					deleterTabela();
					ArrayList<Cliente>clientes = repositorioCliente.recoverAll("select nome,endereço,id_cliente from cliente where nome like '%"+nomeC.getText()+"%'");
					ArrayList<Fisico> fisicos = repositorioFisico.recoverAll("select cpf,codigo_cliente from fisico");
					ArrayList<Juridica>juridicas = repositorioJuridica.recoverAll("select cnpj,codigo_cliente from juridica");
					
					m =(DefaultTableModel)Clientes.table.getModel();
					
					for (Cliente cliente : clientes) {
						String fone = cliente.getFone().get(0);
						String fone_2 = cliente.getFone().get(1);
						
						for(int i =0 ; i < fisicos.size();i++){
							if(fisicos.get(i).getId_cliente() == cliente.getId_cliente()){
								String[] linha = {cliente.getId_cliente()+"",cliente.getNome(),fisicos.get(i).getCPF(),cliente.getEndereco(),fone,fone_2};
								m.addRow(linha);
							}
						}
						for (int i = 0; i < juridicas.size(); i++) {
							if(juridicas.get(i).getId_cliente() == cliente.getId_cliente()){
								String[] linha = {cliente.getId_cliente()+"",cliente.getNome(),juridicas.get(i).getCNPJ(),cliente.getEndereco(),fone,fone_2};
								m.addRow(linha);
							}
						}
						
					}
					limpaCampos();
					
				}
				
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBuscar.setBounds(581, 41, 89, 23);
		contentPane.add(btnBuscar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Menu2.mostraMenu2();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnVoltar.setBounds(10, 437, 89, 23);
		contentPane.add(btnVoltar);
		
		JButton btnApagar = new JButton("Apagar ");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				int id_cliente = Integer.parseInt(m.getValueAt(linha, 0).toString());
				String identificador = m.getValueAt(linha, 2).toString();
				
				
				if(identificador.length() <= 14){
					Fisico f = new Fisico(null, null, null, null, 0, null, null, 0, null, id_cliente);
					f.setId_cliente(id_cliente);;
					repositorioFisico.delete(f);
					repositorioCliente.delete((Cliente)f);
				}
				
				else if(identificador.length() >= 18){
						Juridica j = new Juridica(null, null, null, null, 0, null, null, 0, null, id_cliente);						
						repositorioJuridica.delete(j);
						repositorioCliente.delete((Cliente)j);
				}

				((DefaultTableModel)table.getModel()).removeRow(table.getSelectedRow());
			}
		}); 
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnApagar.setBounds(382, 436, 89, 23);
		contentPane.add(btnApagar);
		
		JButton btnAdicionar = new JButton("Adicionar ");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Cadastro2.mostraCadastro2();
			}
		}); 
		btnAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAdicionar.setBounds(581, 436, 89, 23);
		contentPane.add(btnAdicionar);
		
		JLabel lblTabela = new JLabel("Tabela ");
		lblTabela.setBounds(278, 274, 46, 14);
		contentPane.add(lblTabela);
		
		JButton btnAltera = new JButton("Altera");
		btnAltera.setEnabled(true);
		btnAltera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int linha = table.getSelectedRow();
				
				AlteraCadastro ac = new AlteraCadastro();
				int id = Integer.parseInt(getValorTabela(linha, 0)) ;
				String nome = getValorTabela(linha, 1);
				String identificador =getValorTabela(linha, 2);
				String endereço = getValorTabela(linha, 3);
				String celular = getValorTabela(linha, 4);
				String fixo = getValorTabela(linha, 5);
				String[] string = {id+"",nome,identificador,endereço,celular,fixo};
				ac.setText(string);
				ac.setVisible(true);
				
				
				
			}
		});
		btnAltera.setBounds(482, 436, 89, 23);
		contentPane.add(btnAltera);
	}
	public void limpaCampos(){
		
		nomeC.setText("");
	}
	public String getValorTabela(int x, int y){
		DefaultTableModel m = (DefaultTableModel)table.getModel();
		try{
			return m.getValueAt(x, y).toString() ;
		}catch(Exception e){
			return "";
		}
	}
	
	//DELATA TODOS AS LINHA DA TABELA  --------------------------------------------------------
		public void deleterTabela(){
			DefaultTableModel   m =(DefaultTableModel)Clientes.table.getModel();
			m.setRowCount(0);
			
		}
	
}
