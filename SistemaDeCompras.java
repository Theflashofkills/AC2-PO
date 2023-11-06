import java.time.LocalDate;
import java.time.Period;
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
            scanner.nextLine();
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
                scanner.nextLine();
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
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("\nAtualizar Pagamento de Compra:");
        System.out.println("Opções:");
        System.out.println("1 - Procurar compra por CPF");
        System.out.println("2 - Procurar por Identificador da Compra");
        System.out.println("3 - Sair");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();
    
        switch (opcao) {
            case 1:
                System.out.print("Digite o CPF do cliente: ");
                String cpf = scanner.nextLine();
    
                List<Compra> comprasCliente = new ArrayList<>();
    
                // Encontrar todas as compras do cliente com o CPF fornecido
                for (Compra compra : compras) {
                    if (compra.getCpfOuCnpjCliente().equals(cpf)) {
                        comprasCliente.add(compra);
                    }
                }
    
                if (comprasCliente.isEmpty()) {
                    System.out.println("Nenhuma compra encontrada para este CPF.");
                    break;
                }
    
                System.out.println("Compras do cliente com CPF " + cpf + ":");
                for (Compra compra : comprasCliente) {
                    System.out.println(compra);
                }
    
                System.out.print("Escolha o número da compra que deseja atualizar (ou 0 para cancelar): ");
                int escolhaCompra = scanner.nextInt();
                scanner.nextLine();
    
                if (escolhaCompra == 0 || escolhaCompra > comprasCliente.size()) {
                    System.out.println("Operação cancelada. Voltando ao menu principal.");
                    break;
                }
    
                Compra compraEscolhida = comprasCliente.get(escolhaCompra - 1);
    
                System.out.print("Digite o novo total pago pelo cliente: R$");
                double novoTotalPago = scanner.nextDouble();
                scanner.nextLine();
    
                double totalRestante = compraEscolhida.calcularValorRestante();
                
                if (novoTotalPago > totalRestante) {
                    System.out.println("Novo total excederá o valor total da compra. Insira novamente.");
                } else {
                    compraEscolhida.atualizarTotalPago(novoTotalPago);
                    System.out.println("Total pago atualizado com sucesso.");
                }
    
                break;
    
            case 2:
                System.out.print("Digite o Identificador da Compra: ");
                int identificadorCompra = scanner.nextInt();
                scanner.nextLine();
    
                Compra compraEncontrada = null;
    
                for (Compra compra : compras) {
                    if (compra.getIdentificador() == identificadorCompra) {
                        compraEncontrada = compra;
                        break;
                    }
                }
    
                if (compraEncontrada == null) {
                    System.out.println("Compra com Identificador " + identificadorCompra + " não encontrada.");
                    break;
                }
    
                System.out.println("Compra encontrada:");
                System.out.println(compraEncontrada);
    
                System.out.print("Digite o novo total pago pelo cliente: R$");
                novoTotalPago = scanner.nextDouble();
                scanner.nextLine();
    
                double totalRestanteCompra = compraEncontrada.calcularValorRestante();
    
                if (novoTotalPago > totalRestanteCompra) {
                    System.out.println("Novo total excederá o valor total da compra. Insira novamente.");
                } else {
                    compraEncontrada.atualizarTotalPago(novoTotalPago);
                    System.out.println("Total pago atualizado com sucesso.");
                }
    
                break;
    
            case 3:
                System.out.println("Operação cancelada. Voltando ao menu principal.");
                break;
    
            default:
                System.out.println("Opção inválida. Tente novamente.");
                break;
        }
    }
    
    







    public void exibirRelatorios() {
    Scanner scanner = new Scanner(System.in);

    int opcao;

    do {
        System.out.println("\nRelatórios:");
        System.out.println("1 - Relação de Clientes por Nome");
        System.out.println("2 - Relação de Todos os Produtos");
        System.out.println("3 - Busca de Produto pelo Nome");
        System.out.println("4 - Relação de Todas as Compras");
        System.out.println("5 - Busca de Compra por Número");
        System.out.println("6 - Compras Não Pagas");
        System.out.println("7 - Últimas 10 Compras Pagas");
        System.out.println("8 - Compra Mais Cara");
        System.out.println("9 - Compra Mais Barata");
        System.out.println("10 - Valor Total de Compras nos Últimos 12 Meses");
        System.out.println("11 - Voltar ao Menu Principal");

        System.out.print("Escolha uma opção: ");
        opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                System.out.print("Digite a sequência de caracteres iniciais do nome: ");
                String sequencia = scanner.nextLine();
                relacaoClientesPorNome(sequencia);
                break;
            case 2:
                relacaoTodosProdutos();
                break;
            case 3:
                System.out.print("Digite o nome do produto: ");
                String nomeProduto = scanner.nextLine();
                buscaProdutoPeloNome(nomeProduto);
                break;
            case 4:
                relacaoTodasCompras();
                break;
            case 5:
                System.out.print("Digite o número da compra: ");
                int numeroCompra = scanner.nextInt();
                buscaCompraPeloNumero(numeroCompra);
                break;
            case 6:
                comprasNaoPagas();
                break;
            case 7:
                ultimas10ComprasPagas();
                break;
            case 8:
                compraMaisCara();
                break;
            case 9:
                compraMaisBarata();
                break;
            case 10:
                valorTotalComprasUltimos12Meses();
                break;
            case 11:
                return;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    } while (opcao != 11);
}

private void relacaoClientesPorNome(String sequencia) {
    List<Cliente> clientesEncontrados = new ArrayList<>();

    for (Cliente cliente : clientes) {
        if (cliente.getNome().toLowerCase().startsWith(sequencia.toLowerCase())) {
            clientesEncontrados.add(cliente);
        }
    }

    if (clientesEncontrados.isEmpty()) {
        System.out.println("Histórico não encontrado.");
    } else {
        System.out.println("Clientes com nome iniciado por \"" + sequencia + "\":");
        for (Cliente cliente : clientesEncontrados) {
            System.out.println(cliente);
        }
    }
}

private void relacaoTodosProdutos() {
    if (produtos.isEmpty()) {
        System.out.println("Histórico não encontrado.");
    } else {
        System.out.println("Todos os Produtos:");
        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }
}

private void buscaProdutoPeloNome(String nomeProduto) {
    boolean encontrado = false;

    for (Produto produto : produtos) {
        if (produto.getNome().equalsIgnoreCase(nomeProduto)) {
            encontrado = true;
            System.out.println("Produto Encontrado:");
            System.out.println(produto);
            break;
        }
    }

    if (!encontrado) {
        System.out.println("Histórico não encontrado.");
    }
}

private void relacaoTodasCompras() {
    if (compras.isEmpty()) {
        System.out.println("Histórico não encontrado.");
    } else {
        System.out.println("Todas as Compras:");
        for (Compra compra : compras) {
            System.out.println(compra);
        }
    }
}

private void buscaCompraPeloNumero(int numeroCompra) {
    boolean encontrado = false;

    for (Compra compra : compras) {
        if (compra.getIdentificador() == numeroCompra) {
            encontrado = true;
            System.out.println("Compra Encontrada:");
            System.out.println(compra);
            break;
        }
    }

    if (!encontrado) {
        System.out.println("Histórico não encontrado.");
    }
}

private void comprasNaoPagas() {
    boolean encontradas = false;

    for (Compra compra : compras) {
        if (compra.calcularValorRestante() > 0) {
            encontradas = true;
            System.out.println("Compra Não Paga:");
            System.out.println(compra);
        }
    }

    if (!encontradas) {
        System.out.println("Histórico não encontrado.");
    }
}

private void ultimas10ComprasPagas() {
    List<Compra> ultimasComprasPagas = new ArrayList<>();

    for (int i = compras.size() - 1; i >= 0; i--) {
        Compra compra = compras.get(i);
        if (compra.getTotalPago() == compra.getValorTotal()) {
            ultimasComprasPagas.add(compra);
        }
        if (ultimasComprasPagas.size() == 10) {
            break;
        }
    }

    if (ultimasComprasPagas.isEmpty()) {
        System.out.println("Histórico não encontrado.");
    } else {
        System.out.println("Últimas 10 Compras Pagas:");
        for (Compra compra : ultimasComprasPagas) {
            System.out.println(compra);
        }
    }
}

private void compraMaisCara() {
    Compra compraMaisCara = null;
    double maiorValor = 0;

    for (Compra compra : compras) {
        if (compra.getValorTotal() > maiorValor) {
            maiorValor = compra.getValorTotal();
            compraMaisCara = compra;
        }
    }

    if (compraMaisCara != null) {
        System.out.println("Compra Mais Cara:");
        System.out.println(compraMaisCara);
    } else {
        System.out.println("Histórico não encontrado.");
    }
}

private void compraMaisBarata() {
    Compra compraMaisBarata = null;
    double menorValor = Double.MAX_VALUE;

    for (Compra compra : compras) {
        if (compra.getValorTotal() < menorValor) {
            menorValor = compra.getValorTotal();
            compraMaisBarata = compra;
        }
    }

    if (compraMaisBarata != null) {
        System.out.println("Compra Mais Barata:");
        System.out.println(compraMaisBarata);
    } else {
        System.out.println("Histórico não encontrado.");
    }
}

private void valorTotalComprasUltimos12Meses() {
    LocalDate dataAtual = LocalDate.now();
    double[] totalComprasPorMes = new double[12];

    for (Compra compra : compras) {
        LocalDate dataCompra = compra.getData();
        Period periodo = Period.between(dataCompra, dataAtual);
        int mesesAtras = periodo.getYears() * 12 + periodo.getMonths();

        if (mesesAtras < 12) {
            totalComprasPorMes[11 - mesesAtras] += compra.getValorTotal();
        }
    }

    for (int i = 0; i < totalComprasPorMes.length; i++) {
        System.out.println("Mês " + (i + 1) + ": R$" + totalComprasPorMes[i]);
    }

    if (compras.isEmpty()) {
        System.out.println("Histórico não encontrado.");
    }
}
}

