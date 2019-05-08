import java.util.ArrayList;
public class GerenciaContas{
	ArrayList<Conta> contas = new ArrayList<Conta>();

	public void adicionarConta(Conta c){
		contas.add(c);
	}

	public boolean removeConta(int numero){
		for(int i = 0; i < contas.size(); i++){
			if(contas.get(i).getNumConta() == numero){
				contas.remove(i);
				return true;
			}
		}
		return false;
	}

	public String buscarContasEspeciais(){
		String statement = "";
		for(int i = 0, j = 1; i < contas.size(); i++){
			if(contas.get(i) instanceof ContaEspecial){
				statement += "---------------------------\n";
				statement += "Conta " + j + ": " + "\n";
				statement += contas.get(i).imprimir() + "\n";
				j++;
			}
		}
		return statement;
	}

	public String buscarClientesUsandoLimite(){
		String statement = "";
		ContaCorrente conta;
		for(int i = 0, j = 1; i < contas.size(); i++){
			if((contas.get(i) instanceof ContaEspecial || contas.get(i) instanceof ContaCorrente)){
				conta = (ContaCorrente) contas.get(i);
				if(conta.usandoLimite()){
					statement += "Conta " + j + ": " + "\n";
					statement += contas.get(i).imprimir() + "\n";
					j++;
				}
			}
		}
		return statement;
	}

	public Conta buscarConta(int numConta){
		for (Conta c : contas) {
			if(c.getNumConta() == numConta)
				return c;
		}
		return new ContaCorrente(0, null, null);
	}

	public boolean sacar(int numConta, double valor){
		for (Conta c : contas) {
			if(c.getNumConta() == numConta){
				return c.sacar(valor);
			}
		}
		return false;
	}

	public boolean depositar(int numConta, double valor){
		if(valor < 0)
			return false;
		for (Conta c : contas) {
			if(c.getNumConta() == numConta){
				return c.depositar(valor);
			}
		}
		return false;
	}

	public boolean aplicarRendimento(int numConta){
		for (Conta c : contas) {
			if(c.getNumConta() == numConta){
				if(c instanceof ContaPoupanca){
					ContaPoupanca conta = (ContaPoupanca) c;
					conta.calculaRendimento(ContaPoupanca.RENDIMENTO);
					return true;
				}
				else{
					System.out.println("A conta não é poupança!");
					return false;
				}
			}
		}
		return false;
	}

	public boolean transferirValor(int numeroConta1, int numeroConta2, double valor){
		if(sacar(numeroConta1, valor)){
			if(depositar(numeroConta2, valor)){
				return true;
			}
			else{
				depositar(numeroConta1, valor);
			}
		}
		else{
			return false;
		}
		return false;
	}

	public String listarContas(){
		String statement = "";
		for(int i = 0; i < contas.size(); i++){
			statement += "---------------------------\n";
			statement += "Conta " + (i + 1) + ": " + "\n";
			statement += contas.get(i).imprimir() + "\n";
		}
		return statement;
	}

	public ArrayList<Conta> returnContas(){
		return contas;
	}
}