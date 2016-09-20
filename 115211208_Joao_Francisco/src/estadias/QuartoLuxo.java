package estadias;

public class QuartoLuxo extends Quarto {
	double valorDiaria = 250;
	
	public QuartoLuxo(String id){
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
		return "Quarto De Luxo " + this.id + " - Valor da Diária: " + this.getValorDiaria() + " R$";
	}
}
