package CidaDoDoce.upe.telas.br;

import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import CidaDoDoce.upe.negocio.br.Doce;
import CidaDoDoce.upe.repositorio.br.RepositorioDoce;

public class AdicionaCompras extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBoxPeso;
	private JComboBox comboBoxDoce;
	private JComboBox comboBoxPago;
	private RepositorioDoce repositorioDoce = new RepositorioDoce();
	private JTextField quantidade;

	public static void mostraAdicionaCompras() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdicionaCompras frame = new AdicionaCompras();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdicionaCompras() {
		setTitle("Adicionar");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 278, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDoe = new JLabel("Do\u00E7e");
		lblDoe.setBounds(10, 11, 46, 14);
		contentPane.add(lblDoe);

		ArrayList<Doce> doce = repositorioDoce
				.recoverAll("select tipo,data_fabricação,peso,validade,preço,quantidade,id_doce from doce");
		String[] doceTipo = new String[doce.size()];
		String[] docePeso = new String[doce.size()];
		for (int i = 0; i < docePeso.length; i++) {
			docePeso[i] = doce.get(i).getPeso() + "";
			doceTipo[i] = doce.get(i).getTipo();
		}

		String[] array = { "Sim", "Não" };

		comboBoxDoce = new JComboBox(doceTipo);
		comboBoxDoce.setBounds(66, 11, 196, 20);
		contentPane.add(comboBoxDoce);

		comboBoxPeso = new JComboBox(docePeso);
		comboBoxPeso.setBounds(66, 42, 196, 20);
		contentPane.add(comboBoxPeso);

		comboBoxPago = new JComboBox(array);
		comboBoxPago.setBounds(66, 73, 196, 20);
		contentPane.add(comboBoxPago);

		JLabel lblPeso = new JLabel("Peso");
		lblPeso.setBounds(10, 45, 46, 14);
		contentPane.add(lblPeso);

		JLabel lblPago = new JLabel("Pago");
		lblPago.setBounds(10, 76, 46, 14);
		contentPane.add(lblPago);

		JButton btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(10, 142, 89, 23);
		contentPane.add(btnCancel);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					
					//PEGANDO OS VALORES DOS CAMPOS E COMBOXS PARA A TABELA EM COMPRAS ---------- 
					
					DefaultTableModel m = (DefaultTableModel) Compras.getTable().getModel();
					String tipo_1 = comboBoxDoce.getSelectedItem().toString();
					double peso_2 = Double.parseDouble(comboBoxPeso.getSelectedItem().toString());
					int quantidade_3 = Integer.parseInt(quantidade.getText().trim());

					if (quantidade_3>=0) {
						JOptionPane.showMessageDialog(null, "Informe um numero maior que 0 na quantidade");
					} else {

						String pagou_5 = comboBoxPago.getSelectedItem().toString();
						
						// É NECESSARIO VERIFICAR SE EXISTE A QUANTIDADE NO ESTOQUE ----------------------
						String sql = "select tipo,data_fabricação,peso,validade,preço,quantidade,id_doce from doce where tipo = '"
								+ tipo_1 + "' and peso = " + peso_2;
						ArrayList<Doce> doce = repositorioDoce.recoverAll(sql);

						if (doce.size() == 0) {
							JOptionPane.showMessageDialog(null, "Não existe esse doce armazenado");
						} else if (doce.get(0).getQuantidade() < quantidade_3) {
							JOptionPane.showMessageDialog(null, "Não tem doces suficientes tente uma quantidade menor");
						} else {
							// SE TIVER ESSE DOCE COLOCAR A LINHA NA TABELA -----------------
							String[] row = { doce.get(0).getId_Doce() + "", tipo_1, peso_2 + "", quantidade_3 + "",
									doce.get(0).getPreço() + "", pagou_5 };
							m.addRow(row);
							// É SET A QUANTIDADE NO BANCO DE CIDA  -----------------
							doce.get(0).setQuantidade(doce.get(0).getQuantidade() - quantidade_3);
							repositorioDoce.update(doce.get(0));
						}
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Informe um numero natural na quantidade");
				}
			}

		});
		btnOk.setBounds(173, 142, 89, 23);
		contentPane.add(btnOk);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(10, 108, 75, 14);
		contentPane.add(lblQuantidade);

		quantidade = new JTextField();

		quantidade.setBounds(95, 104, 167, 20);
		contentPane.add(quantidade);
		quantidade.setColumns(10);
	}

}
