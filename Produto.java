class Produto {
    private int codigo;
    private String nome;
    private String descricao;
    private double preco;
    private java.time.LocalDate dataValidade;

    public Produto(int codigo, String nome, String descricao, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public void setDataValidade(java.time.LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public boolean estaVencido() {
        if (dataValidade != null) {
            return java.time.LocalDate.now().isAfter(dataValidade);
        }
        return false;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "Código do Produto: " + codigo + "\nNome: " + nome + "\nDescrição: " + descricao + "\nPreço: R$" + preco;
    }
}
