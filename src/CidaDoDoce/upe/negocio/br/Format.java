package CidaDoDoce.upe.negocio.br;

public class Format {

	public static String formato1(String s) {//POR RAZÕES DO PADRÃO AMERICANO O FORMATO DE ENTRADA DEVE SER (ANO-MES-DIA)XXXX-XX-XX
		

		String array[] = s.split("-");
		String dia = array[2];
		String mes = array[1];
		String ano = array[0];

		return dia+"/"+mes+"/"+ano; // FORMATO DE SAIDA (DIA-MES-ANO)(XX-XX-XXXX)
	}

	
	public static String formato2(String s) {// (XX/XX/XXXX)
		
		String array[] = s.split("/");
		String dia = array[2];
		String mes = array[1];
		String ano = array[0];

		return ano+"-"+mes+"-"+dia;
		// XXXX-XX-XX
		
	}
}
