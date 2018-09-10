package CidaDoDoce.upe.telas.br;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void mostraMenu2() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu2 frame = new Menu2();
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
	public Menu2() {
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnMercadoria = new JButton("Mercadoria ");
		btnMercadoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Mercadoria.mostraMercadoria();
			}
		});
		btnMercadoria.setIcon(new ImageIcon(Menu2.class.getResource("/CidaDoDoce/upe/imagens/br/wrong.png")));
		btnMercadoria.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnMercadoria.setBounds(470, 244, 144, 57);
		contentPane.add(btnMercadoria);
		
		JButton btnClientes = new JButton("Clientes");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Clientes.mostraCliente();
			}
		});
		btnClientes.setIcon(new ImageIcon(Menu2.class.getResource("/CidaDoDoce/upe/imagens/br/icone-homem.png")));
		btnClientes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnClientes.setBounds(59, 244, 144, 57);
		contentPane.add(btnClientes);
		
		JButton btnCadastro = new JButton("Cadastro");
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Cadastro2.mostraCadastro2();
			}
		});
		btnCadastro.setIcon(new ImageIcon(Menu2.class.getResource("/CidaDoDoce/upe/imagens/br/adduser_a\u00F1adir_3553.png")));
		btnCadastro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastro.setBounds(265, 244, 144, 57);
		contentPane.add(btnCadastro);
		
		JButton btnLogOff = new JButton("Log Off");
		btnLogOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login2 b = new Login2();
				b.setVisible(true);
				dispose();
				
				
			}
		});
		btnLogOff.setIcon(new ImageIcon(Menu2.class.getResource("/CidaDoDoce/upe/imagens/br/log_off-512.png")));
		btnLogOff.setBounds(24, 448, 144, 57);
		contentPane.add(btnLogOff);
		
		JButton btnEstoque = new JButton("Estoque ");
		btnEstoque.setIcon(new ImageIcon(Menu2.class.getResource("/CidaDoDoce/upe/imagens/br/wrong.png")));
		btnEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Estoque.mostraEstoque();
				dispose();
			}
		});
		btnEstoque.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEstoque.setBounds(59, 335, 144, 57);
		contentPane.add(btnEstoque);
		
		JButton btnMovimentao = new JButton("Movimenta\u00E7\u00E3o");
		btnMovimentao.setIcon(new ImageIcon(Menu2.class.getResource("/CidaDoDoce/upe/imagens/br/usuario-masculino-simbolo-interfaz-de-busqueda_318-39648.png")));
		btnMovimentao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Movimentacao.mostraMovimentacao();
				dispose();
			}
		});
		btnMovimentao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnMovimentao.setBounds(265, 335, 144, 57);
		contentPane.add(btnMovimentao);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Menu2.class.getResource("/CidaDoDoce/upe/imagens/br/Logo_Cida_1.1.png")));
		lblNewLabel.setBounds(242, 41, 198, 144);
		contentPane.add(lblNewLabel);
		
		JButton btnCompras = new JButton("Compras \r\n");
		btnCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Compras.mostraCompras();
				dispose();
			}
		});
		btnCompras.setIcon(new ImageIcon(Menu2.class.getResource("/CidaDoDoce/upe/imagens/br/carrinho-de-compras-de-projeto-checkered_318-50865.jpg")));
		btnCompras.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCompras.setBounds(470, 335, 144, 57);
		contentPane.add(btnCompras);
	}
}
