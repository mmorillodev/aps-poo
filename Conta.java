public abstract class Conta{
	private int numConta;
	private String nomeCliente;
	private String cpf;
	private double saldo;


	public Conta(int numConta, String nomeCliente, String cpf){
		setNumConta(numConta);
		setNomeCliente(nomeCliente);
		setCpf(cpf);
	}

	public boolean sacar(double valorSacado){
		if(saldo - valorSacado > 0){
			setSaldo(saldo - valorSacado);
			return true;
		}
		return false;
	}

	public boolean depositar(double valorDepositado){
		if(valorDepositado > 0){
			setSaldo(saldo + valorDepositado);
			return true;
		}
		return false;
	}

	public String imprimir(){
		return "Numero da conta: " + numConta + "\n" +
				"Nome do cliente: " + nomeCliente + "\n" +
				"CPF: " + cpf + "\n" + 
				"Saldo: " + saldo;
	}

	private void setNumConta(int numConta){
		this.numConta = numConta;
	}

	public int getNumConta(){
		return numConta;
	}

	public void setNomeCliente(String nomeCliente){
		this.nomeCliente = nomeCliente;
	}

	public String getNomeCliente(){
		return nomeCliente;
	}

	private void setCpf(String cpf){
		this.cpf = cpf;
	}

	public String getCpf(){
		return cpf;
	}

	protected void setSaldo(double saldo){
		this.saldo = saldo;
	}

	public double getSaldo(){
		return saldo;
	}

}