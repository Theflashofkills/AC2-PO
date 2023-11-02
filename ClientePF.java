class ClientePF extends Cliente {
    private String cpf;
    private int maxParcelas;

    public ClientePF(String nome, Endereco endereco, String cpf, int maxParcelas) {
        super(nome, endereco);
        this.cpf = cpf;
        this.maxParcelas = maxParcelas;
    }

    public String getCpf() {
        return cpf;
    }



    @Override
    public String toString() {
        return super.toString() + "\nCPF: " + cpf + "\nMáximo de Parcelas: " + maxParcelas;
    }

    
}