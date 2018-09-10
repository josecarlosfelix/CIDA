package CidaDoDoce.upe.telas.br;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import CidaDoDoce.upe.negocio.br.Doce;
import CidaDoDoce.upe.repositorio.br.RepositorioDoce;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class Mercadoria extends JFrame {

	private JPanel contentPane;
	private int id_doce = -1;
	private JTextField tipo;
	private JFormattedTextField dataF;
	private JFormattedTextField dataV;
	private JFormattedTextField peso;
	private JFormattedTextField preco;
	private JTextField quantidade;
	private RepositorioDoce repositorioDoce = new RepositorioDoce();
	private Doce doce;
	/**
	 * Launch the application.
	 */
	public static void mostraMercadoria() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mercadoria frame = new Mercadoria();
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
	public Mercadoria() {
		setTitle("Cadastro de Mercadoria ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 695, 254);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTipo.setBounds(10, 36, 46, 14);
		contentPane.add(lblTipo);

		tipo = new JTextField();
		tipo.setBounds(51, 34, 607, 20);
		contentPane.add(tipo);
		tipo.setColumns(10);

		JLabel lblData = new JLabel("Data de Fabrica\u00E7\u00E3o:");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblData.setBounds(10, 86, 115, 14);
		contentPane.add(lblData);

		JLabel lblDataDeValidade = new JLabel("Data de Validade:");
		lblDataDeValidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDataDeValidade.setBounds(10, 61, 102, 14);
		contentPane.add(lblDataDeValidade);

		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPeso.setBounds(322, 86, 46, 14);
		contentPane.add(lblPeso);

		JLabel lblPreo = new JLabel("Pre\u00E7o:");
		lblPreo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPreo.setBounds(10, 111, 46, 14);
		contentPane.add(lblPreo);

		JLabel lblQuatidade = new JLabel("Quatidade:");
		lblQuatidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuatidade.setBounds(322, 111, 62, 14);
		contentPane.add(lblQuatidade);

		dataF = new JFormattedTextField(Mascara("##/##/####"));
		dataF.setBounds(125, 84, 187, 20);
		contentPane.add(dataF);
		dataF.setColumns(10);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Menu2.mostraMenu2();

			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVoltar.setBounds(10, 174, 89, 23);
		contentPane.add(btnVoltar);

		JButton btnAdicionar = new JButton("Cadastrar\r\n");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id_doce == -1) {
					doce = new Doce(-1, tipo.getText(), dataF.getText(), Double.parseDouble(peso.getText()),
							dataV.getText(), Double.parseDouble(preco.getText()),
							Double.parseDouble(quantidade.getText()));

					new RepositorioDoce().insert(doce);
					limpaCampos();
				}else{
					doce = new Doce(id_doce, tipo.getText(), dataF.getText(), Double.parseDouble(peso.getText()),
							dataV.getText(), Double.parseDouble(preco.getText()),
							Double.parseDouble(quantidade.getText()));
					repositorioDoce.update(doce);
					JOptionPane.showMessageDialog(null, "Registro alterado");
					Estoque.mostraEstoque();
					dispose();
					
				}

			}
		});
		btnAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAdicionar.setBounds(569, 174, 89, 23);
		contentPane.add(btnAdicionar);

		dataV = new JFormattedTextField(Mascara("##/##/####"));
		dataV.setBounds(115, 59, 543, 20);
		contentPane.add(dataV);
		dataV.setColumns(10);

		peso = new JFormattedTextField();
		peso.setBounds(389, 84, 269, 20);
		contentPane.add(peso);
		peso.setColumns(10);

		preco = new JFormattedTextField();
		preco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				preco.setText(preco.getText().replaceAll("[^0-9 ^.]", ""));
				
			}
		});
		preco.setBounds(51, 111, 261, 20);
		contentPane.add(preco);
		preco.setColumns(10);

		quantidade = new JTextField();
		quantidade.setBounds(389, 109, 269, 20);
		contentPane.add(quantidade);
		quantidade.setColumns(10);
	}

	public void limpaCampos() {

		tipo.setText("");
		dataF.setText("");
		dataV.setText("");
		peso.setText("");
		preco.setText("");
		quantidade.setText("");
	}

	public void setText(Doce t){
		id_doce = t.getId_Doce();
		tipo.setText(t.getTipo());
		dataF.setText(t.getDataFabricacao());
		dataV.setText(t.getDataDeValidade());
		preco.setText(t.getPreço()+"");
		quantidade.setText(t.getQuantidade()+"");
		peso.setText(""+t.getPeso());
	}
	
// COLOLCANDO MASCARA NOS CAMPOS DE MERCADORIA ------------------------------
	public MaskFormatter Mascara(String Mascara){
		MaskFormatter F_Mascara = new MaskFormatter();
		
		try{
			F_Mascara.setMask(Mascara);
			F_Mascara.setPlaceholderCharacter(' ');
			}
		catch (Exception excecao){
			excecao.printStackTrace();
			}
		return F_Mascara;
	}
}
	
