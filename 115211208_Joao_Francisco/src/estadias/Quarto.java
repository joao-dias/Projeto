package estadias;

public abstract class Quarto {
	
	String id;
	
	public Quarto(String id){
		this.id = id;
	}
	
	public String getId(){
		return this.id;
	}
	
	public abstract double getValorDiaria();
	
	public abstract void setValorDiaria(double novoValor);
	
	public void setId(String novoId){
		this.id = novoId;
	}
	
}
