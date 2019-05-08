public class ContaCorrente extends Conta{
	private double limite;

	public ContaCorrente(int numeroConta, String nomeCliente, String cpf){
		super(numeroConta, nomeCliente, cpf);
		limite = 0;
	}

	public ContaCorrente(int numeroConta, String nomeCliente, String cpf, double limite){
		super(numeroConta, nomeCliente, cpf);
		setLimite(limite);
	}

	public void setLimite(double valor){
		if(valor > 0){
			limite = valor;
		}
	}

	public boolean usandoLimite(){
		if(super.getSaldo() < 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean sacar(double valor){
		if((super.getSaldo() + limite) - valor >= 0){
			super.setSaldo(super.getSaldo() - valor);
			return true;
		}
		return false;
	}

	@Override
	public String imprimir(){
		return super.imprimir() + "\n" + "Limite: " + limite;
	}
}