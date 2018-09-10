package CidaDoDoce.upe.telas.br;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import CidaDoDoce.upe.negocio.br.Cliente;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MultiplosClientes extends JFrame {

	private JPanel contentPane;
	private JTable table;

	
	public MultiplosClientes() {
		setResizable(false);
		setTitle("Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 237);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 354, 155);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"ID", "Nome"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() >-1){
					int linha = table.getSelectedRow();
					
					
					Compras.getTextFieldID().setText(table.getValueAt(linha, 0).toString());
					Compras.getTextFieldCliente().setText(table.getValueAt(linha, 1).toString());
					
					dispose();
				
					
				}else{
					JOptionPane.showMessageDialog(null, "Por Favor selecione a porra da linha!!!");
				}
			}
		});
		btnNewButton.setBounds(275, 174, 89, 23);
		contentPane.add(btnNewButton);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table2) {
		table = table2;
	}

	public void addRons(ArrayList<Cliente> cliente) {
		
		DefaultTableModel m = (DefaultTableModel)table.getModel();
		m.setRowCount(0);
		for (int j = 0; j < cliente.size(); j++) {
			String[] linha = {cliente.get(j).getId_cliente()+"",cliente.get(j).getNome()};
			m.addRow(linha);
			//System.out.println(cliente.get(j).getId_cliente()+"  "+cliente.get(j).getNome());
		}
		
	}
	
}
