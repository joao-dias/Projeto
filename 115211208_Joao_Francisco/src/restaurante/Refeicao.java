package restaurante;

import java.util.*;

public class Refeicao {
	List<Prato> listaPratos = new ArrayList<Prato>();
	String nome, descricao, pratos;
	
	public Refeicao(String nome, String descricao, String pratos){
		this.nome = nome;
		this.descricao = descricao;
		this.pratos = pratos;
	}

	public List<Prato> getListaPratos() {
		return this.listaPratos;
	}

	public void setListaPratos(List<Prato> listaPratos) {
		this.listaPratos = listaPratos;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPratos() {
		return this.pratos;
	}

	public void setPratos(String pratos) {
		this.pratos = pratos;
	}
	
	public double getPrecoTotal(){
		double preco = 0;
		for (Prato prato : listaPratos){
			preco += prato.getPreco();
		}
		preco -= (preco * 0.1);
		return preco;
	}
	
	public String toString(){
		String description = this.descricao + " Serao servidos: ";
		int i = 1;
		for (String pratoAtual : pratos.split(";")){
			if (i == pratos.split(";").length){
				description += "(" + i + ") " + pratoAtual + ".";
				i += 1;
			}else{
				description += "(" + i + ") " + pratoAtual + ", ";
				i += 1;
			}
		}
		return description;
	}

}
