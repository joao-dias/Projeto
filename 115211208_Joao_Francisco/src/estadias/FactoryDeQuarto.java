package estadias;

public class FactoryDeQuarto {
	
	public Quarto getQuarto(String tipoQuarto, String id) throws Exception{
		if ((tipoQuarto.toLowerCase().equals("quartosimples")) || (tipoQuarto.toLowerCase().equals("quarto simples")) || (tipoQuarto.toLowerCase().equals("simples"))){
			return new QuartoSimples(id);
		}else if ((tipoQuarto.toLowerCase().equals("quartoluxo")) || (tipoQuarto.toLowerCase().equals("quarto de luxo")) || (tipoQuarto.toLowerCase().equals("quartodeluxo")) || (tipoQuarto.toLowerCase().equals("luxo"))){
			return new QuartoLuxo(id);
		}else if ((tipoQuarto.toLowerCase().equals("quartopresidencial")) || (tipoQuarto.toLowerCase().equals("quarto presidencial")) || (tipoQuarto.toLowerCase().equals("presidencial"))){
			return new QuartoPresidencial(id);
		}else{
			return null;
		}
	}
}
