package CidaDoDoce.upe.interfeces.br;

import java.util.ArrayList;

//FUNCIONA COMO HARANÇA PARA QUANDO ELA FOR CHMADA ATRIBUIR TODOS ESSES METODOS 

public interface InterfeceGeral <T> {
	
	public void insert(T t);
	public ArrayList<T> recoverAll(String S);
	public void update(T t);
	public void delete(T t);
	public ArrayList<T> all();
	
	

}
