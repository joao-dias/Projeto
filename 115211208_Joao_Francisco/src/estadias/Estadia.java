package estadias;

public class Estadia {
	
	Quarto quarto;
	int numDias;
	
	public Estadia(Quarto quarto, int numDias){
		this.quarto = quarto;
		this.numDias = numDias;
	}
	
	public Quarto getQuarto(){
		return this.quarto;
	}
	
	public int getNumDias(){
		return this.numDias;
	}
	
	public void setNumDias(int novoNumDias){
		this.numDias = novoNumDias;
	}
	
	public double totalPago(){
		return (this.numDias * this.quarto.getValorDiaria());
	}
	
	public String totalPagoString(){
		return "R$" + ((int)(this.numDias * this.quarto.getValorDiaria())) + ",00";
	}
}
