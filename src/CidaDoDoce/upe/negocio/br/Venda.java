package CidaDoDoce.upe.negocio.br;

public class Venda {
	
	private int Id_Venda;
	private String Data;
	private double Valor;
	private String pagou;
	private int quantidade;
	private int codigo_cliente;
	private int codigo_doce;
	
	public Venda(int id_Venda, String data, double valor, String pagou,int quantidade, int codigo_cliente,int codigo_doce) {
		Id_Venda = id_Venda;
		Data = data;
		Valor = valor;
		this.pagou = pagou;
		this.quantidade = quantidade;
		this.codigo_cliente = codigo_cliente;
		this.codigo_doce = codigo_doce;
	}


	public int getId_Venda() {
		return Id_Venda;
	}


	public void setId_Venda(int id_Venda) {
		Id_Venda = id_Venda;
	}


	public String getData() {
		return Data;
	}


	public void setData(String data) {
		Data = data;
	}

	public double getValor() {
		return Valor;
	}


	public void setValor(double valor) {
		Valor = valor;
	}

	public int getCodigo_doce() {
		return codigo_doce;
	}


	public void setCodigo_doce(int codigo_doce) {
		this.codigo_doce = codigo_doce;
	}


	public String getPagou() {
		return pagou;
	}


	public void setPagou(String pagou) {
		this.pagou = pagou;
	}


	public int getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	public int getCodigo_cliente() {
		return codigo_cliente;
	}


	public void setCodigo_cliente(int codigo_cliente) {
		this.codigo_cliente = codigo_cliente;
	}


	//-------------------------------------------------------------------------------------------------------
	//SUB ESCRITA DE METODO(STRING) 
	@Override
	public String toString() {
		return "Venda [Id_Venda=" + Id_Venda + ", Data=" + Data + ", Valor=" + Valor + ", pagou=" + pagou
				+ ", quantidade=" + quantidade + ", codigo_cliente=" + codigo_cliente + "]";
	}
	
	

}
