package CidaDoDoce.upe.telas.br;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login2 extends JFrame {

	private JPanel contentPane;
	private JTextField usuario;
	private JTextField senha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login2 frame = new Login2();
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
	public Login2() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usuario = new JTextField();
		usuario.setBounds(198, 220, 283, 20);
		contentPane.add(usuario);
		usuario.setColumns(10);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLogin.setBounds(198, 195, 46, 14);
		contentPane.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSenha.setBounds(198, 265, 46, 14);
		contentPane.add(lblSenha);
		
		senha = new JTextField();
		senha.setBounds(198, 290, 283, 20);
		contentPane.add(senha);
		senha.setColumns(10);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Menu2.mostraMenu2();
			}
		});
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEntrar.setBounds(295, 351, 89, 23);
		contentPane.add(btnEntrar);
		
		JLabel lblCidaDoDoce = new JLabel("Cida do Doce \u00AE");
		lblCidaDoDoce.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblCidaDoDoce.setBounds(591, 474, 89, 14);
		contentPane.add(lblCidaDoDoce);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Login2.class.getResource("/CidaDoDoce/upe/imagens/br/Logo_Cida_1.1.png")));
		lblNewLabel.setBounds(241, 22, 197, 143);
		contentPane.add(lblNewLabel);
	}

}
