public class Contato {
    // Atributos privados que representam as informações de um contato
    private String nome;
    private String telefone;
    private String email;
    private String morada;

    // Construtor que inicializa um objeto Contato com as informações fornecidas
    public Contato(String nome, String telefone, String email, String morada) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.morada = morada;
    }

    // Métodos de acesso para obter e definir o nome do contato
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Métodos de acesso para obter e definir o telefone do contato
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // Métodos de acesso para obter e definir o email do contato
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Métodos de acesso para obter e definir a morada do contato
    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    // Método toString para representar o objeto Contato como uma string formatada
    public String toString() {
        return "Nome: " + nome + ", Telefone: " + telefone + ", Email: " + email + ", Morada: " + morada;
    }
}
