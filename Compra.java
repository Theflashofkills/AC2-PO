import java.util.ArrayList;
import java.util.List;

public class Compra {
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
        this.valorTotal = 0; // Inicialize o valor total como zero
    }

    public void adicionarItem(ItemCompra item) {
        itens.add(item);
        valorTotal += item.getValorTotal();
    }

    public double calcularValorRestante() {
        return valorTotal - totalPago;
    }

    public void atualizarTotalPago(double valorPago) {
        totalPago += valorPago;
    }

    @Override
    public String toString() {
        return "Identificador da Compra: " + identificador + "\nData: " + data + "\nValor Total: R$" + valorTotal + "\nCPF/CNPJ do Cliente: " + cpfOuCnpjCliente + "\nTotal Pago: R$" + totalPago;
    }
}

