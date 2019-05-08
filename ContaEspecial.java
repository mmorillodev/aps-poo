public class ContaEspecial extends ContaCorrente{
	private String nomeGerente;

	public ContaEspecial(int numeroConta, String nomeCliente, String cpf, double limite, String nomeGerente){
		super(numeroConta, nomeCliente, cpf, limite);
		setNomeGerente(nomeGerente);
	}

	public void setNomeGerente(String nomeGerente){
		this.nomeGerente = nomeGerente;
	}

	public String getNomeGerente(){
		return nomeGerente;
	}

	@Override
	public String imprimir(){
		return super.imprimir() + "\nNome do gerente: " + nomeGerente;
	}
}