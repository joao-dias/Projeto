package hotel;

import java.time.*;

public class Checkout {
	LocalDate data;
	String nomeHospede, idQuarto;
	double totalPago;
	
	public Checkout(LocalDate data, String nomeHospede, String idQuarto, double totalPago){
		this.data = data;
		this.nomeHospede = nomeHospede;
		this.idQuarto = idQuarto;
		this.totalPago = totalPago;
	}
	
	public LocalDate getData(){
		return data;
	}
	
	public String getNomeHospede(){
		return nomeHospede;
	}
	
	public String getIdQuarto(){
		return idQuarto;
	}
	
	public double getTotalPago(){
		return totalPago;
	}
	
	public void setNomeHospede(String novoNome){
		this.nomeHospede = novoNome;
	}
	
	public void setIdQuarto(String novoId){
		this.idQuarto = novoId;
	}
	
	public void setTotalPago(double novoValor){
		this.totalPago = novoValor;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
