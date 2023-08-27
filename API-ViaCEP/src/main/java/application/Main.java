package application;

import controller.EnderecoController;
import exception.NumeroMaiorQuePermitidoException;
import model.Endereco;
import utilities.GeradorDeArquivo;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner sc = new Scanner(System.in);
		String op = "";
		String busca = "00000000";

		do {
			System.out.println("Digite o CEP: ");
			busca = sc.nextLine();

			if(busca.toString().length() > 8){
				throw new NumeroMaiorQuePermitidoException("Número maior que o permitido. Digite apenas o CEP com 8 dígitos!");
			}

			Endereco endereco = new EnderecoController().buscaEndereco(busca);

			System.out.println(endereco);

			new GeradorDeArquivo().salvaJson(endereco);

			System.out.println();
			System.out.println("Deseja continuar? Digite qualquer coisa para continuar e \"Sair\" para deixar o programa");
			op = sc.nextLine();

		}while (!op.equalsIgnoreCase("sair"));
	}
}