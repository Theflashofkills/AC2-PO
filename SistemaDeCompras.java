import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        int opcao;

        do {
            String menu = "MENU:\n" +
                    "1 - Cadastro de Clientes\n" +
                    "2 - Deletar Cliente pelo CPF ou CNPJ\n" +
                    "3 - Deletar Cliente pelo Nome\n" +
                    "4 - Cadastro de Produtos\n" +
                    "5 - Efetuar uma Compra\n" +
                    "6 - Atualizar Situação de Pagamento de uma Compra\n" +
                    "7 - Relatórios\n" +
                    "8 - Sair";

            String opcaoStr = JOptionPane.showInputDialog(null, menu + "\nEscolha uma opção:");
            opcao = Integer.parseInt(opcaoStr);

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
                    JOptionPane.showMessageDialog(null, "Saindo do sistema. Obrigado!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
            }
        } while (opcao != 8);
    }

    











    public void cadastrarCliente() {
        do {
            System.out.println("\nCadastro de Cliente:");

            String nome = JOptionPane.showInputDialog("Nome:");
            String rua = JOptionPane.showInputDialog("Endereço - Rua:");
            int numero = Integer.parseInt(JOptionPane.showInputDialog("Endereço - Número:"));
            String bairro = JOptionPane.showInputDialog("Endereço - Bairro:");
            String cep = JOptionPane.showInputDialog("Endereço - CEP:");
            String cidade = JOptionPane.showInputDialog("Endereço - Cidade:");
            String estado = JOptionPane.showInputDialog("Endereço - Estado:");

            String[] opcoesCliente = {"Pessoa Física (PF)", "Pessoa Jurídica (PJ)"};
            int tipoCliente = JOptionPane.showOptionDialog(
                    null,
                    "Escolha o tipo de cliente:",
                    "Tipo de Cliente",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoesCliente,
                    opcoesCliente[0]
            ) + 1;

            Cliente cliente = null;

            switch (tipoCliente) {
                case 1: // Pessoa Física
                    String cpf = JOptionPane.showInputDialog("CPF:");
                    int maxParcelasPF = Integer.parseInt(JOptionPane.showInputDialog("Máximo de Parcelas:"));

                    cliente = new ClientePF(nome, new Endereco(rua, numero, bairro, cep, cidade, estado), cpf, maxParcelasPF);
                    break;
                case 2: // Pessoa Jurídica
                    String cnpj = JOptionPane.showInputDialog("CNPJ:");
                    String razaoSocial = JOptionPane.showInputDialog("Razão Social:");
                    int prazoMaximoPJ = Integer.parseInt(JOptionPane.showInputDialog("Prazo Máximo:"));

                    cliente = new ClientePJ(nome, new Endereco(rua, numero, bairro, cep, cidade, estado), cnpj, razaoSocial, prazoMaximoPJ);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
                    continue;
            }

            if (cliente != null) {
                JOptionPane.showMessageDialog(null, "Dados do Cliente:\n" + cliente);

                String[] opcoesContinuar = {"Sim (Cadastrar Cliente e voltar ao menu principal)",
                        "Não, quero editar (Deletar o cliente atual e voltar ao início)",
                        "Não, Sair (Voltar ao menu principal)"};

                int opcao = JOptionPane.showOptionDialog(
                        null,
                        "Deseja continuar?",
                        "Continuar",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcoesContinuar,
                        opcoesContinuar[0]
                ) + 1;

                if (opcao == 1) {
                    clientes.add(cliente);
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");

                    try {
                        salvarClientesEmArquivo(cliente);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Erro ao salvar cliente no arquivo: " + e.getMessage());
                    }

                    return; // Volta ao menu principal
                } else if (opcao == 2) {
                    // Não faz nada para continuar o loop e permitir a edição
                } else if (opcao == 3) {
                    return; // Volta ao menu principal
                } else {
                    JOptionPane.showMessageDialog(null, "Opção inválida. Retornando ao menu principal.");
                    return; // Volta ao menu principal
                }
            }
        } while (true);
    }

    private void salvarClientesEmArquivo(Cliente cliente) throws IOException {
        File arquivo = new File("clientes.txt");

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo, true))) {
            escritor.write(cliente.toSaveString()); // toSaveString é um método que você deve adicionar à sua classe Cliente
            escritor.newLine();
        }
    }

    
    
    








    public void deletarClientePorCpfOuCnpj() {
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há nenhum cliente em nossa base de dados.");
            return;
        }
    
        String cpfOuCnpj = JOptionPane.showInputDialog("Deletar Cliente por CPF ou CNPJ:\nDigite o CPF ou CNPJ do cliente que deseja deletar:");
    
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
            JOptionPane.showMessageDialog(null, "Informações do Cliente a ser deletado:\n" + clienteADeletar);
    
            String[] opcoes = {"Sim", "Não, quero deletar outro cliente", "Não"};
            int opcao = JOptionPane.showOptionDialog(
                    null,
                    "Deseja mesmo deletar este cliente?",
                    "Confirmação",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            ) + 1;
    
            if (opcao == 1) {
                clientes.remove(clienteADeletar);
                JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso.");
            } else if (opcao == 2) {
                // Volta ao início da função para deletar outro cliente
                deletarClientePorCpfOuCnpj();
            } else {
                JOptionPane.showMessageDialog(null, "Operação cancelada. Voltando ao menu principal.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cliente com CPF ou CNPJ não encontrado. Voltando ao menu principal.");
        }
    }
    

   
    





    
    public void deletarClientePorNome() {
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há nenhum cliente em nossa base de dados.");
            return;
        }
    
        String nomeCliente = JOptionPane.showInputDialog("Deletar Cliente por Nome:\nDigite o nome do cliente que deseja deletar:");
    
        List<Cliente> clientesEncontrados = new ArrayList<>();
    
        // Procurar por clientes com o mesmo nome
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(nomeCliente)) {
                clientesEncontrados.add(cliente);
            }
        }
    
        if (clientesEncontrados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
    
            int opcao;
            do {
                String[] opcoes = {"Voltar ao Menu", "Inserir nome novamente"};
                opcao = JOptionPane.showOptionDialog(
                        null,
                        "Opções:",
                        "Opções",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcoes,
                        opcoes[0]
                ) + 1;
    
                if (opcao == 1) {
                    return;
                } else if (opcao != 2) {
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
                }
            } while (opcao != 1 && opcao != 2);
        } else {
            StringBuilder clientesEncontradosMessage = new StringBuilder("Clientes encontrados com o nome \"" + nomeCliente + "\":\n");
            for (int i = 0; i < clientesEncontrados.size(); i++) {
                Cliente cliente = clientesEncontrados.get(i);
                clientesEncontradosMessage.append(i + 1).append(" - ").append(cliente).append("\n");
            }
    
            String[] opcoes = new String[clientesEncontrados.size() + 1];
            for (int i = 0; i < clientesEncontrados.size(); i++) {
                opcoes[i] = Integer.toString(i + 1);
            }
            opcoes[opcoes.length - 1] = "0";
    
            int escolha = JOptionPane.showOptionDialog(
                    null,
                    clientesEncontradosMessage.toString() + "Escolha o número do cliente que deseja deletar (ou 0 para cancelar):",
                    "Escolha do Cliente",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );
    
            if (escolha >= 0 && escolha < clientesEncontrados.size()) {
                Cliente clienteADeletar = clientesEncontrados.get(escolha);
    
                JOptionPane.showMessageDialog(null, "Informações do Cliente a ser deletado:\n" + clienteADeletar);
    
                String[] opcoesConfirmacao = {"Sim", "Não, quero deletar outro cliente", "Não"};
                int opcao = JOptionPane.showOptionDialog(
                        null,
                        "Deseja mesmo deletar este cliente?",
                        "Confirmação",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcoesConfirmacao,
                        opcoesConfirmacao[0]
                ) + 1;
    
                if (opcao == 1) {
                    clientes.remove(clienteADeletar);
                    JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso.");
                } else if (opcao == 2) {
                    deletarClientePorNome();
                } else {
                    JOptionPane.showMessageDialog(null, "Operação cancelada. Voltando ao menu principal.");
                }
            } else if (escolha == opcoes.length - 1) {
                JOptionPane.showMessageDialog(null, "Operação cancelada. Voltando ao menu principal.");
            } else {
                JOptionPane.showMessageDialog(null, "Opção inválida. Voltando ao menu principal.");
            }
        }
    }
     
    
    
    
    
    
    





    

    public void cadastrarProduto() {
        System.out.println("\nCadastro de Produto:");
    
        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código do Produto:"));
        String nome = JOptionPane.showInputDialog("Nome:");
        String descricao = JOptionPane.showInputDialog("Descrição:");
        double preco = Double.parseDouble(JOptionPane.showInputDialog("Preço: R$"));
    
        Produto produto = new Produto(codigo, nome, descricao, preco);
    
        produtos.add(produto);
        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
    
        int opcao = JOptionPane.showConfirmDialog(
                null,
                "Deseja ver como ficou o produto cadastrado?",
                "Visualizar Produto",
                JOptionPane.YES_NO_OPTION
        );
    
        if (opcao == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Produto Cadastrado:\n" + produto);
        }
    }










    public void efetuarCompra() {
        if (clientes.isEmpty() || produtos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não é possível efetuar uma compra sem clientes e produtos cadastrados.");
            return;
        }
    
        JOptionPane.showMessageDialog(null, "Efetuar Compra:");
    
        // Selecionar o cliente para a compra
        String cpfOuCnpjCliente = JOptionPane.showInputDialog("Digite o CPF ou CNPJ do cliente:");
        Cliente clienteSelecionado = encontrarClientePorCpfOuCnpj(cpfOuCnpjCliente);
    
        if (clienteSelecionado == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado. Voltando ao menu principal.");
            return;
        }
    
        Compra compra = criarCompra(clienteSelecionado);
    
        boolean continuarCompra = true;
    
        while (continuarCompra) {
            StringBuilder listaProdutos = new StringBuilder("Lista de Produtos Disponíveis:\n");
    
            for (int i = 0; i < produtos.size(); i++) {
                Produto produto = produtos.get(i);
                listaProdutos.append(i + 1).append(" - ").append(produto).append("\n");
            }
    
            String escolhaProdutoStr = JOptionPane.showInputDialog(listaProdutos + "Escolha o número do produto que deseja adicionar à compra (ou 0 para finalizar a compra):");
            int escolhaProduto = Integer.parseInt(escolhaProdutoStr);
    
            if (escolhaProduto == 0) {
                continuarCompra = false;
            } else if (escolhaProduto > 0 && escolhaProduto <= produtos.size()) {
                Produto produtoEscolhido = produtos.get(escolhaProduto - 1);
    
                String quantidadeStr = JOptionPane.showInputDialog("Quantidade desejada:");
                int quantidade = Integer.parseInt(quantidadeStr);
    
                if (quantidade <= 0) {
                    JOptionPane.showMessageDialog(null, "Quantidade inválida. Produto não adicionado à compra.");
                } else {
                    ItemCompra item = new ItemCompra(quantidade, produtoEscolhido.getNome(), produtoEscolhido.getPreco());
                    compra.adicionarItem(item);
                    JOptionPane.showMessageDialog(null, "Produto adicionado à compra.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
            }
        }
    
        JOptionPane.showMessageDialog(null, "Resumo da Compra:\n" + compra);
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
        JOptionPane.showMessageDialog(null, "Atualizar Pagamento de Compra:");
    
        Object[] opcoes = {"Procurar compra por CPF", "Procurar por Identificador da Compra", "Sair"};
        int opcao = JOptionPane.showOptionDialog(
                null,
                "Escolha uma opção:",
                "Opções",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        ) + 1;
    
        switch (opcao) {
            case 1:
                String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente:");
    
                List<Compra> comprasCliente = new ArrayList<>();
    
                // Encontrar todas as compras do cliente com o CPF fornecido
                for (Compra compra : compras) {
                    if (compra.getCpfOuCnpjCliente().equals(cpf)) {
                        comprasCliente.add(compra);
                    }
                }
    
                if (comprasCliente.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhuma compra encontrada para este CPF.");
                    break;
                }
    
                StringBuilder comprasClienteMessage = new StringBuilder("Compras do cliente com CPF " + cpf + ":\n");
                for (Compra compra : comprasCliente) {
                    comprasClienteMessage.append(compra).append("\n");
                }
    
                String escolhaCompraStr = JOptionPane.showInputDialog(comprasClienteMessage + "Escolha o número da compra que deseja atualizar (ou 0 para cancelar):");
                int escolhaCompra = Integer.parseInt(escolhaCompraStr);
    
                if (escolhaCompra == 0 || escolhaCompra > comprasCliente.size()) {
                    JOptionPane.showMessageDialog(null, "Operação cancelada. Voltando ao menu principal.");
                    break;
                }
    
                Compra compraEscolhida = comprasCliente.get(escolhaCompra - 1);
    
                String novoTotalPagoStr = JOptionPane.showInputDialog("Digite o novo total pago pelo cliente: R$");
                double novoTotalPago = Double.parseDouble(novoTotalPagoStr);
    
                double totalRestante = compraEscolhida.calcularValorRestante();
    
                if (novoTotalPago > totalRestante) {
                    JOptionPane.showMessageDialog(null, "Novo total excederá o valor total da compra. Insira novamente.");
                } else {
                    compraEscolhida.atualizarTotalPago(novoTotalPago);
                    JOptionPane.showMessageDialog(null, "Total pago atualizado com sucesso.");
                }
    
                break;
    
            case 2:
                String identificadorCompraStr = JOptionPane.showInputDialog("Digite o Identificador da Compra:");
                int identificadorCompra = Integer.parseInt(identificadorCompraStr);
    
                Compra compraEncontrada = null;
    
                for (Compra compra : compras) {
                    if (compra.getIdentificador() == identificadorCompra) {
                        compraEncontrada = compra;
                        break;
                    }
                }
    
                if (compraEncontrada == null) {
                    JOptionPane.showMessageDialog(null, "Compra com Identificador " + identificadorCompra + " não encontrada.");
                    break;
                }
    
                JOptionPane.showMessageDialog(null, "Compra encontrada:\n" + compraEncontrada);
    
                String novoTotalPagoStr2 = JOptionPane.showInputDialog("Digite o novo total pago pelo cliente: R$");
                double novoTotalPago2 = Double.parseDouble(novoTotalPagoStr2);
    
                double totalRestanteCompra = compraEncontrada.calcularValorRestante();
    
                if (novoTotalPago2 > totalRestanteCompra) {
                    JOptionPane.showMessageDialog(null, "Novo total excederá o valor total da compra. Insira novamente.");
                } else {
                    compraEncontrada.atualizarTotalPago(novoTotalPago2);
                    JOptionPane.showMessageDialog(null, "Total pago atualizado com sucesso.");
                }
    
                break;
    
            case 3:
                JOptionPane.showMessageDialog(null, "Operação cancelada. Voltando ao menu principal.");
                break;
    
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
                break;
        }
    }
    
    







    public void exibirRelatorios() {
        int opcao;
    
        do {
            String opcoes = "Relatórios:\n" +
                    "1 - Relação de Clientes por Nome\n" +
                    "2 - Relação de Todos os Produtos\n" +
                    "3 - Busca de Produto pelo Nome\n" +
                    "4 - Relação de Todas as Compras\n" +
                    "5 - Busca de Compra por Número\n" +
                    "6 - Compras Não Pagas\n" +
                    "7 - Últimas 10 Compras Pagas\n" +
                    "8 - Compra Mais Cara\n" +
                    "9 - Compra Mais Barata\n" +
                    "10 - Valor Total de Compras nos Últimos 12 Meses\n" +
                    "11 - Voltar ao Menu Principal";
    
            opcao = Integer.parseInt(JOptionPane.showInputDialog(opcoes));
    
            switch (opcao) {
                case 1:
                    String sequencia = JOptionPane.showInputDialog("Digite a sequência de caracteres iniciais do nome:");
                    relacaoClientesPorNome(sequencia);
                    break;
                case 2:
                    relacaoTodosProdutos();
                    break;
                case 3:
                    String nomeProduto = JOptionPane.showInputDialog("Digite o nome do produto:");
                    buscaProdutoPeloNome(nomeProduto);
                    break;
                case 4:
                    relacaoTodasCompras();
                    break;
                case 5:
                    int numeroCompra = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da compra:"));
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
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
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
            JOptionPane.showMessageDialog(null, "Histórico não encontrado.");
        } else {
            StringBuilder mensagem = new StringBuilder("Clientes com nome iniciado por \"" + sequencia + "\":\n");
            for (Cliente cliente : clientesEncontrados) {
                mensagem.append(cliente).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensagem.toString());
        }
    }
    
    private void relacaoTodosProdutos() {
        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Histórico não encontrado.");
        } else {
            StringBuilder mensagem = new StringBuilder("Todos os Produtos:\n");
            for (Produto produto : produtos) {
                mensagem.append(produto).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensagem.toString());
        }
    }
    
    private void buscaProdutoPeloNome(String nomeProduto) {
        boolean encontrado = false;
    
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nomeProduto)) {
                encontrado = true;
                JOptionPane.showMessageDialog(null, "Produto Encontrado:\n" + produto);
                break;
            }
        }
    
        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "Histórico não encontrado.");
        }
    }
    
    private void relacaoTodasCompras() {
        if (compras.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Histórico não encontrado.");
        } else {
            StringBuilder mensagem = new StringBuilder("Todas as Compras:\n");
            for (Compra compra : compras) {
                mensagem.append(compra).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensagem.toString());
        }
    }
    
    private void buscaCompraPeloNumero(int numeroCompra) {
        boolean encontrado = false;
    
        for (Compra compra : compras) {
            if (compra.getIdentificador() == numeroCompra) {
                encontrado = true;
                JOptionPane.showMessageDialog(null, "Compra Encontrada:\n" + compra);
                break;
            }
        }
    
        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "Histórico não encontrado.");
        }
    }
    
    private void comprasNaoPagas() {
        boolean encontradas = false;
    
        for (Compra compra : compras) {
            if (compra.calcularValorRestante() > 0) {
                encontradas = true;
                JOptionPane.showMessageDialog(null, "Compra Não Paga:\n" + compra);
            }
        }
    
        if (!encontradas) {
            JOptionPane.showMessageDialog(null, "Histórico não encontrado.");
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
            JOptionPane.showMessageDialog(null, "Histórico não encontrado.");
        } else {
            StringBuilder mensagem = new StringBuilder("Últimas 10 Compras Pagas:\n");
            for (Compra compra : ultimasComprasPagas) {
                mensagem.append(compra).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensagem.toString());
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
            JOptionPane.showMessageDialog(null, "Compra Mais Cara:\n" + compraMaisCara);
        } else {
            JOptionPane.showMessageDialog(null, "Histórico não encontrado.");
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
            JOptionPane.showMessageDialog(null, "Compra Mais Barata:\n" + compraMaisBarata);
        } else {
            JOptionPane.showMessageDialog(null, "Histórico não encontrado.");
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
    
        StringBuilder mensagem = new StringBuilder();
    
        for (int i = 0; i < totalComprasPorMes.length; i++) {
            mensagem.append("Mês ").append(i + 1).append(": R$").append(totalComprasPorMes[i]).append("\n");
        }
    
        if (compras.isEmpty()) {
            mensagem.append("Histórico não encontrado.");
        }
    
        JOptionPane.showMessageDialog(null, mensagem.toString());
    }


}


