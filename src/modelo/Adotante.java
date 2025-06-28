package modelo;
import modelo.Pessoa;

public class Adotante extends Pessoa {

    //ATRIBUTOS
    public int idAdotante;
    public String questionario;

    //CONSTRUTOR SEM PARÂMETRO
    public Adotante(){

    }

    //CONSTRUTOR COM PARÂMETRO
    public Adotante(int idPessoa, String nome, String telefone, String email, int idAdotante, String questionario) {
        super(idPessoa, nome, telefone, email);
        this.idAdotante = idAdotante;
        this.questionario = questionario;
    }

    //GETTERS E SETTERS
    public int getIdAdotante() {
        return idAdotante;
    }

    public void setIdAdotante(int idAdotante) {
        this.idAdotante = idAdotante;
    }

    public String getQuestionario() {
        return questionario;
    }

    public void setQuestionario(String questionario) {
        this.questionario = questionario;
    }

    @Override
    public String toString() {
        return String.format("""
            ______________________________________________________________
            Id Pessoa         | %d
            Nome              | %s
            Telefone          | %s
            E-mail            | %s
            Id Adotante       | %d
            Questionario      | %s
            _______________________________________________________________
            """, getIdPessoa(), getNome(), getTelefone(), getEmail(), idAdotante, questionario);
    }

}//fim da classe Adotante
