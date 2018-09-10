package CidaDoDoce.upe.negocio.br;

import java.util.ArrayList;

//CLASS FISICO FILHA DA CLASS CLIENTE 

public class Fisico  extends Cliente{
	
	private int Id_Fisico;
	private String CPF;
	public Fisico(String nome, String rua, String complemento, String barrio, int numero, String cEP,
			ArrayList<String> fone, int id_Fisico, String cPF,int codigo_cliente) {
		super(nome, rua, complemento, barrio, numero, cEP, fone, codigo_cliente);
		Id_Fisico = id_Fisico;
		CPF = cPF;
	}
	public int getId_Fisico() {
		return Id_Fisico;
	}
	public void setId_Fisico(int id_Fisico) {
		Id_Fisico = id_Fisico;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	
	
	
}
	
	


	
