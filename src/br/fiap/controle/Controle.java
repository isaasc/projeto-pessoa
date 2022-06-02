package br.fiap.controle;

import static javax.swing.JOptionPane.*;
import java.util.ArrayList;
import java.util.List;

import br.fiap.cliente.Cliente;
import br.fiap.empregado.Gerente;
import br.fiap.empregado.Vendedor;
import br.fiap.pessoa.Pessoa;

import static java.lang.Integer.*;
import static java.lang.Double.*;

public class Controle {

	private List<Pessoa> lista = new ArrayList<Pessoa>();

	public void menu() {
		int opcao = 0;
		String aux = "Escolha uma opção\n";
		aux += "1. Cadastrar Empregado\n";
		aux += "2. Cadastrar Cliente\n";
		aux += "3. Pesquisar\n";
		aux += "4. Listar Empregados\n";
		aux += "5. Listar Clientes\n";
		aux += "6. Remover\n";
		aux += "7. Finalizar";

		do {
			try {
				opcao = parseInt(showInputDialog(aux));
				if (opcao < 1 || opcao > 7) {
					showMessageDialog(null, "Opção inválida");
				} else {
					switch (opcao) {
					case 1:
						cadastrarEmpregado();
						break;
					case 2:
						cadastrarCliente();
						break;
					case 3:
						pesquisar();
						break;
					case 4:
						listarEmpregado();
						break;
					case 5:
						listarCliente();
						break;
					case 6:
						remover();
						break;
					}
				}
			} catch (NumberFormatException e) {
				showMessageDialog(null, "A opção deve ser um número..........");
			}
		} while (opcao != 7);

	}

	private void cadastrarEmpregado() {
		String nome, cpf, matricula; // empregado
		double totalDasVendas, comissao; // vendedor
		double salario, bonus; // gerente

		int opcao = 0;

		String aux = "Escolha uma opção\n";
		aux += "1. Cadastrar Vendedor\n";
		aux += "2. Cadastrar Gerente\n";
		aux += "3. Voltar";
		opcao = parseInt(showInputDialog(aux));
		do {
			try {
				opcao = parseInt(showInputDialog(aux));
				if (opcao < 1 || opcao > 3) {
					showMessageDialog(null, "Opção inválida");
				} else {
					if (opcao == 1 || opcao == 2) {
						nome = showInputDialog("Nome");
						cpf = showInputDialog("CPF");
						matricula = showInputDialog("Matrícula");
						if (opcao == 1) {
							totalDasVendas = parseDouble(showInputDialog("Total das Vendas"));
							comissao = parseDouble(showInputDialog("Comissão"));
							lista.add(new Vendedor(nome, cpf, matricula, totalDasVendas, comissao));
						} else {
							salario = parseDouble(showInputDialog("Salário"));
							bonus = parseDouble(showInputDialog("Bônus"));
							lista.add(new Gerente(nome, cpf, matricula, salario, bonus));
						}
					}
				}
			} catch (NumberFormatException e) {
				showMessageDialog(null, "A opção deve ser um número..........");
			}

		} while (opcao != 3);
	}

	private void cadastrarCliente() {
		String nome, cpf;
		double valorDaDivida;
		nome = showInputDialog("Nome");
		cpf = showInputDialog("CPF");
		valorDaDivida = parseDouble(showInputDialog("Valor da Dívida"));
		lista.add(new Cliente(nome, cpf, valorDaDivida));
	}

	private void listarEmpregado() {
		String vendedor = "";
		String gerente = "";

		// tipo de variavel do que eu busco e o nome do lugar que as variaveis estão.
		for (Pessoa p : lista) {
			if (p instanceof Vendedor) {
				vendedor += p + "\n";
			} else if (p instanceof Gerente) {
				gerente += p + "\n";
			}
		}
		showMessageDialog(null, vendedor + "\n" + gerente);
	}

	private void listarCliente() {
		String cliente = "";

		for (Pessoa p : lista) {
			if (p instanceof Cliente) {
				cliente += p + "\n";
			}
		}
		showMessageDialog(null, cliente);
	}

	private int pesquisarAux() {
		String cpf = showInputDialog("CPF para pesquisa");
		int indice = -1;
		for (int i = 0; i < lista.size(); i++) {
			// lista.get(i) é um objeto então é preciso colocar o .getCpf para acessar o que
			// se quer desse objeto.
			if (lista.get(i).getCpf().equals(cpf)) {
				indice = i;
			}
		}
		if (indice == -1) {
			showMessageDialog(null, cpf + " não encontrado");
		}
		return indice;
	}

	private void pesquisar() {
		int indice = pesquisarAux();
		if (indice != -1) {
			showMessageDialog(null, lista.get(indice));
		}
	}

	private void remover() {
		int indice = pesquisarAux();
		if (indice != -1) {
			showMessageDialog(null, lista.remove(indice));
		}
	}
}
