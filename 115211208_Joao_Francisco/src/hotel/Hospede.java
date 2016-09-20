package hotel;

import estadias.*;
import java.util.*;

public class Hospede {
	
	String nome, email, anoNascimento;
	List<Estadia> estadias = new ArrayList<Estadia>();
	
	public Hospede(String nome, String email, String anoNascimento) throws Exception{
		String simbolos = "!@#$%¨&*_-+=~^?:;[{]}";
		String[] anoString = anoNascimento.split("/");
		
		if ((nome.equals("")) || (nome.contains("  ") || (nome.equals(" ")))){
			throw new Exception("Erro no cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");
			
		}else if ((email.equals("")) || (email.contains("  ")) || (email.equals(" "))){
			throw new Exception("Erro no cadastro de Hospede. Email do(a) hospede nao pode ser vazio.");
			
		}else if ((!email.contains("@")) || (!email.contains("."))){
			throw new Exception("Erro no cadastro de Hospede. Email do(a) hospede esta invalido.");
			
		}else if ((anoNascimento.equals("")) || (anoNascimento.contains("  ") || (anoNascimento.equals(" ")))){
			throw new Exception("Erro no cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.");
			
		}else if ((!(anoNascimento.length() == 10)) || (anoNascimento.contains("."))){
			throw new Exception("Erro no cadastro de Hospede. Formato de data invalido.");
		}
		
		int ano = Integer.parseInt(anoString[2]);
		if ((2016 - ano) < 18){
			throw new Exception("Erro no cadastro de Hospede. A idade do(a) hospede deve ser maior que 18 anos.");
		}
			
		for (String simb : simbolos.split("")){
			if (nome.contains(simb)){
				throw new Exception("Erro no cadastro de Hospede. Nome do(a) hospede esta invalido.");
			}
		}
		this.nome = nome;
		this.email = email;
		this.anoNascimento = anoNascimento;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public String getAnoNascimento(){
		return this.anoNascimento;
	}
	
	public String getNumHospedagens(){
		String numHospedagens = "";
		return numHospedagens += this.estadias.size();
	}
	
	public String getQuartos(){
		String quartos = "";
		for (int i = 0; i < estadias.size(); i++){
			if (estadias.size() == (i+1)){
				quartos += (estadias.get(i).getQuarto().getId());
			}else{
				quartos += ((estadias.get(i).getQuarto().getId()) + ",");
			}
		}
		return quartos;
	}
	
	public List<Estadia> getEstadias(){
		return this.estadias;
	}
	
	public Estadia getEstadiaPorId(String id){
		for (Estadia estadia : estadias){
			if (estadia.getQuarto().getId().equals(id)){
				return estadia;
			}
		}
		return null;
	}
	
	public void setNome(String novoNome){
		this.nome = novoNome;
	}
	
	public void setEmail(String novoEmail){
		this.email = novoEmail;
	}
	
	public void setAnoNascimento(String novoAnoNascimento){
		this.anoNascimento = novoAnoNascimento;
	}
	
	public void addEstadia(Estadia estadia){
		this.estadias.add(estadia);
	}
	
	public double totalPagoEstadiasDouble(){
		
		double total = 0;
		
		for (Estadia itr : estadias){
			total += itr.totalPago();
		}
		return total;
	}
	
public String totalPagoEstadiasString(){
		
		double total = 0;
		String valor = "R$";
		
		for (Estadia itr : estadias){
			total += itr.totalPago();
		}
		return valor += ((int)total) + ",00";
	}
	
	public String toString(){
		return this.nome + " - " + this.email;
	}

}
