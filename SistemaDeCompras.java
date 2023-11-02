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
                    scanner.nextLine();
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
                System.out.println("1 - Sim (Cadastrar Cliente e voltar ao menu principal)");
                System.out.println("2 - Não, quero editar (Deletar o cliente atual e voltar ao início)");
                System.out.println("3 - Não, Sair (Voltar ao menu principal)");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
    
                if (opcao == 1) {
                    clientes.add(cliente);
                    System.out.println("Cliente cadastrado com sucesso!");
                    return; // Volta ao menu principal
                } else if (opcao == 2) {
                    // Não faz nada para continuar o loop e permitir a edição
                } else if (opcao == 3) {
                    return; // Volta ao menu principal
                } else {
                    System.out.println("Opção inválida. Retornando ao menu principal.");
                    return; // Volta ao menu principal
                }
            }
        } while (true);
    }
    
    
    

    public void deletarClientePorCpfOuCnpj() {
        Scanner scanner = new Scanner(System.in);
    
        if (clientes.isEmpty()) {
            System.out.println("Não há nenhum cliente em nossa base de dados.");
            return;
        }
    
        System.out.println("\nDeletar Cliente por CPF ou CNPJ:");
        System.out.print("Digite o CPF ou CNPJ do cliente que deseja deletar: ");
        String cpfOuCnpj = scanner.next();
        scanner.nextLine();
    
        Cliente clienteADeletar = null;
    
        for (Cliente cliente : clientes) {
            if (cliente instanceof ClientePF) {
                ClientePF clientePF = (ClientePF) cliente;
                if (clientePF.getCpf().equals(cpfOuCnpj)) {
                    clienteADeletar = cliente;
                    break;
                }
            } else if (cliente instanceof ClientePJ) {
                ClientePJ clientePJ = (ClientePJ) cliente;
                if (clientePJ.getCnpj().equals(cpfOuCnpj)) {
                    clienteADeletar = cliente;
                    break;
                }
            }
        }
    
        if (clienteADeletar != null) {
            System.out.println("Informações do Cliente a ser deletado:");
            System.out.println(clienteADeletar);
    
            System.out.println("Deseja mesmo deletar este cliente?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não, quero deletar outro cliente");
            System.out.println("3 - Não");
    
            int opcao = scanner.nextInt();
    
            if (opcao == 1) {
                clientes.remove(clienteADeletar);
                System.out.println("Cliente deletado com sucesso.");
            } else if (opcao == 2) {
                // Volta ao início da função para deletar outro cliente
                deletarClientePorCpfOuCnpj();
            } else {
                System.out.println("Operação cancelada. Voltando ao menu principal.");
            }
        } else {
            System.out.println("Cliente com CPF ou CNPJ não encontrado. Voltando ao menu principal.");
        }
    }
    

   
    
    
    public void deletarClientePorNome() {
        Scanner scanner = new Scanner(System.in);
    
        if (clientes.isEmpty()) {
            System.out.println("Não há nenhum cliente em nossa base de dados.");
            return;
        }
    
        System.out.println("\nDeletar Cliente por Nome:");
        System.out.print("Digite o nome do cliente que deseja deletar: ");
        String nomeCliente = scanner.nextLine();
    
        List<Cliente> clientesEncontrados = new ArrayList<>();
    
        // Procurar por clientes com o mesmo nome
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(nomeCliente)) {
                clientesEncontrados.add(cliente);
            }
        }
    
        if (clientesEncontrados.isEmpty()) {
            System.out.println("Cliente não encontrado.");
    
            int opcao;
            do {
                System.out.println("Opções:");
                System.out.println("1 - Voltar ao Menu");
                System.out.println("2 - Inserir nome novamente");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (opcao == 1) {
                    return;
                } else if (opcao != 2) {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } while (opcao != 1 && opcao != 2);
        } else {
            System.out.println("Clientes encontrados com o nome \"" + nomeCliente + "\":");
            for (int i = 0; i < clientesEncontrados.size(); i++) {
                Cliente cliente = clientesEncontrados.get(i);
                System.out.println(i + 1 + " - " + cliente);
            }
    
            System.out.print("Escolha o número do cliente que deseja deletar (ou 0 para cancelar): ");
            int escolha = scanner.nextInt();
            scanner.nextLine();
    
            if (escolha > 0 && escolha <= clientesEncontrados.size()) {
                Cliente clienteADeletar = clientesEncontrados.get(escolha - 1);
    
                System.out.println("Informações do Cliente a ser deletado:");
                System.out.println(clienteADeletar);
    
                System.out.println("Deseja mesmo deletar este cliente?");
                System.out.println("1 - Sim");
                System.out.println("2 - Não, quero deletar outro cliente");
                System.out.println("3 - Não");
    
                int opcao = scanner.nextInt();
    
                if (opcao == 1) {
                    clientes.remove(clienteADeletar);
                    System.out.println("Cliente deletado com sucesso.");
                } else if (opcao == 2) {
                    deletarClientePorNome();
                } else {
                    System.out.println("Operação cancelada. Voltando ao menu principal.");
                }
            } else if (escolha == 0) {
                System.out.println("Operação cancelada. Voltando ao menu principal.");
            } else {
                System.out.println("Opção inválida. Voltando ao menu principal.");
            }
        }
    }
     
    
    
    
    
    
    





    

    public void cadastrarProduto() {
            Scanner scanner = new Scanner(System.in);
        
            System.out.println("\nCadastro de Produto:");
            System.out.print("Código do Produto: ");
            int codigo = scanner.nextInt();
            scanner.nextLine();
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
        Scanner scanner = new Scanner(System.in);
    
        if (clientes.isEmpty() || produtos.isEmpty()) {
            System.out.println("Não é possível efetuar uma compra sem clientes e produtos cadastrados.");
            return;
        }
    
        System.out.println("\nEfetuar Compra:");
    
        // Selecionar o cliente para a compra
        System.out.print("Digite o CPF ou CNPJ do cliente: ");
        String cpfOuCnpjCliente = scanner.next();
        scanner.nextLine();
    
        Cliente clienteSelecionado = encontrarClientePorCpfOuCnpj(cpfOuCnpjCliente);
    
        if (clienteSelecionado == null) {
            System.out.println("Cliente não encontrado. Voltando ao menu principal.");
            return;
        }
    
        Compra compra = criarCompra(clienteSelecionado);
    
        boolean continuarCompra = true;
    
        while (continuarCompra) {
            System.out.println("Lista de Produtos Disponíveis:");
    
            for (int i = 0; i < produtos.size(); i++) {
                Produto produto = produtos.get(i);
                System.out.println(i + 1 + " - " + produto);
            }
    
            System.out.print("Escolha o número do produto que deseja adicionar à compra (ou 0 para finalizar a compra): ");
            int escolhaProduto = scanner.nextInt();
    
            if (escolhaProduto == 0) {
                continuarCompra = false;
            } else if (escolhaProduto > 0 && escolhaProduto <= produtos.size()) {
                Produto produtoEscolhido = produtos.get(escolhaProduto - 1);
    
                System.out.print("Quantidade desejada: ");
                int quantidade = scanner.nextInt();
    
                if (quantidade <= 0) {
                    System.out.println("Quantidade inválida. Produto não adicionado à compra.");
                } else {
                    ItemCompra item = new ItemCompra(quantidade, produtoEscolhido.getNome(), produtoEscolhido.getPreco());
                    compra.adicionarItem(item);
                    System.out.println("Produto adicionado à compra.");
                }
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    
        System.out.println("Resumo da Compra:");
        System.out.println(compra);
    }
    
    private Cliente encontrarClientePorCpfOuCnpj(String cpfOuCnpj) {
        for (Cliente cliente : clientes) {
            if (cliente instanceof ClientePF && ((ClientePF) cliente).getCpf().equals(cpfOuCnpj)) {
                return cliente;
            } else if (cliente instanceof ClientePJ && ((ClientePJ) cliente).getCnpj().equals(cpfOuCnpj)) {
                return cliente;
            }
        }
        return null;
    }
    private Compra criarCompra(Cliente cliente) {
        int novoIdentificador = compras.size() + 1;
        Compra compra = new Compra(novoIdentificador, cliente instanceof ClientePF ? ((ClientePF) cliente).getCpf() : ((ClientePJ) cliente).getCnpj());
        compras.add(compra);
        return compra;
    }
    




    public void atualizarPagamentoCompra() {
        // Implemente a lógica para atualizar o pagamento de uma compra.
    }

    public void exibirRelatorios() {
        // Implemente a lógica para exibir os relatórios solicitados.
    }
}

