package AC2;

class Compra {
    private int identificador;
    private java.time.LocalDate data;
    private double valorTotal;
    private String cpfOuCnpjCliente;
    private double totalPago;
    private List<ItemCompra> itens;

    public Compra(int identificador, String cpfOuCnpjCliente) {
        this.identificador = identificador;
        this.data = java.time.LocalDate.now();
        this.cpfOuCnpjCliente = cpfOuCnpjCliente;
        this.totalPago = 0;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemCompra item) {
        itens.add(item);
        valorTotal += item.valorTotal;
    }

    public double calcularValorRestante() {
        return valorTotal - totalPago;
    }

    @Override
    public String toString() {
        return "Identificador da Compra: " + identificador + "\nData: " + data + "\nValor Total: R$" + valorTotal + "\nCPF/CNPJ do Cliente: " + cpfOuCnpjCliente + "\nTotal Pago: R$" + totalPago;
    }
}

