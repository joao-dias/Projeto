package estadias;

public class QuartoPresidencial extends Quarto{
	double valorDiaria = 450;
	
	public QuartoPresidencial(String id){
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
		return "Quarto Presidencial " + this.id + " - Valor da Diária: " + this.getValorDiaria() + " R$";
	}
}
