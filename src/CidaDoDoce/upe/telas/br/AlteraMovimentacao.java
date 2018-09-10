package CidaDoDoce.upe.telas.br;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import CidaDoDoce.upe.negocio.br.Venda;
import CidaDoDoce.upe.repositorio.br.RepositorioVenda;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AlteraMovimentacao extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldID;
	private JTextField textFieldData;
	private JTextField textFieldSaldo;
	private JTextField textFieldQuantidade;
	private JComboBox comboBoxPagou;
	private static String[] conteudoComboBox;

	public AlteraMovimentacao() {
		setResizable(false);
		setTitle("Altera Venda");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 311, 218);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(10, 11, 46, 14);
		contentPane.add(lblId);

		JLabel lblData = new JLabel("Data");
		lblData.setBounds(10, 36, 46, 14);
		contentPane.add(lblData);

		JLabel lblSaldo = new JLabel("Saldo");
		lblSaldo.setBounds(10, 61, 46, 14);
		contentPane.add(lblSaldo);

		JLabel lblPagou = new JLabel("Situa\u00E7\u00E3o");
		lblPagou.setBounds(10, 86, 80, 14);
		contentPane.add(lblPagou);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(10, 111, 80, 14);
		contentPane.add(lblQuantidade);

		textFieldID = new JTextField();
		textFieldID.setEditable(false);
		textFieldID.setBounds(96, 8, 199, 20);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);

		textFieldData = new JTextField();
		textFieldData.setBounds(96, 33, 199, 20);
		contentPane.add(textFieldData);
		textFieldData.setColumns(10);

		textFieldSaldo = new JTextField();
		textFieldSaldo.setBounds(96, 58, 199, 20);
		contentPane.add(textFieldSaldo);
		textFieldSaldo.setColumns(10);

		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setBounds(96, 108, 199, 20);
		contentPane.add(textFieldQuantidade);
		textFieldQuantidade.setColumns(10);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				boolean erro = false;
				try {
					
					/*//data
					String[] a = textFieldData.getText().split("/");
					int n1 = Integer.parseInt(a[0]);
					int n2 = Integer.parseInt(a[1]);
					int n3 = Integer.parseInt(a[2]);
					
					if(n1 <= 0 && n2 <= 0 && n3 <= 0){
						erro = true;
					}*/
					//quantidade
					Integer.parseInt(textFieldQuantidade.getText());
					
					//valor
					Double.parseDouble(textFieldSaldo.getText());
					
				} catch (NumberFormatException e) {
					erro = true;
				}

				if (erro == true) {
					JOptionPane.showMessageDialog(null, "Preencha esses campos corretamente ");
				} else {

					int id = Integer.parseInt(textFieldID.getText());
					String data = textFieldData.getText();
					double valor = Double.parseDouble(textFieldSaldo.getText());
					String pagou = comboBoxPagou.getSelectedItem().toString();
					int quantidade = Integer.parseInt(textFieldQuantidade.getText());

					Venda v = new Venda(id, data, valor, pagou, quantidade, 0, 0);

					RepositorioVenda repositorioVenda = new RepositorioVenda();
					repositorioVenda.update(v);
					// DELETE TODAS AS LINHAS -------------------------------
					Movimentacao.deletaAsLinhasDaTabela();
					
					// RECOLOCAR OS VALORES NA TABELA ------------------------
				
					DefaultTableModel m = (DefaultTableModel) Movimentacao.getTable().getModel();
					ArrayList<Venda> vendas = repositorioVenda.all();
					for (Venda venda : vendas) {

						String[] linha = { venda.getId_Venda() + "", venda.getData(), venda.getValor() + "",
								venda.getPagou(), venda.getQuantidade() + "", venda.getCodigo_doce() + "",
								venda.getCodigo_cliente() + "" };
						m.addRow(linha);

					}

					JOptionPane.showMessageDialog(null, "Registro Alterado");
				}
			}
		});
		btnNewButton.setBounds(206, 155, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Fechar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(10, 155, 89, 23);
		contentPane.add(btnNewButton_1);

		comboBoxPagou = new JComboBox(conteudoComboBox);
		comboBoxPagou.setBounds(96, 83, 199, 20);
		contentPane.add(comboBoxPagou);
	}

	public JTextField getTextFieldID() {
		return textFieldID;
	}

	public void setTextFieldID(JTextField textFieldID) {
		this.textFieldID = textFieldID;
	}

	public JTextField getTextFieldData() {
		return textFieldData;
	}

	public void setTextFieldData(JTextField textFieldData) {
		this.textFieldData = textFieldData;
	}

	public JTextField getTextFieldSaldo() {

		return textFieldSaldo;
	}

	public void setTextFieldSaldo(JTextField textFieldSaldo) {
		this.textFieldSaldo = textFieldSaldo;
	}

	/*
	 * public JTextField getTextFieldPagou() { return textFieldPagou; }
	 * 
	 * 
	 * public void setTextFieldPagou(JTextField textFieldPagou) {
	 * this.textFieldPagou = textFieldPagou; }
	 */

	public JTextField getTextFieldQuantidade() {
		return textFieldQuantidade;
	}

	public void setTextFieldQuantidade(JTextField textFieldQuantidade) {
		this.textFieldQuantidade = textFieldQuantidade;
	}

	public void setText(JTextField j, String t) {
		j.setText(t);
	}

	public static String[] getConteudoComboBox() {
		return conteudoComboBox;
	}

	public static void setConteudoComboBox(String[] conteudoComboBox2) {
		conteudoComboBox = conteudoComboBox2;
	}

	public JComboBox getComboBoxPagou() {

		return comboBoxPagou;
	}
}
