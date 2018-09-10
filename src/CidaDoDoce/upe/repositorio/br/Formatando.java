package CidaDoDoce.upe.repositorio.br;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

//TRATANDO EXEÇOES DAS CAIXINHAS DE TEXTOS E LIMITANDO A QUANTIDADE LETRA EM CADA CAMPO 


public class Formatando extends PlainDocument {
	private int limit;
	public Formatando(int limit){
		super();
		this.limit = limit;
	}
	Formatando(int limit, boolean upper){
		super();
		this.limit = limit;
	}
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if (str == null)
			return;
		if ((getLength()+ str.length()) <= limit){
			super.insertString(offset, str, attr);
		}
	}
	

}
