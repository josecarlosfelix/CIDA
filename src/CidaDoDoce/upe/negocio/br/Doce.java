package CidaDoDoce.upe.negocio.br;

public class Doce {
	
	private int Id_Doce;
	private String Tipo;
	private String DataFabricacao;
	private double Peso;
	private String DataDeValidade;
	private double preço;

	private double Quantidade;
	
	
	public Doce(int id_Doce, String tipo, String dataFabricacao, double peso, String dataDeValidade, double preço,
	double quantidade) {
		super();
		Id_Doce = id_Doce;
		Tipo = tipo;
		DataFabricacao = dataFabricacao;
		Peso = peso;
		DataDeValidade = dataDeValidade;
		this.preço = preço;
		
		Quantidade = quantidade;
	}


	public int getId_Doce() {
		return Id_Doce;
	}


	public void setId_Doce(int id_Doce) {
		Id_Doce = id_Doce;
	}


	public String getTipo() {
		return Tipo;
	}


	public void setTipo(String tipo) {
		Tipo = tipo;
	}


	public String getDataFabricacao() {
		return DataFabricacao;
	}


	public void setDataFabricacao(String dataFabricacao) {
		DataFabricacao = dataFabricacao;
	}


	public double getPeso() {
		return Peso;
	}


	public void setPeso(double peso) {
		Peso = peso;
	}


	public String getDataDeValidade() {
		return DataDeValidade;
	}


	public void setDataDeValidade(String dataDeValidade) {
		DataDeValidade = dataDeValidade;
	}


	public double getPreço() {
		return preço;
	}


	public void setPreço(double preço) {
		this.preço = preço;
	}


	


	public double getQuantidade() {
		return Quantidade;
	}


	public void setQuantidade(double quantidade) {
		Quantidade = quantidade;
	}
//-------------------------------------------------------------------------------------------------------
//SUB ESCRITA DE METODO(STRING) 

	@Override
	public String toString() {
		return "Doce [Id_Doce=" + Id_Doce + ", Tipo=" + Tipo + ", DataFabricacao=" + DataFabricacao + ", Peso=" + Peso
				+ ", DataDeValidade=" + DataDeValidade + ", preço=" + preço + ", Quantidade=" + Quantidade + "]";
	}


	
	
	
	

}
