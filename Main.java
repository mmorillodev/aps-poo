import java.util.Scanner;
import java.lang.Math;
import java.util.InputMismatchException;

public class Main{
	public static Scanner scanner = new Scanner(System.in);
	public static GerenciaContas contas = new GerenciaContas();
	public static String PATTERN = "\\d{3}(\\.)?\\d{3}(\\.)?\\d{3}(-)?\\d{2}";

	public static void main(String[] args) {
		boolean fst = true;
		do{
			if(!fst) scanner.nextLine();
			Panel.cls();
			System.out.println("O que deseja fazer?");
			Panel.drawMenu();
			try{
				switch(scanner.nextByte()){
					case Panel.ADD:
						adicionar();
						break;
					case Panel.REM:
						if(remover())
							System.out.println("Removido com sucesso!");
						else
							System.out.println("Erro na exclusão.");
						break;
					case Panel.MANAGE:
						manage();
						break;
					case Panel.LIST:
						listar();
						break;
					case Panel.EXT:
						System.exit(0);
					default:
						System.out.println("Opção inválida!");
						break;
				}
			} catch(InputMismatchException e){
				System.out.println("O valor inserido é inválido!");
			}
			fst = false;
		}while(true);
	}
	
	public static void adicionar() throws InputMismatchException{
		Panel.cls();
		System.out.println("Digite o tipo da conta que deseja adicionar: ");
		Panel.showTypes();
		byte tipo = scanner.nextByte();
		switch (tipo){
			case Panel.COR:
				adcContaCorrente();
				break;
			case Panel.POU:
				adcPoupanca();
				break;
			case Panel.ESP:
				adcEspecial();
				break;
			case Panel.BACK:
				return;
			default:
				System.out.println("Opção inválida!");
				break;
		}
	}

	public static void adcContaCorrente() throws InputMismatchException{
		String cpf = "";
		String nome = "";
		double limite = 0;
		boolean valido = true;
		scanner.nextLine();
		do{
			System.out.println((valido ? "Digite o seu CPF:" : "Digite um CPF válido:"));
			cpf = scanner.nextLine();
			if(!(cpf.matches(PATTERN)))
				valido = false;
			else
				valido = true;
		}while(!valido);

		System.out.println("Digite seu nome: ");
		nome = scanner.nextLine();

		System.out.println("Valor do limite: ");
		limite = scanner.nextDouble();

		contas.adicionarConta(new ContaCorrente(getRandom(), nome, cpf, limite));
		System.out.println("Inserido com sucesso!");
	}

	public static void adcPoupanca() throws InputMismatchException{
		String cpf = "";
		String nome = "";
		boolean valido = true;
		scanner.nextLine();
		do{
			System.out.println((valido ? "Digite o seu CPF:" : "Digite um CPF válido:"));
			cpf = scanner.nextLine();
			if(!(cpf.matches(PATTERN)))
				valido = false;
			else
				valido = true;
		}while(!valido);

		System.out.println("Digite seu nome: ");
		nome = scanner.nextLine();

		contas.adicionarConta(new ContaPoupanca(getRandom(), nome, cpf));
		System.out.println("Conta adicionada com sucesso!");
}

	public static void adcEspecial() throws InputMismatchException{
		String cpf = "";
		String nome = "";
		String nomeGerente = "";
		double limite = 0;
		boolean valido = true;
		scanner.nextLine();
		do{
			System.out.println((valido ? "Digite o seu CPF:" : "Digite um CPF válido:"));
			cpf = scanner.nextLine();
			if(!(cpf.matches(PATTERN)))
				valido = false;
			else
				valido = true;
		}while(!valido);

		System.out.println("Digite seu nome: ");
		nome = scanner.nextLine();

		System.out.println("Valor do limite: ");
		limite = scanner.nextDouble();

		scanner.nextLine();

		System.out.println("Digite o nome do gerente: ");
		nomeGerente = scanner.nextLine();

		contas.adicionarConta(new ContaEspecial(getRandom(), nome, cpf, limite, nomeGerente));
		System.out.println("Inserido com sucesso!");
		main(new String[0]);
	}

	public static void manage() throws InputMismatchException{
		byte operation;
		Panel.cls();
		System.out.println("Qual operação deseja realizar?");
		Panel.showOperations();
		operation = scanner.nextByte();
		switch(operation){
			case Panel.SACAR:
				sacar();
				break;
			case Panel.DEPOSITAR:
				depositar();
				break;
			case Panel.SEARCH:
				search();
				break;
			case Panel.LIMITE:
				System.out.println(contas.buscarClientesUsandoLimite());
				break;
			case Panel.ESP_ACC:
				System.out.println(contas.buscarContasEspeciais());
				break;
			case Panel.TRANSF:
				transferir();
				break;
			case Panel.RENDIMENTO:
				aplicarRendimento();
				break;
			case Panel.BACK_:
				return;
			default:
				System.out.println("Opção inválida");
		}
	}

	public static void sacar() throws InputMismatchException{
		Panel.cls();
		System.out.println("Digite a conta que deseja sacar: ");
		int numeroConta = scanner.nextInt();

		System.out.println("Digite o valor que deseja sacar: ");
		double valor = scanner.nextDouble();
		if(contas.sacar(numeroConta, valor)){
			System.out.println("Saque realizado com sucesso!");
			return;
		}
		System.out.println("Erro! Revise o número da conta e o valor a ser sacado.");
	}

	public static void transferir(){
		System.out.println("Digite a conta remetente ou 0 para voltar ao menu anterior: ");
		int c1 = scanner.nextInt();

		if(c1 == 0)
			return;

		System.out.println("Digite a conta destinatária");
		int c2 = scanner.nextInt();

		System.out.println("Digite o valor a ser transferido:");
		int v = scanner.nextInt();
		if(contas.transferirValor(c1, c2, v)){
			System.out.println("Transferência realizada com sucesso!");
		}
		else
			System.out.println("Falha! Cheque o número das contas e o valor a ser transferido.");
	}

	public static void depositar() throws InputMismatchException{
		Panel.cls();
		System.out.println("Digite a conta que deseja depositar: ");
		int numeroConta = scanner.nextInt();

		System.out.println("Digite o valor que deseja depositar: ");
		double valor = scanner.nextDouble();
		if(contas.depositar(numeroConta, valor)){
			System.out.println("Deposito realizado com sucesso!");
			return;
		}
		System.out.println("Erro! Revise o número da conta ou o valor a ser depositado.");
	}

	public static void aplicarRendimento(){
		System.out.println("Digite o número da conta ou 0 para voltar: ");
		int numConta = scanner.nextInt();
		if(numConta == 0){
			return;
		}
		else if(contas.aplicarRendimento(numConta)){
			System.out.println("Sucesso!");
		}
		else{
			System.out.println("Erro.");
		}
	}

	public static void search() throws InputMismatchException{
		int numConta;
		Panel.cls();
		System.out.println("Digite o número da conta: ");
		numConta = scanner.nextInt();
		System.out.println(contas.buscarConta(numConta).imprimir());
	}

	public static boolean remover() throws InputMismatchException{
		Panel.cls();
		System.out.println("Digite o numero da conta a ser removida: ");
		return contas.removeConta(scanner.nextInt());
	}

	public static void listar() throws InputMismatchException{
		Panel.cls();
		System.out.println(contas.listarContas());
	}

	public static int getRandom(){
		return (int) Math.floor(Math.random()*1000);
	}
}
class Panel{
	public static final byte ADD = 1;
	public static final byte REM = 2;
	public static final byte MANAGE = 3;
	public static final byte LIST = 4;
	public static final byte EXT = 5;

	public static final byte COR = 1;
	public static final byte POU = 2;
	public static final byte ESP = 3;

	public static final byte SACAR = 1;
	public static final byte DEPOSITAR = 2;
	public static final byte SEARCH = 3;
	public static final byte LIMITE = 4;
	public static final byte ESP_ACC = 5;
	public static final byte TRANSF = 6;
	public static final byte RENDIMENTO = 7;

	public static final byte BACK = 4;
	public static final byte BACK_ = 8;

	public static void drawMenu(){
		System.out.println(ADD    + " - Adicionar uma nova conta.");
		System.out.println(REM    + " - Remover uma conta.");
		System.out.println(MANAGE + " - Gerenciar uma conta.");
		System.out.println(LIST   + " - Listar todas as contas.");
		System.out.println(EXT    + " - Sair.");
	}

	public static void showTypes(){
		System.out.println(COR  + " - Conta corrente.");
		System.out.println(POU  + " - Conta poupança.");
		System.out.println(ESP  + " - Conta especial.");
		System.out.println(BACK + " - Voltar ao menu anterior.");
	}

	public static void showOperations(){
		System.out.println(SACAR      +  " - Sacar.");
		System.out.println(DEPOSITAR  +  " - Depositar.");
		System.out.println(SEARCH     +  " - Procurar conta específica.");
		System.out.println(LIMITE     +  " - Mostrar clientes usando o limite.");
		System.out.println(ESP_ACC    +  " - Listar contas especiais.");
		System.out.println(TRANSF     +  " - Transferir");
		System.out.println(RENDIMENTO +  " - Aplicar rendimento (contas poupança).");
		System.out.println(BACK_      +  " - Voltar ao menu anterior.");
	}

	public static void cls(){
		for (int i = 0; i < 12; i++) {
			System.out.println();
		}
	}
}