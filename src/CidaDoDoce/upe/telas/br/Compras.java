package CidaDoDoce.upe.telas.br;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
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

import CidaDoDoce.upe.negocio.br.Cliente;
import CidaDoDoce.upe.negocio.br.Doce;
import CidaDoDoce.upe.repositorio.br.RepositorioCliente;
import CidaDoDoce.upe.repositorio.br.RepositorioDoce;

public class Compras extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private static JFormattedTextField textFieldNome;
	private RepositorioCliente repositorioCliente = new RepositorioCliente();
	private static JTextField textFieldCliente;
	private static JTextField textFieldID;
	private JTextField textFieldData;
	/**
	 * Launch the application.
	 */
	public static void mostraCompras() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Compras frame = new Compras();
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
	public Compras() {
		setTitle("Compras\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 678, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 645, 218);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"ID Doce", "Tipo", "Peso", "Quantidade", "Pre\u00E7o", "Situa\u00E7\u00E3o"
			}
			
			
		)
				
		{
			boolean[] canEdit = new boolean [] {
                    false, false, false,false,false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
		}		
		);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 31, 46, 14);
		contentPane.add(lblNome);
		
		textFieldNome = new JFormattedTextField();
		try {
			MaskFormatter m = new MaskFormatter("*********************************");
			m.install(textFieldNome);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		textFieldNome.setBounds(53, 28, 503, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textFieldNome.getText().trim().equals("")){
					textFieldCliente.setText("");
					textFieldNome.setText("");
					textFieldID.setText("");
					DefaultTableModel m =(DefaultTableModel) table.getModel();
					m.setRowCount(0);
					JOptionPane.showMessageDialog(null, "Informe o nome do cliente");
				}else{
					ArrayList<Cliente>cliente = repositorioCliente.recoverAll("select nome,endereço,id_cliente from cliente where nome = '"+textFieldNome.getText().trim()+"'");
					
					textFieldCliente.setText("");
					textFieldID.setText("");
					DefaultTableModel m =(DefaultTableModel) table.getModel();
					m.setRowCount(0);
					
//-----------------//CASO EXISTA UM CLIENTE ------------------------------
				
					if(cliente.size() == 1){
						
						textFieldCliente.setText(textFieldNome.getText());
						
						textFieldID.setText(cliente.get(0).getId_cliente()+"");
//---------------------------------------------------------------------------------------------------------------------					
					// ESSA OUTRO SERVE PARA CASO O USUARIO ESCOLHER E TENHA MAIS DE UM CLIENTE  
					}else if(cliente.size()> 1){
						JOptionPane.showMessageDialog(null, "A mais de um cliente com esse nome escolha um dessa tabela a seguir");
						
						try {
							MultiplosClientes frame = new MultiplosClientes();
							frame.addRons(cliente);
							frame.setVisible(true);
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}else{
						JOptionPane.showMessageDialog(null, "Esse Elemento(Cliente) não existe");
					}
				}
			}
		});
		btnBuscar.setBounds(566, 27, 89, 23);
		contentPane.add(btnBuscar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu2.mostraMenu2();
				dispose();
			}
		});
		btnVoltar.setBounds(10, 328, 89, 23);
		contentPane.add(btnVoltar);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()<-1){
					JOptionPane.showMessageDialog(null, "Por favor selecione uma linha");
				}
				//DELETA NA TABELA E ALTERA NO BANCO  ------------------------------
				else{
					//ALTEROU NO BANCO --------------------------------------
					RepositorioDoce repositorioDoce = new RepositorioDoce();
					int linha = table.getSelectedRow();
					int id_doce = Integer.parseInt(table.getValueAt(linha, 0).toString());
					String sql = "select tipo,data_fabricação,peso,validade,preço,quantidade,id_doce from doce where id_doce = "+id_doce;
							
					ArrayList<Doce>doce = repositorioDoce.recoverAll(sql);
					
					int quantidade = Integer.parseInt(table.getValueAt(linha, 3).toString());
					doce.get(0).setQuantidade(doce.get(0).getQuantidade()+quantidade);;
					repositorioDoce.update(doce.get(0));
					
					//REMOVEL DA TABELA ---------------------------------------------- 
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					m.removeRow(linha);
				}
			}
		});
		btnApagar.setBounds(467, 328, 89, 23);
		contentPane.add(btnApagar);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldID.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Esta sem Cliente. Informe um");
				}else{
					AdicionaCompras.mostraAdicionaCompras();
				}
				
				
			}
		});
		btnAdicionar.setBounds(566, 328, 89, 23);
		contentPane.add(btnAdicionar);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(10, 56, 46, 22);
		contentPane.add(lblCliente);
		
		textFieldCliente = new JTextField();
		textFieldCliente.setEditable(false);
		textFieldCliente.setBounds(53, 59, 189, 20);
		contentPane.add(textFieldCliente);
		textFieldCliente.setColumns(10);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(252, 59, 46, 14);
		contentPane.add(lblId);
		
		textFieldID = new JTextField();
		textFieldID.setEditable(false);
		textFieldID.setBounds(279, 58, 86, 20);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(375, 59, 46, 14);
		contentPane.add(lblData);
		
		textFieldData = new JTextField();
		textFieldData.setBounds(414, 57, 142, 20);
		contentPane.add(textFieldData);
		textFieldData.setColumns(10);
		
		
	}

	public static JTextField getTextFieldNome() {
		return textFieldNome;
	}

	public static void setTextFieldNome(JFormattedTextField textFieldNome) {
		Compras.textFieldNome = textFieldNome;
	}

	
	public static JTextField getTextFieldCliente() {
		return textFieldCliente;
	}

	public static void setTextFieldCliente(JTextField textFieldCliente_1) {
		Compras.textFieldCliente = textFieldCliente_1;
	}

	public static JTextField getTextFieldID() {
		return textFieldID;
	}

	public static void setTextFieldID(JTextField textFieldID) {
		Compras.textFieldID = textFieldID;
	}

	public static JTable getTable() {
		return table;
	}

	public static void setTable(JTable table) {
		Compras.table = table;
	}
}
