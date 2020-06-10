package principal;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Call {
	
	public static void main(final String[] args) throws ClassNotFoundException, SQLException {

		/* Principal */

		/* Leitura dos arquivos */
		Professor.desserialization();
		Diretor.desserialization();
		Aluno.desserialization();
		Sala.desserialization();
		Disciplina.desserialization();
		/* Leitura dos arquivos */

		System.out.println("Desserializa��o completa!");
		InterfaceGrafica.separator();

		/* Instancia para chamada das operacoes */
		Operation operacao = new Operation();
		
		/* Leitor do menu de login */
		Scanner leitorLogin = new Scanner(System.in);
		/* Leitor do menu */
		Scanner leitorSelection = new Scanner(System.in);

		// Arrumar o M�todo do getLista de diretor pra dar Override na de Professor
		// Se for possivel claro
		//Talvez n�o seja poss�vel no atual design do professor
		/* Checagem da existencia de um Diretor no sistema */
		List<Diretor> checagemDiretor = Diretor.getListaDir();
		/* Checagem da existencia de um Diretor no sistema */
		List<Disciplina> checagemDisciplina = Disciplina.getLista();

		if(checagemDisciplina.isEmpty()) {
			operacao.pegarDisciplinas();
			operacao.serializeAll();
		}

		/* Usuarios */
		Diretor userDiretor = null;
		Professor userProfessor = null;
		Aluno userAluno = null;

		/* Login */
		String cpf, senha;
		boolean isLogged = false;
		/* Sele��o nos menus */
		String userSelect;
		String userLogin;
		/* Condi��o de parada While */
		boolean repStop = false;
		boolean userStop = false;

		InterfaceGrafica.welcome();

		/* Menu Principal das Operacoes */
		while (!repStop) {
			
			// Checagem se existe um Diretor no sistema
			if (!checagemDiretor.isEmpty()) {
				InterfaceGrafica.loginMode();
				
				userLogin = leitorSelection.nextLine();

				switch (userLogin) {
					case "1":

						// Login Aluno

						isLogged = false;
						userStop = false;

						while (!isLogged) {
							if (Aluno.getLista().isEmpty()) {
								InterfaceGrafica.semAluno();
								break;
							}
							final Aluno call = Aluno.getLista().get(0);
							boolean userCheck = false;

							InterfaceGrafica.lineBreaker();
							System.out.println("Digite o Cpf:");
							cpf = leitorLogin.nextLine();

							InterfaceGrafica.lineBreaker();
							System.out.println("Digite a Senha:");
							senha = leitorLogin.nextLine();

							userCheck = call.autenticar(cpf, senha);

							if (userCheck == true) {
								isLogged = true;

								InterfaceGrafica.lineBreaker();
								InterfaceGrafica.separator();
								System.out.println("Acesso Garantido!");
								for (final Aluno x : Aluno.getLista()) {
									if (cpf.equals(x.getCpf())) {
										userAluno = x;
										break;
									}
								}
								System.out.println("Bem vindo, " + userAluno.getNome() + "!");
								InterfaceGrafica.separator();
								InterfaceGrafica.lineBreaker();
							}else {
								
								if(!operacao.usuarioVoltar()) {
									isLogged = true;
									userStop = true;
									break;
								}
								
							}

							if (isLogged) {

								while (!userStop) {
									InterfaceGrafica.operationsAluno();

									userSelect = leitorSelection.nextLine();

									switch (userSelect) {
										case "1":

											InterfaceGrafica.getNP1();
											operacao.getNP1(userAluno);
											operacao.serializeAll();
											
											break;
										case "2":

											InterfaceGrafica.getNP1();
											operacao.getNP1(userAluno);
											operacao.serializeAll();
											
											break;
										case "3":

											InterfaceGrafica.separator();
											System.out.println("Opera��o n�o Implementada.");
											InterfaceGrafica.separator();

											break;
										case "33":
											
											//Ajeitar pra ser s� os alunos desse professor
											operacao.verAlunos();
											
											break;
										case "0":

											repStop = true;
											userStop = true;

											break;
										case "/":

											userStop = true;

											break;
										default:
											
											InterfaceGrafica.separator();
											System.out.println("Opera��o selecionada n�o existe.");
											InterfaceGrafica.separator();

									}
									// Switch end
								}
								// While end
							}
							// IF isLogged end
						}
						// Main While end

						break;
					case "2":

						// Login Professor

						isLogged = false;
						userStop = false;

						while (!isLogged) {
							if (Professor.getLista().isEmpty()) {
								InterfaceGrafica.semProfessor();
								break;
							}
							final Professor call = Professor.getLista().get(0);
							boolean userCheck = false;

							InterfaceGrafica.lineBreaker();
							System.out.println("Digite o Cpf:");
							cpf = leitorLogin.nextLine();

							InterfaceGrafica.lineBreaker();
							System.out.println("Digite a Senha:");
							senha = leitorLogin.nextLine();

							userCheck = call.autenticar(cpf, senha);

							//Checagem de Autentica��o
							//Else para poder tirar o usu�rio do loop caso o mesmo deseje
							if (userCheck == true) {
								isLogged = true;

								InterfaceGrafica.lineBreaker();
								InterfaceGrafica.separator();
								System.out.println("Acesso Garantido!");
								for (final Professor x : Professor.getLista()) {
									if (cpf.equals(x.getCpf())) {
										userProfessor = x;
										break;
									}
								}
								System.out.println("Bem vindo(a), " + userProfessor.getNome() + "!");
								InterfaceGrafica.separator();
								InterfaceGrafica.lineBreaker();
							}else {
								
								if(!operacao.usuarioVoltar()) {
									isLogged = true;
									userStop = true;
									break;
								}
							}

							if (isLogged) {
								
								while (!userStop) {
									InterfaceGrafica.operationsProfessor();

									userSelect = leitorSelection.nextLine();

									switch (userSelect) {
										case "1":

											InterfaceGrafica.setNP1();
											operacao.setNP1(userProfessor);
											operacao.serializeAll();

											break;
										case "2":

											InterfaceGrafica.setNP2();
											operacao.setNP2(userProfessor);
											operacao.serializeAll();

											break;
										case "11":

											InterfaceGrafica.separator();
											System.out.println("Opera��o n�o Implementada.");
											InterfaceGrafica.separator();

											break;
										case "22":

											InterfaceGrafica.separator();
											System.out.println("Opera��o n�o Implementada.");
											InterfaceGrafica.separator();

											break;
										case "33":

											InterfaceGrafica.separator();
											System.out.println("Opera��o n�o Implementada.");
											InterfaceGrafica.separator();

											break;
										case "0":

											repStop = true;
											userStop = true;

											break;
										case "/":

											userStop = true;

											break;
										default:

											InterfaceGrafica.separator();
											System.out.println("Opera��o selecionada n�o existe.");
											InterfaceGrafica.separator();

									}
									// Switch end
								}
								// While end
							}
							// IF isLogged end
						}
						// Main While end

						break;
					case "3":

						// Login Diretor

						isLogged = false;
						userStop = false;

						while (!isLogged) {
							final Diretor call = Diretor.getListaDir().get(0);
							boolean userCheck = false;

							InterfaceGrafica.lineBreaker();
							System.out.println("Digite o Cpf:");
							cpf = leitorLogin.nextLine();

							InterfaceGrafica.lineBreaker();
							System.out.println("Digite a Senha:");
							senha = leitorLogin.nextLine();

							userCheck = call.autenticar(cpf, senha);

							//Checagem de Autentica��o
							//Else para poder tirar o usu�rio do loop caso o mesmo deseje
							if (userCheck == true) {
								isLogged = true;

								InterfaceGrafica.lineBreaker();
								InterfaceGrafica.separator();
								System.out.println("Acesso Garantido!");
								for (final Diretor x : Diretor.getListaDir()) {
									if(cpf.equals(x.getCpf())) {
										userDiretor = x;
										break;
									}
								}
								
								System.out.println("Bem vindo, " + userDiretor.getNome() +"!");
								InterfaceGrafica.separator();
								InterfaceGrafica.lineBreaker();
							}else {
								
								if(!operacao.usuarioVoltar()) {
									isLogged = true;
									userStop = true;
									break;
								}
								
						}

						if(isLogged) {
							while(!userStop) {
								InterfaceGrafica.operationsDiretor();
								
								userSelect = leitorSelection.nextLine();
								
								switch(userSelect) {
								case "1":
									
									//Colocar um IF aqui para verificar se a lista de salas n�o est� vazia
									operacao.cadastrarAluno();
									operacao.serializeAll();
									
									break;
								case "2":
									
									//Colocar um IF aqui para verificar se a lista de disciplinas n�o est� vazia
									operacao.cadastrarProfessor();
									operacao.serializeAll();
									
									break;
								case "3":
									
									operacao.cadastrarSala();
									operacao.serializeAll();
									
									break;
								case "4":
									
									operacao.cadastrarDiretor();
									operacao.serializeAll();
									
									break;
								case "11":
									
									operacao.verAlunos();
									
									break;
								case "22":
									
									operacao.verProfessores();
									
									break;
								case "33":
									
									operacao.verSalas();
									
									break;
								case "44":
									
									operacao.verDiretores();
									
									break;
								case "55":
									
									operacao.verDisciplinas();
									
									break;
								case "111":

									InterfaceGrafica.deletarAluno();
									operacao.deletarAluno();
									operacao.serializeAll();
									
									break;
								case "222":
									
									InterfaceGrafica.deletarProfessor();
									operacao.deletarProfessor();
									operacao.serializeAll();
									
									break;
								case "444":

									InterfaceGrafica.deletarDiretor();
									operacao.deletarDiretor(userDiretor.getCpf());
									operacao.serializeAll();
									
									break;
								case "1111":

									InterfaceGrafica.updateAluno();
									operacao.updateAluno();
									operacao.serializeAll();
									
									break;
								case "2222":

									InterfaceGrafica.updateProfessor();
									operacao.updateProfessor();
									operacao.serializeAll();
									
									break;
								case "4444":

									InterfaceGrafica.updateDiretor();
									operacao.updateDiretor();
									operacao.serializeAll();
									
									break;
								case "0":
									
									repStop = true;
									userStop = true;
									
									break;
								case "/":
									
									userStop = true;
									
									break;
								default:
									
									InterfaceGrafica.separator();
									System.out.println("Opera��o selecionada n�o existe.");
									InterfaceGrafica.separator();
									
								}
								//Switch end
							}
							//While end
						}
						//IF isLogged end
					}
					//Main While end
					
					break;
				case "0":
					repStop = true;
					userStop = true;
					break;
				default:
					
					InterfaceGrafica.separator();
					System.out.println("Opera��o selecionada n�o existe.");
					InterfaceGrafica.separator();
					
				}
				//Main Switch end

			}else {
			
				InterfaceGrafica.separator();
				InterfaceGrafica.semDiretor();
				InterfaceGrafica.separator();
					
				operacao.cadastrarDiretor();
					
				/*Serializar*/
				operacao.serializeAll();

			}
		}
		
		leitorSelection.close();
		leitorLogin.close();
		
		operacao.closeScanners();
			
		/* Salvamento dos arquivos */
		operacao.serializeAll();
		/* Salvamento dos arquivos */
		
		InterfaceGrafica.separator();
		System.out.println("Serializa��o completa!");
		InterfaceGrafica.separator();
		InterfaceGrafica.end();
		
	}
	
}
