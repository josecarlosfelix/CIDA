package CidaDoDoce.upe.telas.br;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import CidaDoDoce.upe.negocio.br.Doce;
import CidaDoDoce.upe.repositorio.br.RepositorioDoce;


public class Estoque extends JFrame {

	private JPanel contentPane;
	public static JTable table;
	private JTextField textField;
	RepositorioDoce repositorioDoce = new RepositorioDoce();
	/**
	 * Launch the application.
	 */
	public static void mostraEstoque() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Estoque frame = new Estoque();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Estoque() {
		setTitle("Estoque de produtos ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 88, 660, 240);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"ID_Produto", "Tipo", "Fabricado Em", "Valido At\u00E9", "Quantidade"
			}
		)
		);
		scrollPane.setViewportView(table);
		
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setBounds(10, 39, 65, 14);
		contentPane.add(lblProduto);
		
		textField = new JTextField();
		textField.setBounds(54, 36, 502, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		 btnBuscar.addActionListener(new ActionListener() {
			 // RETORNA TODOS OS DOCES E COLOCA NA TEBELA ------------------------------------
			public void actionPerformed(ActionEvent e) {
				repositorioDoce.deleterTabela();
				ArrayList<Doce>doces = repositorioDoce.recoverAll("select tipo,data_fabricação,peso,validade,preço,quantidade,id_doce from doce where tipo like'%"+textField.getText()+"%'");
				DefaultTableModel m = null;
				for (Doce doce : doces) {
					m = (DefaultTableModel)table.getModel();
					String[] linha = {doce.getId_Doce()+"",doce.getTipo(),doce.getDataFabricacao(),doce.getDataDeValidade(),doce.getQuantidade()+""};
					m.addRow(linha);
				}
				textField.setText("");
			}
		});
		btnBuscar.setBounds(581, 35, 89, 23);
		contentPane.add(btnBuscar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Menu2.mostraMenu2();
			}
		});
		btnVoltar.setBounds(10, 366, 89, 23);
		contentPane.add(btnVoltar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ALTERA NA TABELA DOCE PEGANDO A LINHA SELECIONADA -----------------------------------------
				int linha = table.getSelectedRow();
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				int id_doce = Integer.parseInt(m.getValueAt(linha, 0).toString());
//------------------------------------------------------------------------------------------------------------
				
				//BUSCA UM DOCE COM O SELECT DO ID_DOCE  -----------------------------------------------------
				ArrayList<Doce> doce = repositorioDoce.recoverAll("select tipo,data_fabricação,peso,validade,preço,quantidade,id_doce from doce where id_doce = "+id_doce);
				
				//CHAMA A OUTRA TELA E FECHA ESSA ----------------------------------------------------------
				Mercadoria mercadoria = new Mercadoria();
				mercadoria.setText(doce.get(0));
				mercadoria.setVisible(true);
				dispose();
				
			}
		});
		btnAlterar.setBounds(581, 366, 89, 23);
		contentPane.add(btnAlterar);
		
		JButton btnDeleta = new JButton("Deletar");
		btnDeleta .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int linha = table.getSelectedRow();
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				int id_doce = Integer.parseInt(m.getValueAt(linha, 0).toString());
				Doce doce = new Doce(id_doce,null,null,0,null,0,0);
				
				//	DELETA O REGISTRO CONFORME O ID QUE ESTA SELECIONADO -------------------------------------------
				repositorioDoce.delete(doce);
				((DefaultTableModel)table.getModel()).removeRow(table.getSelectedRow());
			}
		});
		btnDeleta.setBounds(482, 366, 89, 23);
		contentPane.add(btnDeleta);
	}
}
