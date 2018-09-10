package CidaDoDoce.upe.telas.br;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import CidaDoDoce.upe.negocio.br.Venda;
import CidaDoDoce.upe.repositorio.br.RepositorioVenda;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JLayeredPane;

public class Movimentacao extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private JFormattedTextField textFieldData;
	
	private RepositorioVenda repositorioVenda = new RepositorioVenda();
	private DefaultTableModel m = null;
	
	/**
	 * Launch the application.
	 */
	public static void mostraMovimentacao() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Movimentacao frame = new Movimentacao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Movimentacao() {
		setTitle("Movimeta\u00E7\u00E3o do Dia ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 84, 641, 248);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Data", "Saldo", "Pagou", "Quantidade", "Produto", "Cliente"
			}
		)
				
		{
			boolean[] canEdit = new boolean [] {
                    false, false, false,false,false,false,false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
		}
				
		);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getColumnModel().getColumn(0).setPreferredWidth(48);
		scrollPane.setViewportView(table);
		
		JLabel lblDia = new JLabel("Data\r\n");
		lblDia.setBounds(19, 37, 46, 14);
		contentPane.add(lblDia);
		
		textFieldData = new JFormattedTextField();
		
		MaskFormatter mask;
		try {
			mask = new MaskFormatter("##/##/####"); // MASCARA NOS CAMPOS --------------
			mask.install(textFieldData);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		textFieldData.setBounds(49, 34, 270, 20);
		contentPane.add(textFieldData);
		textFieldData.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// ISSO TUDO SÓ PARA VERIFICAR SE A DATA É VALIDA -----------------------
				boolean erro = false;
				try{
				String[] s = textFieldData.getText().split("/");
				int n1 = Integer.parseInt(s[0]);
				int n2 = Integer.parseInt(s[1]);
				int n3 = Integer.parseInt(s[2]);
				if(n1 <= 0 && n2 <= 0 && n3 <= 0){
					erro = true;
				}
				}catch(NumberFormatException e3){
					erro = true;
				}
				if(erro == true){
					JOptionPane.showMessageDialog(null, "Informe uma data valida");
				}
				// SE FOR VALIDA EXECUTE ESSA OUTRA COISA ------------------------------- 
				
				else{
					deletaAsLinhasDaTabela();
					m = (DefaultTableModel)table.getModel();
					ArrayList<Venda>vendas = repositorioVenda.recoverAll("select id_venda, data, valor, pago, quantidade, codigo_cliente, codigo_doce from venda where data = '"+textFieldData.getText()+"' order by id_venda");
					for (Venda venda : vendas) {
						
						String [] linha = {venda.getId_Venda()+"",venda.getData(),venda.getValor()+"",venda.getPagou(),venda.getQuantidade()+"",venda.getCodigo_doce()+"",venda.getCodigo_cliente()+""};
						m.addRow(linha);
					
					}
					
				}
			}
		});
		btnBuscar.setBounds(329, 33, 89, 23);
		contentPane.add(btnBuscar);
		
		JButton btnVoltar = new JButton("Voltar ");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Menu2.mostraMenu2();
			}
		});
		btnVoltar.setBounds(19, 351, 89, 23);
		contentPane.add(btnVoltar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m = (DefaultTableModel)table.getModel();
				if(table.getSelectedRow() < 0 ){
					JOptionPane.showMessageDialog(null, "Seleciona uma linha");
				}else{
					int linha  = table.getSelectedRow();
					
					// ISSO TUDO PARA ENVIAR POR COMBOXBOX ----------------------------------
					
					if(table.getValueAt(linha, 3).toString().equals("Sim")){
						String[] conteudo = {"Sim","Não"};
						AlteraMovimentacao.setConteudoComboBox(conteudo);
					}else{
						String[] conteudo = {"Não","Sim"};
						AlteraMovimentacao.setConteudoComboBox(conteudo);
					}
					
					AlteraMovimentacao frame = new AlteraMovimentacao();
					
					frame.setText(frame.getTextFieldID(),table.getValueAt(linha, 0).toString());
					frame.setText(frame.getTextFieldData(),table.getValueAt(linha, 1).toString());
					frame.setText(frame.getTextFieldSaldo(),table.getValueAt(linha, 2).toString());
					
					frame.setText(frame.getTextFieldQuantidade(),table.getValueAt(linha, 4).toString());
					frame.setVisible(true);
				}
			}
		});
		btnAlterar.setBounds(571, 351, 89, 23);
		contentPane.add(btnAlterar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRow() < 0){
					JOptionPane.showMessageDialog(null, "Por favor selecione uma linha");
				}else{
					m = (DefaultTableModel)table.getModel();
					int linha  = table.getSelectedRow();
					Venda t = new Venda(Integer.parseInt(table.getValueAt(linha, 0).toString()), null, 0, null, 0, 0, 0);
					repositorioVenda.delete(t);
					m.removeRow(linha);
				}
			}
		});
		btnDeletar.setBounds(474, 351, 89, 23);
		contentPane.add(btnDeletar);
		
		JButton btnTodos = new JButton("Todos");
		btnTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletaAsLinhasDaTabela();
				m = (DefaultTableModel)table.getModel();
				ArrayList<Venda>vendas = repositorioVenda.all();
				for (Venda venda : vendas) {
					
					String [] linha = {venda.getId_Venda()+"",venda.getData(),venda.getValor()+"",venda.getPagou(),venda.getQuantidade()+"",venda.getCodigo_doce()+"",venda.getCodigo_cliente()+""};
					m.addRow(linha);
				
				}
			}
		});
		btnTodos.setBounds(431, 33, 89, 23);
		contentPane.add(btnTodos);
	}
	public static void deletaAsLinhasDaTabela(){
		DefaultTableModel m2 = (DefaultTableModel)table.getModel();
		m2.setRowCount(0);
	}

	public static JTable getTable() {
		return table;
	}

	public static void setTable(JTable table) {
		Movimentacao.table = table;
	}

	
}
