package principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@SuppressWarnings("serial")
public class Aluno extends Pessoa implements Serializable, Autenticacao {

	private static List<Aluno> listaAlunos =  new ArrayList<>();
	
	private Map<String, Float[]> disc = new HashMap<String, Float[]>();
	
	private static int nmTotalMat;
	
	private int serie;
	private Sala sala;
	private String mat;
	private String turno;
	
	Aluno(String nome, String cpf, String rg, String dtNasc, int serie, String turno, String sala, String senha) throws SQLException {
		super(nome, cpf, rg, dtNasc, senha);
		this.serie = serie;
		this.turno = turno;
		nmTotalMat++;
		mat = sala + "" + "" + serie + "" + nmTotalMat;
		
		if(serie < 6) {
			disc.put("Portugues", null);
			disc.put("Matematica", null);
			disc.put("Educacao Fisica", null);
			disc.put("Arte", null);
		}else if(serie > 5) {
			disc.put("Portugues", null);
			disc.put("Matematica", null);
			disc.put("Educacao Fisica", null);
			disc.put("Ciencia", null);
			disc.put("Ingles", null);
			disc.put("Filosofia", null);
			disc.put("Geografia", null);
			disc.put("Historia", null);
			disc.put("Arte", null);
		}
		for(int i = 0; i < Sala.listaSalas.size(); i++) {
			if(sala == Sala.listaSalas.get(i).getSala()) {
				Sala.listaSalas.get(i).addAluno(this);
				this.sala = Sala.listaSalas.get(i);
			}
		}
	}
	
	/* Autentica��o */
	
	@Override
	public Aluno autenticar(String cpf,String senha) {

		Aluno usuario = null;
		
		return usuario;
		
	}
	
	/* M�todos B�sicos */
	
	public static List<Aluno> getLista(){
		return listaAlunos;
	}
	
	public static void colocarNaLista(Aluno aluno){
		listaAlunos.add(aluno);
	}
	
	public String getMat() {
		return mat;
	}
	
	String getTurno() {
		return turno;
	}
	
	String getSala() {
		return sala.getSala();
	}
	
	int getSerie() {
		return serie;
	}
	
	float getNP1(String a) {
		Float[] x = disc.get(a);
		
		return x[0];
	}
	
	float getNP2(String a) {
		Float[] x = disc.get(a);
		
		return x[1];
	}
	
	void setNP1(Float np1, Professor prof) {
		Float[] np = disc.get(prof.getDisc());
		disc.remove(prof.getDisc());
		np[0] = np1;
		disc.put(prof.getDisc(), np);
	}
	
	void setNP2(Float np2, Professor prof) {
		Float[] np = disc.get(prof.getDisc());
		disc.remove(prof.getDisc());
		np[1] = np2;
		disc.put(prof.getDisc(), np);
	}
	
	Float getMedia(Professor prof) {
		Float np[] = disc.get(prof.getDisc());
		Float media = (np[0] + np[1]) / 2;
		return media;
	}
	
	/* Serialization Handler */
	
	public static void serialization() {
		Aluno.saveObjectList(listaAlunos, "Alunos");
	}
	
	public static void desserialization() {
		listaAlunos = Aluno.readObjectList("Alunos");
	}
	
	public static void saveObjectList(List<Aluno> lista, String nomeArq) {
	      File arq = new File(nomeArq);
	      try {
	    	  arq.delete();
	    	  arq.createNewFile();
	    
	    	  ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
	        
	    	  objOutput.writeObject(lista);
	    	  objOutput.close();
	    
	      } catch(IOException erro) {
	    	  System.out.printf("Erro: %s", erro.getMessage());
	      }
	}
	
	@SuppressWarnings("all")
	public static List<Aluno> readObjectList(String nomeArq) {
		
		List<Aluno> lista = new ArrayList<Aluno>();
		try {
			File arq = new File(nomeArq);
			if (arq.exists()) {
				ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
				lista = (List<Aluno>) objInput.readObject();
				objInput.close();
			}
			
		} catch(IOException erro1) {
			System.out.printf("Erro: %s", erro1.getMessage());
		} catch(ClassNotFoundException erro2) {
			System.out.printf("Erro: %s", erro2.getMessage());
		}
	    
		return(lista);
	}
	

}
