package estadias;

public class QuartoSimples extends Quarto {
	double valorDiaria = 100;
	
	public QuartoSimples(String id){
		super(id);
	}
	
	@Override
	public double getValorDiaria(){
		return this.valorDiaria;
	}
	
	@Override
	public void setValorDiaria(double novoValor){
		this.valorDiaria = novoValor;
	}
	
	public String toString(){
		return "Quarto Simples " + this.id + " - Valor da Diária: " + this.getValorDiaria() + " R$";
	}
}
