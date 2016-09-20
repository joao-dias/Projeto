package restaurante;

public class Prato {
	String nome, descricao;
	double preco;
	
	public Prato(String nome, double preco, String descricao){
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public double getPreco(){
		return this.preco;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
	
	public void setNome(String novoNome){
		this.nome = novoNome;
	}
	
	public void setPreco(double novoPreco){
		this.preco = novoPreco;
	}
	
	public void setDescricao(String novaDescricao){
		this.descricao = novaDescricao;
	}
	
	public String toString(){
		return "R$" + this.preco + " " + this.nome + " - " + this.descricao;
	}
	
	
	
	
	
	

}
