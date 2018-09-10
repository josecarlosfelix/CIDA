package CidaDoDoce.upe.negocio.br;

import java.util.ArrayList;

public class Cliente {
	private  int id_cliente;
	private String Nome;
	private String Rua;
	private String Complemento;
	private String Barrio;
	private int Numero = 0;
	private String CEP;
	private String endereco;
	private ArrayList<String> fone;
	
	
	
	public Cliente(String nome, String rua, String complemento, String barrio, int numero, String cEP,ArrayList<String> fone,int id) {
		Nome = nome;
		Rua = rua;
		Complemento = complemento;
		Barrio = barrio;
		Numero = numero;
		CEP = cEP;
		this.fone = fone;
		id_cliente = id;
	}
	public Cliente(String nome, String endereco,ArrayList<String> fone,int id) {
		Nome = nome;
		this.endereco = endereco;
		this.fone = fone;
		id_cliente = id;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getRua() {
		return Rua;
	}
	public void setRua(String rua) {
		Rua = rua;
	}
	public String getComplemento() {
		return Complemento;
	}
	public void setComplemento(String complemento) {
		Complemento = complemento;
	}
	public String getBarrio() {
		return Barrio;
	}
	public void setBarrio(String barrio) {
		Barrio = barrio;
	}
	public int getNumero() {
		return Numero;
	}
	public void setNumero(int numero) {
		Numero = numero;
	}
	public String getCEP() {
		return CEP;
	}
	public void setCEP(String cEP) {
		CEP = cEP;
	}
	public ArrayList<String> getFone() {
		return fone;
	}
	public void setFone(ArrayList<String> fone) {
		this.fone = fone;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
		
		//COLOCAR TUDO EM UMA VARIAVEL SO (ENDEREÇO) 
	} 
	public String Endereco(){
		return "Rua " + Rua + "  Nº" + Numero +" Bairro " + Barrio + " Complemento "+ Complemento + " cep " + CEP;
	}
	
	public String getEndereco(){
		return endereco;
	}
	
	public void setEndereco(String e){
		this.endereco = e;
	}
}
