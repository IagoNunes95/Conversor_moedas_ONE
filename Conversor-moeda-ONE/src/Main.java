import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		ConversorMoedaService conversor = new ConversorMoedaService();
		
		//criando array das moedas para o nosso menu.
		String[] moedas = { "BRL", "USD", "EUR", "GBP", "JPY", "ARS",};
		String[] nomes = {
			    "BRL (Real Brasileiro)",
			    "USD (Dólar Americano)",
			    "EUR (Euro)",
			    "GBP (Libra Esterlina)",
			    "JPY (Iene Japonês)",
			    "ARS (Peso Argentino)"
			};

		System.out.println("========== CONVERSOR DE MOEDAS ==========\n");

		//laço para leitura do array para um menu mais simples.
		System.out.println("Escolha a moeda de ORIGEM:");
		for (int i = 0; i < nomes.length; i++) {
			System.out.printf("%d - %s\n", i + 1, nomes[i]);
		}
		System.out.print("Digite o número da moeda de origem: ");
		int opcaoOrigem = scanner.nextInt();
		String moedaOrigem = moedas[opcaoOrigem - 1];

		
		System.out.println("\nEscolha a moeda de DESTINO:");
		for (int i = 0; i < nomes.length; i++) {
			System.out.printf("%d - %s\n", i + 1, nomes[i]);
		}
		System.out.print("Digite o número da moeda de destino: ");
		int opcaoDestino = scanner.nextInt();
		String moedaDestino = moedas[opcaoDestino - 1];

		
		System.out.print("\nDigite o valor que deseja converter: ");
		double valor = scanner.nextDouble();

		System.out.println("\nConvertendo...");
		
		//iniciando nossa API para retornar a conversão das moedas.
		try {
			double resultado = conversor.converter(moedaOrigem, moedaDestino, valor);
			System.out.printf("\n%.2f %s equivalem a %.2f %s\n", valor, moedaOrigem, resultado, moedaDestino);
		} catch (Exception e) {
			System.out.println("Erro ao converter moedas: " + e.getMessage());
		}

		scanner.close();
	}

}
