package AC2;

class ItemCompra {
    private int quantidade;
    private String nomeProduto;
    private double precoUnitario;
    private double valorTotal;

    public ItemCompra(int quantidade, String nomeProduto, double precoUnitario) {
        this.quantidade = quantidade;
        this.nomeProduto = nomeProduto;
        this.precoUnitario = precoUnitario;
        this.valorTotal = quantidade * precoUnitario;
    }

    @Override
    public String toString() {
        return "Produto: " + nomeProduto + "\nQuantidade: " + quantidade + "\nPreço Unitário: R$" + precoUnitario + "\nValor Total: R$" + valorTotal;
    }
}
