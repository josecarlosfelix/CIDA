package CidaDoDoce.upe.negocio.br;

import java.util.ArrayList;

public class Juridica extends Cliente{
	
	private int Id_Juridica;
	private String CNPJ;
	public Juridica(String nome, String rua, String complemento, String barrio, int numero, String cEP,
			ArrayList<String> fone, int id_Juridica, String cNPJ,int codigo_cliente) {
		super(nome, rua, complemento, barrio, numero, cEP, fone,codigo_cliente);
		Id_Juridica = id_Juridica;
		CNPJ = cNPJ;
	}
	public int getId_Juridica() {
		return Id_Juridica;
	}
	public void setId_Juridica(int id_Juridica) {
		Id_Juridica = id_Juridica;
	}
	public String getCNPJ() {
		return CNPJ;
	}
	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	
	
	
	

}
