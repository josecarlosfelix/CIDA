package CidaDoDoce.upe.telas.br;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import CidaDoDoce.upe.negocio.br.Cliente;
import CidaDoDoce.upe.negocio.br.Fisico;
import CidaDoDoce.upe.negocio.br.Juridica;
import CidaDoDoce.upe.negocio.br.Venda;
import CidaDoDoce.upe.repositorio.br.Conexao;
import CidaDoDoce.upe.repositorio.br.Formatando;
import CidaDoDoce.upe.repositorio.br.RepositorioCliente;
import CidaDoDoce.upe.repositorio.br.RepositorioFisico;
import CidaDoDoce.upe.repositorio.br.RepositorioJuridica;
import CidaDoDoce.upe.repositorio.br.RepositorioVenda;


public class Cadastro2 extends JFrame {

	private JPanel contentPane;
	private JTextField nome;
	private JTextField rua;
	private JTextField numero;
	private JTextField complemeto;
	private JTextField bairro;
	private JFormattedTextField cep;
	private JFormattedTextField celular;
	private JFormattedTextField fixo;
	private static JFormattedTextField cpf;
	private static JFormattedTextField cnpj;
	private static Cliente cliente; 
	private RepositorioCliente repositorioCliente = new RepositorioCliente();
	private RepositorioFisico repositorioFisico = new RepositorioFisico();
	private RepositorioJuridica repositorioJuridica = new RepositorioJuridica();
	private RepositorioVenda repositorioVenda = new RepositorioVenda();
	
	/**
	 * Launch the application.
	 */
	public static void mostraCadastro2() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro2 frame = new Cadastro2();
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
	public Cadastro2() {
		setTitle("Cadastro de clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			//VOLTA PARA ANTERIO
			public void actionPerformed(ActionEvent e) {
				dispose();
				Menu2.mostraMenu2();
			}
		});
		btnVoltar.setBounds(10, 232, 89, 23);
		contentPane.add(btnVoltar);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(10, 23, 46, 14);
		contentPane.add(lblNome);
		
		nome = new JTextField();
		nome.setDocument(new Formatando(45));
		nome.setBounds(101, 21, 569, 20);
		contentPane.add(nome);
		nome.setColumns(10);
		
		JLabel lblRua = new JLabel("Rua:");
		lblRua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRua.setBounds(10, 48, 37, 14);
		contentPane.add(lblRua);
		
		rua = new JTextField();
		rua.setBounds(101, 46, 269, 20);
		contentPane.add(rua);
		rua.setColumns(10);
		
		JLabel lblNumero = new JLabel("N\u00FAmero:");
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumero.setBounds(383, 48, 58, 14);
		contentPane.add(lblNumero);
		
		numero = new JTextField();
		numero.setBounds(451, 46, 219, 20);
		contentPane.add(numero);
		numero.setColumns(10);
		
		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblComplemento.setBounds(10, 79, 92, 14);
		contentPane.add(lblComplemento);
		
		complemeto = new JTextField();
		complemeto.setBounds(101, 77, 569, 20);
		contentPane.add(complemeto);
		complemeto.setColumns(10);
		
		JLabel lblBarrio = new JLabel("Bairro:");
		lblBarrio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBarrio.setBounds(10, 106, 46, 14);
		contentPane.add(lblBarrio);
		
		bairro = new JTextField();
		bairro.setBounds(101, 104, 569, 20);
		contentPane.add(bairro);
		bairro.setColumns(10);
		
		JLabel lblCep = new JLabel("CEP:");
		lblCep.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCep.setBounds(10, 137, 29, 14);
		contentPane.add(lblCep);
		
		cep = new JFormattedTextField(Mascara("#####-###"));
		cep.setBounds(101, 135, 132, 20);
		contentPane.add(cep);
		cep.setColumns(10);
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCelular.setBounds(240, 137, 46, 14);
		contentPane.add(lblCelular);
		
		celular = new JFormattedTextField(Mascara("(##)#####-####"));
		celular.setColumns(10);
		celular.setBounds(286, 135, 155, 20);
		contentPane.add(celular);
		
		JLabel lblFixo = new JLabel("Fixo: ");
		lblFixo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFixo.setBounds(448, 137, 29, 14);
		contentPane.add(lblFixo);
		
		fixo = new JFormattedTextField(Mascara("(##)####-####"));
		fixo.setColumns(10);
		fixo.setBounds(487, 135, 183, 20);
		contentPane.add(fixo);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCpf.setBounds(10, 160, 29, 14);
		contentPane.add(lblCpf);
		
		cpf = new JFormattedTextField(Mascara("###.###.###-##"));
		cpf.setBounds(101, 158, 132, 20);
		contentPane.add(cpf);
		cpf.setColumns(10);
		
		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setBounds(240, 162, 37, 14);
		contentPane.add(lblCnpj);
		
		cnpj = new JFormattedTextField(Mascara("##.###.###/####-##"));
		cnpj.setColumns(10);
		cnpj.setBounds(286, 159, 384, 20);
		contentPane.add(cnpj);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			//CRIANDO O CLIENTE
			public void actionPerformed(ActionEvent e) {
					
				
				ArrayList<String> fones = new ArrayList<>();
				fones.add(celular.getText());
				fones.add(fixo.getText());
				
				// CASO CPF ESTEJA PREENCHIDO EO CPNJ NÃO FAÇA ------------------
		
			if(!cpf.getText().equals("") && cnpj.getText().equals("")){
					 // ADICIONA ATRIBUTOS AO CLIENTE E INVOCA UM METODO PARA GUARDA NA MEMORIA --------------- 
				
					Fisico fisico = new Fisico(nome.getText(),rua.getText(),complemeto.getText()
						,bairro.getText(),Integer.parseInt(numero.getText()),cep.getText(),fones,0,cpf.getText(),0);
					
					cliente = fisico;
					
					repositorioCliente.insert(cliente);
					
					repositorioFisico.insert(fisico);
					
					
					JOptionPane.showMessageDialog(null, "Registro adicionado");
				 // CASO SEJA CNPJ FAÇA A MESMA SO COM O CNPJ 	
				
				}else if(!cnpj.getText().equals("") && cpf.getText().equals("")){

					Juridica juridica = new Juridica(nome.getText(),rua.getText(),complemeto.getText()
							,bairro.getText(),Integer.parseInt(numero.getText()),cep.getText(),fones,0,cnpj.getText(),0);
					
					cliente = juridica;
					
					repositorioCliente.insert(cliente);
					
					repositorioJuridica.insert(juridica);
			
					JOptionPane.showMessageDialog(null, "Registro adicionado");
				// AMBOS SEM PREENCHIMENTO IMPRIMA MESSAGEM 
				}else if(cpf.getText().equals("") && cnpj.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Insira um dos campos(CPF ou CNPJ)");
				
				// OS DOIS PREENCHIDOS ------------------------------------------------------
				}else{
					
					JOptionPane.showMessageDialog(null, "Insira apenas um dos campos(CPF ou CNPJ)");
				}
			}
		});
		btnCadastrar.setBounds(581, 232, 89, 23);
		contentPane.add(btnCadastrar);
	}
	
	private void limpaCampos(){
		nome.setText("");
		rua.setText("");
		numero.setText("");
		complemeto.setText("");
		bairro.setText("");
		cep.setText("");
		celular.setText("");
		fixo.setText("");
		cpf.setText("");
		cnpj.setText("");
		
		
	}
	
	
	public static Cliente getCliente() {
		return cliente;
	}

	public static void setCliente(Cliente cliente) {
		Cadastro2.cliente = cliente;
	}

	public static JFormattedTextField getCpf() {
		return cpf;
	}

	public static void setCpf(JFormattedTextField cpf) {
		Cadastro2.cpf = cpf;
	}

	public static JFormattedTextField getCnpj() {
		return cnpj;
	}

	public static void setCnpj(JFormattedTextField cnpj) {
		Cadastro2.cnpj = cnpj;
	}
	
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

	/*public static double getValor() {
		return Double.parseDouble(precoTotal.getText());
	}

	public static void setValor(double valor1) {
		precoTotal.setText(""+valor1);
	}*/
}
