class ClientePJ extends Cliente {
    private String cnpj;
    private String razaoSocial;
    private int prazoMaximo;

    public ClientePJ(String nome, Endereco endereco, String cnpj, String razaoSocial, int prazoMaximo) {
        super(nome, endereco);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.prazoMaximo = prazoMaximo;
    }

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String toString() {
        return super.toString() + "\nCNPJ: " + cnpj + "\nRazão Social: " + razaoSocial + "\nPrazo Máximo: " + prazoMaximo + " dias";
    }
}
