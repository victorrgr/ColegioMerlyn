package principal;

import java.util.*;

public class RecallTest {
	public static void main(String[] args) {
		
		List<Aluno> lista = new ArrayList<>(Aluno.readObjectList("Alunos"));
		
		for(Aluno x : lista) {
			System.out.println(x.getNome());
		}
		
		
		
		
		
//-----------------------------------------------------------------------------------------------------------------		
		
		/* Main de Teste para puxar os arquivos serializados */
		
		//List<Professor> listaAlunosRecall = new ArrayList<>(Professor.readObjectList("Alunos"));
//		System.out.println("Desserializado!\n");
//		
//		System.out.println("Nomes dos Alunos:\n");
		
		//listaAlunosRecall.get(0).cadastrarAluno("Perna Longa", "65478932145", "1568749", "10/11/2005");
		
//		for(Pessoa x : listaAlunosRecall) {
//			System.out.println(x.getNome());
//		}
		
		
	}
}
