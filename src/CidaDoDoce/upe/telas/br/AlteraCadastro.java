package CidaDoDoce.upe.telas.br;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import CidaDoDoce.upe.negocio.br.Cliente;
import CidaDoDoce.upe.negocio.br.Fisico;
import CidaDoDoce.upe.negocio.br.Juridica;
import CidaDoDoce.upe.repositorio.br.RepositorioCliente;
import CidaDoDoce.upe.repositorio.br.RepositorioDoce;
import CidaDoDoce.upe.repositorio.br.RepositorioFisico;
import CidaDoDoce.upe.repositorio.br.RepositorioJuridica;

public class AlteraCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField nome;
	private static JTextField endereco;
	private JTextField celular;
	private JTextField fixo;
	private static JTextField cnpj;
	private int id;
	private JTextField cpf;
	
	RepositorioCliente repositorioCliente = new RepositorioCliente();
	RepositorioFisico repositorioFisico = new RepositorioFisico();
	RepositorioJuridica repositorioJuridica = new RepositorioJuridica();
	
	public AlteraCadastro() {
		setTitle("Altera Cadastro ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 696, 257);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnVoltar = new JButton("Cancelar");
		btnVoltar.addActionListener(new ActionListener() {
			// VOLTA PARA ANTERIO ------------------
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		btnVoltar.setBounds(10, 171, 89, 23);
		contentPane.add(btnVoltar);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(10, 23, 46, 14);
		contentPane.add(lblNome);

		nome = new JTextField();
		nome.setBounds(101, 21, 569, 20);
		contentPane.add(nome);
		nome.setColumns(10);

		JLabel lblRua = new JLabel("Endere\u00E7o:");
		lblRua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRua.setBounds(10, 48, 81, 14);
		contentPane.add(lblRua);

		endereco = new JTextField();
		endereco.setBounds(101, 46, 569, 20);
		contentPane.add(endereco);
		endereco.setColumns(10);

		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCelular.setBounds(10, 127, 46, 14);
		contentPane.add(lblCelular);

		celular = new JTextField();
		celular.setColumns(10);
		celular.setBounds(101, 125, 236, 20);
		contentPane.add(celular);

		JLabel lblFixo = new JLabel("Fixo: ");
		lblFixo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFixo.setBounds(359, 127, 29, 14);
		contentPane.add(lblFixo);

		fixo = new JTextField();
		fixo.setColumns(10);
		fixo.setBounds(413, 125, 257, 20);
		contentPane.add(fixo);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCpf.setBounds(10, 73, 29, 14);
		contentPane.add(lblCpf);

		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCnpj.setBounds(10, 102, 37, 14);
		contentPane.add(lblCnpj);

		cnpj = new JTextField();
		cnpj.setColumns(10);
		cnpj.setBounds(101, 99, 569, 20);
		contentPane.add(cnpj);

		JButton btnCadastrar = new JButton("Alterar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String>f = new ArrayList<String>();
				f.add(celular.getText());
				f.add(fixo.getText());
				Cliente t = new Cliente(nome.getText(), null,null, null, 0, null, f, id) {
					
				};
				repositorioCliente.update(t);
				Fisico fisico = new Fisico(null, null, null, null, 0, null, null, 0, cpf.getText(), id); 
				Juridica juridica  = new Juridica(null, null, null, null, 0, null, null, 0, cnpj.getText(), id);
				if(!cnpj.getText().equals("") && cpf.getText().equals("")){
					repositorioJuridica.update(juridica);
				}else if(!cpf.getText().equals("") && cnpj.getText().equals("") ){
					repositorioFisico.update(fisico);
				}else{
					JOptionPane.showMessageDialog(null, "Erro");
				}
				JOptionPane.showMessageDialog(null, "Registro Alterado");
				dispose();
			}
		});
		btnCadastrar.setBounds(581, 171, 89, 23);
		contentPane.add(btnCadastrar);

		cpf = new JTextField();
		cpf.setColumns(10);
		cpf.setBounds(101, 71, 569, 20);
		contentPane.add(cpf);
	}

	public void setText(String[] array) {
		id = Integer.parseInt(array[0]);
		nome.setText(array[1]);
		endereco.setText(array[3]);
		celular.setText(array[4]);
		fixo.setText(array[5]);
		
		if (array[2].length() <= 14) {
			cpf.setText(array[2]);
			
		} else {
			cnpj.setText(array[2]);
			
		}

	}

	public static JTextField getEndereco() {
		return endereco;
	}

	public static void setEndereco(JTextField endereco) {
		AlteraCadastro.endereco = endereco;
	}
	
	
}
