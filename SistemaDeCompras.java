package AC2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaDeCompras {
    private List<Cliente> clientes;
    private List<Produto> produtos;
    private List<Compra> compras;

    public SistemaDeCompras() {
        clientes = new ArrayList<>();
        produtos = new ArrayList<>();
        compras = new ArrayList<>();
    }

    public static void main(String[] args) {
        SistemaDeCompras sistema = new SistemaDeCompras();
        sistema.exibirMenu();
    }

    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMENU:");
            System.out.println("1 - Cadastro de Clientes");
            System.out.println("2 - Deletar Cliente pelo CPF ou CNPJ");
            System.out.println("3 - Deletar Cliente pelo Nome");
            System.out.println("4 - Cadastro de Produtos");
            System.out.println("5 - Efetuar uma Compra");
            System.out.println("6 - Atualizar Situação de Pagamento de uma Compra");
            System.out.println("7 - Relatórios");
            System.out.println("8 - Sair");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    deletarClientePorCpfOuCnpj();
                    break;
                case 3:
                    deletarClientePorNome();
                    break;
                case 4:
                    cadastrarProduto();
                    break;
                case 5:
                    efetuarCompra();
                    break;
                case 6:
                    atualizarPagamentoCompra();
                    break;
                case 7:
                    exibirRelatorios();
                    break;
                case 8:
                    System.out.println("Saindo do sistema. Obrigado!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 8);
    }

    // Implemente as funcionalidades detalhadas (cadastrarCliente, deletarClientePorCpfOuCnpj, etc.) aqui.

    public void cadastrarCliente() {
        // Implemente a lógica para cadastrar um cliente.
    }

    public void deletarClientePorCpfOuCnpj() {
        // Implemente a lógica para deletar um cliente por CPF ou CNPJ.
    }

    public void deletarClientePorNome() {
        // Implemente a lógica para deletar um cliente por nome.
    }

    public void cadastrarProduto() {
            Scanner scanner = new Scanner(System.in);
        
            System.out.println("\nCadastro de Produto:");
            System.out.print("Código do Produto: ");
            int codigo = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();
            System.out.print("Preço: R$");
            double preco = scanner.nextDouble();
        
            Produto produto = new Produto(codigo, nome, descricao, preco);
        
            // Opcionalmente, você pode definir a data de validade aqui.
            // Por exemplo:
            // System.out.print("Data de Validade (YYYY-MM-DD): ");
            // String dataValidadeStr = scanner.next();
            // LocalDate dataValidade = LocalDate.parse(dataValidadeStr);
            // produto.setDataValidade(dataValidade);
        
            produtos.add(produto);
            System.out.println("Produto cadastrado com sucesso!");
        
            System.out.print("Deseja ver como ficou o produto cadastrado? (S/N): ");
            String verProduto = scanner.next();
            
            if (verProduto.equalsIgnoreCase("S")) {
                System.out.println("Produto Cadastrado:");
                System.out.println(produto);
            }
    }

    public void efetuarCompra() {
        // Implemente a lógica para efetuar uma compra.
    }

    public void atualizarPagamentoCompra() {
        // Implemente a lógica para atualizar o pagamento de uma compra.
    }

    public void exibirRelatorios() {
        // Implemente a lógica para exibir os relatórios solicitados.
    }
}

