package modelo;

public class Pessoa {

    protected String nome;
    protected String telefone;
    protected String email;

    public Pessoa() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Pessoa(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("""
            ________________________________________________
            Nome     | %-25s               \s
            Telefone | %-15s     \s
            Email    | %-30s
            ________________________________________________
           \s""", nome, telefone, email);
    }

}
