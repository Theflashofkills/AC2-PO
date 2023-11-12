class Cliente {
    private String nome;
    private Endereco endereco;
    private String dataCadastro;

    public Cliente(String nome, Endereco endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataCadastro = java.time.LocalDate.now().toString();

        
    }
    public String getNome() {
            return nome;
        }
    
    public String toSaveString() {
            return nome + ";" + endereco + ";" + dataCadastro;
        }

    
    

    @Override
    public String toString() {
        return "Nome do Cliente: " + nome + "\nEndereço: " + endereco + "\nData de Cadastro: " + dataCadastro;
    }
}
