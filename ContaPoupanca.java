public class ContaPoupanca extends Conta{
	public static final double RENDIMENTO = 0.005;	
	public ContaPoupanca(int numConta, String nome, String cpf){
		super(numConta, nome, cpf);
	}

	public void calculaRendimento(double rendimento){
		super.setSaldo(super.getSaldo() * (1 + rendimento));
	}
}