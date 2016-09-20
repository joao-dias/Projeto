package hotel;

import java.time.LocalDate;
import java.util.*;

import easyaccept.EasyAccept;
import estadias.*;
import restaurante.*;

public class ControleHotel {
	Map<String,Hospede> listaHospedes = new HashMap<String,Hospede>();
	List<Checkout> historicoLucros = new ArrayList<Checkout>();
	Map<String,Prato> cardapioPratos = new HashMap<String,Prato>();
	Map<String,Refeicao> cardapioRefeicao = new HashMap<String,Refeicao>();
	
	public String cadastraHospede(String nome, String email, String anoNascimento) throws Exception{
		Hospede hospede = new Hospede(nome, email, anoNascimento);
		
		listaHospedes.put(email, hospede);
		return hospede.getEmail();
	}
	
	public boolean removeHospede(String email) throws Exception{
		String[] arrayString = email.split("");
		
		if ((email.equals("")) || (email.contains("  ")) || (email.equals(" "))){
			throw new Exception("Erro na remocao do Hospede. Formato de email invalido.");
			
		}else if ((!email.contains("@")) || (!email.contains(".")) || (arrayString[0].equals("@"))){
			throw new Exception("Erro na remocao do Hospede. Formato de email invalido.");
		}
		
		if (listaHospedes.containsKey(email)){
			listaHospedes.remove(email);
			return true;
		}else{
			return false;
		}
	}
	
	public Hospede buscaHospede(String email){
		if (listaHospedes.containsKey(email)){
			return listaHospedes.get(email);
		}else{
			return null;
		}
	}
	
	public boolean buscaHospedeBoolean(String email){
		return listaHospedes.containsKey(email);
	}
	
	public boolean realizaCheckin(String email, int numDias, String id, String tipoQuarto) throws Exception{
		String[] arrayString = email.split("");
		String simbolos = "!@#$%¨&*_-+=~^?:;[{]}";
		
		if ((email.equals("")) || (email.contains("  ")) || (email.equals(" "))){
			throw new Exception("Erro ao realizar checkin. Email do(a) hospede nao pode ser vazio.");
			
		}else if ((!email.contains("@")) || (!email.contains(".")) || (arrayString[0].equals("@"))){
			throw new Exception("Erro ao realizar checkin. Email do(a) hospede esta invalido.");
			
		}else if (numDias <= 0){
			throw new Exception("Erro ao realizar checkin. Quantidade de dias esta invalida.");
			
		}else if ((id.equals("")) || (id.contains("  ")) || (id.equals(" "))){
			throw new Exception("Erro ao realizar checkin. ID do quarto invalido, use apenas numeros ou letras.");
			
		}else if ((!tipoQuarto.toLowerCase().equals("simples")) && (!tipoQuarto.toLowerCase().equals("luxo")) && (!tipoQuarto.toLowerCase().equals("presidencial"))){
			throw new Exception("Erro ao realizar checkin. Tipo de quarto invalido.");
		}
		
		for (String simb : simbolos.split("")){
			if ((id.contains(simb))){
				throw new Exception("Erro ao realizar checkin. ID do quarto invalido, use apenas numeros ou letras.");
			}
		}
		
		for (Hospede hospedeAtual : listaHospedes.values()){
			for (Estadia estadiaAtual : hospedeAtual.getEstadias()){
				if (estadiaAtual.getQuarto().getId().equals(id)){
					throw new Exception("Erro ao realizar checkin. Quarto " + id + " ja esta ocupado.");
				}
			}
		}
		
		FactoryDeQuarto factory = new FactoryDeQuarto();
		
		if (listaHospedes.containsKey(email)){
			Estadia estadia = new Estadia(factory.getQuarto(tipoQuarto, id), numDias);
			listaHospedes.get(email).addEstadia(estadia);
			return true;
		}
		return false;
	}
	
	public String getInfoHospede(String email, String info) throws Exception{       
		if (!listaHospedes.containsKey(email)){
			throw new Exception("Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
		}else if ((email.equals("")) || (email.contains("  ")) || (email.equals(" "))){
			throw new Exception("Erro na consulta de hospede. Email do(a) hospede nao pode ser vazio");
			
		}else if ((info.equals("")) || (info.contains("  ")) || (info.equals(" "))){
			throw new Exception("Erro na consulta de hospede. Tipo de informacao do(a) hospede nao pode ser vazio");
			
		}else if ((!info.toLowerCase().equals("nome")) && (!info.toLowerCase().equals("data de nascimento")) && (!info.toLowerCase().equals("email"))){
			throw new Exception("Erro na consulta de hospede. Tipo de informacao do(a) hospede esta invalido");
			
		}
		
		if (info.toLowerCase().equals("nome")){
			return listaHospedes.get(email).getNome();
			
		}else if (info.toLowerCase().equals("data de nascimento")){
			return listaHospedes.get(email).getAnoNascimento();
			
		}else if (info.toLowerCase().equals("email")){
			return listaHospedes.get(email).getEmail();
		}
		return null;
	}
	
	public String getInfoHospedagem(String email, String info) throws Exception{
		String[] arrayString = email.split("");
		
		if ((email.equals("")) || (email.contains("  ")) || (email.equals(" "))){
			throw new Exception("Erro ao checar hospedagem ativa. Email do(a) hospede nao pode ser vazio.");
			
		}else if ((!email.contains("@")) || (!email.contains(".")) || (arrayString[0].equals("@"))){
			throw new Exception("Erro ao checar hospedagem ativa. Email do(a) hospede esta invalido.");	
		}
		
		if ((listaHospedes.get(email).getEstadias().size() == 0) && (listaHospedes.containsKey(email))){
			throw new Exception("Erro na consulta de hospedagem. Hospede " + listaHospedes.get(email).getNome() + " nao esta hospedado(a).");
		}
		
		if (info.toLowerCase().equals("hospedagens ativas")){
			return listaHospedes.get(email).getNumHospedagens();
		
		}else if (info.toLowerCase().equals("quarto")){
			return listaHospedes.get(email).getQuartos();
		
		}else if (info.toLowerCase().equals("total")){
			return listaHospedes.get(email).totalPagoEstadiasString();
		}
		return null;
		}
	
	public void atualizaCadastro(String email, String info, String novaInfo) throws Exception{
		String simbolos = "!@#$%¨&*_-+=~^?:;[{]}";
		String[] anoString = novaInfo.split("/");
		
		if ((info.toLowerCase().equals("nome")) && ((novaInfo.equals("")) || (novaInfo.contains("  ") || (novaInfo.equals(" "))))){
			throw new Exception("Erro na atualizacao do cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");
			
		}else if ((info.toLowerCase().equals("email")) && ((!novaInfo.contains("@")) || (!novaInfo.contains(".")))){
			throw new Exception("Erro na atualizacao do cadastro de Hospede. Email do(a) hospede esta invalido.");
			
		}else if ((info.toLowerCase().equals("data de nascimento")) && ((novaInfo.equals("")) || (novaInfo.contains("  ") || (novaInfo.equals(" "))))){
			throw new Exception("Erro na atualizacao do cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.");
			
		}else if ((info.toLowerCase().equals("data de nascimento")) && ((!(novaInfo.length() == 10)) || (novaInfo.contains(".")))){
			throw new Exception("Erro na atualizacao do cadastro de Hospede. Formato de data invalido.");
			
		}else if (info.toLowerCase().equals("data de nascimento")){
			
			int ano = Integer.parseInt(anoString[2]);
			int mes = Integer.parseInt(anoString[1]);
			int dia = Integer.parseInt(anoString[0]);
			
			if (mes > 12){
				throw new Exception("Erro na atualizacao do cadastro de Hospede. Formato de data invalido.");
				
			}else if (dia > 31){
				throw new Exception("Erro na atualizacao do cadastro de Hospede. Formato de data invalido.");
			}
				
			if ((2016 - ano) < 18){
				throw new Exception("Erro na atualizacao do cadastro de Hospede. A idade do(a) hospede deve ser maior que 18 anos.");
			}
		}
		
		for (String simb : simbolos.split("")){
			if ((novaInfo.contains(simb)) && (info.toLowerCase().equals("nome"))){
				throw new Exception("Erro na atualizacao do cadastro de Hospede. Nome do(a) hospede esta invalido.");
			}
		}
		
		if (info.toLowerCase().equals("nome")){
			listaHospedes.get(email).setNome(novaInfo);
			
		}else if (info.toLowerCase().equals("data de nascimento")){
			listaHospedes.get(email).setAnoNascimento(novaInfo);
			
		}else if (info.toLowerCase().equals("email")){
			listaHospedes.get(email).setEmail(novaInfo);
			listaHospedes.put(novaInfo, listaHospedes.get(email));
			listaHospedes.remove(email);
		}
	}
	
	public String realizaCheckout(String email, String id) throws Exception{
		String[] arrayString = email.split("");
		String simbolos = "!@#$%¨&*_-+=~^?:;[{]}";
		
		if ((email.equals("")) || (email.contains("  ")) || (email.equals(" "))){
			throw new Exception("Erro ao realizar checkout. Email do(a) hospede nao pode ser vazio.");
			
		}else if ((!email.contains("@")) || (!email.contains(".")) || (arrayString[0].equals("@"))){
			throw new Exception("Erro ao realizar checkout. Email do(a) hospede esta invalido.");	
		}
		
		for (String simb : simbolos.split("")){
			if ((id.contains(simb))){
				throw new Exception("Erro ao realizar checkout. ID do quarto invalido, use apenas numeros ou letras.");
			}
		}
		
		LocalDate data = LocalDate.now();
		String total = listaHospedes.get(email).getEstadiaPorId(id).totalPagoString();
		Checkout checkout = new Checkout(data, listaHospedes.get(email).getNome(), id, listaHospedes.get(email).getEstadiaPorId(id).totalPago());
		
		historicoLucros.add(checkout);
		for (int i = 0; i < listaHospedes.get(email).getEstadias().size(); i++){
			if (listaHospedes.get(email).getEstadias().get(i).getQuarto().getId().equals(id)){
				listaHospedes.get(email).getEstadias().remove(i);
			}
		}
		return total;
	}
	
	public String consultaTransacoes(String info) throws Exception{
		if ((info.equals("")) || (info.contains("  ")) || (info.equals(" "))){
			throw new Exception("Erro na consulta de transacoes. Email do(a) hospede nao pode ser vazio.");
			
		}
		
		if (info.toLowerCase().equals("quantidade")){
			String qtd = "";
			
			return (qtd += historicoLucros.size());
			
		}else if (info.toLowerCase().equals("total")){
			double total = 0;
			
			for (Checkout checkout : historicoLucros){
				total += checkout.getTotalPago();
			}
			
			return "R$" + (int)total + ",00";
			
		}else if (info.toLowerCase().equals("nome")){
			String nomes = "";
			int contador = 0;
			
			for (Checkout checkout : historicoLucros){
				if (contador == (historicoLucros.size() - 1)){
					nomes += checkout.getNomeHospede();
					contador += 1;
					
				}else{
					nomes += checkout.getNomeHospede() + ";";
					contador += 1;
				}
			}
			return nomes;
		}
		return null;
	}
	
	public String consultaTransacoes(String info, int indice) throws Exception{
		if (indice < 0){
			throw new Exception("Erro na consulta de transacoes. Indice invalido.");
			
		}else if ((info.equals("")) || (info.contains("  ")) || (info.equals(" "))){
			throw new Exception("Erro na consulta de transacoes. Email do(a) hospede nao pode ser vazio.");
			
		}else if ((info.toLowerCase().equals("preco")) || (info.toLowerCase().equals("descricao"))){
			throw new Exception("Erro na consulta de transacoes. Tipo de informacao invalida.");
		}
		
		if (info.toLowerCase().equals("total")){
			String totalString = "R$";
			return totalString += (int)(historicoLucros.get(indice).getTotalPago()) + ",00";
			
		}else if (info.toLowerCase().equals("nome")){
			return historicoLucros.get(indice).getNomeHospede();
		}
		return null;
	}
	
	public boolean cadastraPrato(String nome, double preco, String descricao) throws Exception{
		if ((nome.equals("")) || (nome.contains("  ")) || (nome.equals(" "))){
			throw new Exception("Erro no cadastro do prato. Nome do prato esta vazio.");
			
		}else if ((descricao.equals("")) || (descricao.contains("  ")) || (descricao.equals(" "))){
			throw new Exception("Erro no cadastro do prato. Descricao do prato esta vazia.");
			
		}else if (preco <= 0){
			throw new Exception("Erro no cadastro do prato. Preco do prato eh invalido.");
		}
		
		Prato prato = new Prato(nome, preco, descricao);
		cardapioPratos.put(nome, prato);
		
		return true;
	}
	
	public boolean atualizaPrato(String nome, String info, String novaInfo) throws Exception{
		String simbolos = "@#$%¨*-+=~^?:;";
		
		if ((nome.equals("")) || (nome.contains("  ")) || (nome.equals(" "))){
			throw new Exception("Erro ao atualizar prato. Nome do prato esta vazio.");
			
		}else if (((novaInfo.equals("")) || (novaInfo.contains("  ")) || (novaInfo.equals(" "))) && (info.toLowerCase().equals("nome"))){
			throw new Exception("Erro ao atualizar prato. Novo nome do prato esta vazio.");
			
		}else if (((novaInfo.equals("")) || (novaInfo.contains("  ")) || (novaInfo.equals(" "))) && (info.toLowerCase().equals("preco"))){
			throw new Exception("Erro ao atualizar prato. Novo preco do prato esta vazio.");
			
		}else if (((novaInfo.equals("")) || (novaInfo.contains("  ")) || (novaInfo.equals(" "))) && (info.toLowerCase().equals("descricao"))){
			throw new Exception("Erro ao atualizar prato. Nova descricao do prato esta vazio.");
		}
		
		for (String simb : simbolos.split("")){
			if ((nome.contains(simb))){
				throw new Exception("Erro ao atualizar prato. Caracteres invalidos.");
				
			}else if ((novaInfo.contains(simb))){
				throw new Exception("Erro ao atualizar prato. Caracteres invalidos.");
			}
		}
		
		if (info.toLowerCase().equals("descricao")){
			cardapioPratos.get(nome).setDescricao(novaInfo);
			return true;
			
		}else if (info.toLowerCase().equals("nome")){
			cardapioPratos.get(nome).setNome(novaInfo);
			cardapioPratos.put(novaInfo, cardapioPratos.get(nome));
			cardapioPratos.remove(nome);
			return true;
			
		}else if (info.toLowerCase().equals("preco")){
			double preco = Double.parseDouble(novaInfo);
			
			if (preco <= 0){
				throw new Exception("Erro ao atualizar prato. Novo preco do prato invalido.");
			}
			
			cardapioPratos.get(nome).setPreco(preco);
			return true;
		}
		return false;
	}
	
	public boolean removePrato(String nome) throws Exception{
		if ((nome.equals("")) || (nome.contains("  ")) || (nome.equals(" "))){
			throw new Exception("Erro ao remover prato. Nome do prato esta vazio.");
		}else if (!cardapioPratos.containsKey(nome)){
			throw new Exception("Erro ao remover prato. Prato nao cadastrado no cardapio.");
		}
		cardapioPratos.remove(nome);
		return true;
	}
	
	public boolean cadastraRefeicao(String nome, String descricao, String pratos) throws Exception{
		String[] listaPratos = pratos.split(";");
		
		if ((nome.equals("")) || (nome.contains("  ")) || (nome.equals(" "))){
			throw new Exception("Erro no cadastro de refeicao. Nome da refeicao esta vazio.");
			
		}else if ((descricao.equals("")) || (descricao.contains("  ")) || (descricao.equals(" "))){
			throw new Exception("Erro no cadastro de refeicao. Descricao da refeicao esta vazia.");
			
		}else if ((pratos.equals("")) || (pratos.contains("  ")) || (pratos.equals(" "))){
			throw new Exception("Erro no cadastro de refeicao. Componente(s) esta(o) vazio(s).");
			
		}else if ((listaPratos.length < 3) || (listaPratos.length > 4)){
			throw new Exception("Erro no cadastro de refeicao completa. Uma refeicao completa deve possuir no minimo 3 e no maximo 4 pratos.");
		}
		
		Refeicao refeicao = new Refeicao(nome, descricao, pratos);
		cardapioRefeicao.put(nome, refeicao);
		
		for (String prato : listaPratos){
			if (cardapioPratos.containsKey(prato)){
				refeicao.getListaPratos().add(cardapioPratos.get(prato));
				
			}else{
				throw new Exception("Erro no cadastro de refeicao. So eh possivel cadastrar refeicoes com pratos ja cadastrados.");
			}
		}
		return true;
	}
	
	public boolean atualizaRefeicao(String nome, String info, String novaInfo) throws Exception{
		String simbolos = "@#$%¨*-+=~^?:;";
		
		if ((nome.equals("")) || (nome.contains("  ")) || (nome.equals(" "))){
			throw new Exception("Erro ao atualizar refeicao. Nome da refeicao esta vazio.");
			
		}else if (((novaInfo.equals("")) || (novaInfo.contains("  ")) || (novaInfo.equals(" "))) && (info.toLowerCase().equals("nome"))){
			throw new Exception("Erro ao atualizar refeicao. Novo nome da refeicao esta vazio.");
			
		}else if (((novaInfo.equals("")) || (novaInfo.contains("  ")) || (novaInfo.equals(" "))) && (info.toLowerCase().equals("descricao"))){
			throw new Exception("Erro ao atualizar refeicao. Nova descricao da refeicao esta vazio.");
		}
		
		for (String simb : simbolos.split("")){
			if ((nome.contains(simb))){
				throw new Exception("Erro ao atualizar refeicao. Caracteres invalidos.");
			}else if ((novaInfo.contains(simb))){
				throw new Exception("Erro ao atualizar refeicao. Caracteres invalidos.");
			}
		}
		
		if (info.toLowerCase().equals("descricao")){
			cardapioRefeicao.get(nome).setDescricao(novaInfo);
			return true;
			
		}else if (info.toLowerCase().equals("nome")){
			cardapioRefeicao.get(nome).setNome(novaInfo);
			return true;
		}
		return false;
	}
	
	public boolean removeRefeicao(String nome) throws Exception{
		if ((nome.equals("")) || (nome.contains("  ")) || (nome.equals(" "))){
			throw new Exception("Erro ao remover refeicao. Nome da refeicao esta vazio.");
		}else if (!cardapioRefeicao.containsKey(nome)){
			throw new Exception("Erro ao remover refeicao. Refeicao nao cadastrada no cardapio.");
		}
		cardapioRefeicao.remove(nome);
		return true;
	}
	
	public String consultaRestaurante(String nome, String info) throws Exception{
		if ((nome.equals("")) || (nome.contains("  ")) || (nome.equals(" "))){
			throw new Exception("Erro na consulta do restaurante. Nome do prato esto vazio.");
			
		}else if ((info.equals("")) || (info.contains("  ")) || (info.equals(" "))){
			throw new Exception("Erro na consulta do restaurante. Tipo de informacao esta vazia.");
			
		}else if (!(info.toLowerCase().equals("preco")) && (!info.toLowerCase().equals("descricao"))){
			throw new Exception("Erro no cadastro do prato. Tipo de informacao invalida.");
		}
		
		if (cardapioPratos.containsKey(nome)){
			if (info.toLowerCase().equals("preco")){
				
				String totalString = "R$" + cardapioPratos.get(nome).getPreco();
				String novaString = totalString.replace(".", ",");
				
				if (novaString.contains(",0")){
					novaString += "0";
					
				}else if(!novaString.contains("0,")){
					novaString += "0";
					
				}
				return novaString;
			}else if (info.toLowerCase().equals("descricao")){
				return cardapioPratos.get(nome).getDescricao();
				
			}
		}else if (cardapioRefeicao.containsKey(nome)){
			
			if (info.toLowerCase().equals("preco")){
				
				String totalString = "R$" + cardapioRefeicao.get(nome).getPrecoTotal();
				String novaString = totalString.replace(".", ",");
				
				if (novaString.contains(",0")){
					novaString += "0";
					
				}else if((!novaString.contains("0,")) && (novaString.contains(",0"))){
					novaString += "0";
					
				}
				return novaString;
				
			}else if (info.toLowerCase().equals("descricao")){
				return cardapioRefeicao.get(nome).toString();
				
			}
		}
		throw new Exception("Erro na consulta do restaurante. Prato ou refeicao nao cadastrados.");
	}
	
	public void iniciaSistema(){}
	public void fechaSistema(){}
	
	public static void main(String[] args) {
		args = new String[] {"hotel.ControleHotel","acceptance_test/testes_uc1", "acceptance_test/testes_uc2", "acceptance_test/testes_uc3", "acceptance_test/testes_uc4", "acceptance_test/testes_uc1_exception", "acceptance_test/testes_uc2_exception", "acceptance_test/testes_uc3_exception", "acceptance_test/testes_uc4_exception"};
		EasyAccept.main(args);
	}	
}
