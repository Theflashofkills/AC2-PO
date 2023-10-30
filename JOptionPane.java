package AC2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JOptionPane {
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

    public void cadastrarCliente() {
        Scanner scanner = new Scanner(System.in);
    
        do {
            System.out.println("\nCadastro de Cliente:");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Endereço - Rua: ");
            String rua = scanner.nextLine();
            System.out.print("Endereço - Número: ");
            int numero = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Endereço - Bairro: ");
            String bairro = scanner.nextLine();
            System.out.print("Endereço - CEP: ");
            String cep = scanner.nextLine();
            System.out.print("Endereço - Cidade: ");
            String cidade = scanner.nextLine();
            System.out.print("Endereço - Estado: ");
            String estado = scanner.nextLine();
    
            System.out.println("Escolha o tipo de cliente:");
            System.out.println("1 - Pessoa Física (PF)");
            System.out.println("2 - Pessoa Jurídica (PJ)");
            System.out.print("Digite o número da opção: ");
            int tipoCliente = scanner.nextInt();
    
            Cliente cliente = null;
    
            switch (tipoCliente) {
                case 1: // Pessoa Física
                    System.out.print("CPF: ");
                    String cpf = scanner.next();
                    System.out.print("Máximo de Parcelas: ");
                    int maxParcelasPF = scanner.nextInt();
    
                    cliente = new ClientePF(nome, new Endereco(rua, numero, bairro, cep, cidade, estado), cpf, maxParcelasPF);
                    break;
                case 2: // Pessoa Jurídica
                    System.out.print("CNPJ: ");
                    String cnpj = scanner.next();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Razão Social: ");
                    String razaoSocial = scanner.nextLine();
                    System.out.print("Prazo Máximo: ");
                    int prazoMaximoPJ = scanner.nextInt();
    
                    cliente = new ClientePJ(nome, new Endereco(rua, numero, bairro, cep, cidade, estado), cnpj, razaoSocial, prazoMaximoPJ);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
    
            if (cliente != null) {
                System.out.println("Dados do Cliente:");
                System.out.println(cliente);
    
                System.out.println("Deseja continuar?");
                System.out.println("1 - Sim");
                System.out.println("2 - Não, desejo editar");
                System.out.println("3 - Não, Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
    
                if (opcao == 1) {
                    clientes.add(cliente);
                    System.out.println("Cliente cadastrado com sucesso!");
                } else if (opcao == 2) {
                    // Continue o loop para inserir novamente os dados
                } else if (opcao == 3) {
                    return; // Volta para o menu principal
                } else {
                    System.out.println("Opção inválida. Retornando ao menu principal.");
                    return; // Volta para o menu principal
                }
            }
        } while (true);
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

