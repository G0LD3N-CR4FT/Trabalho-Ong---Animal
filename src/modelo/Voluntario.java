package modelo;

public class Voluntario extends Pessoa{

    //ATRIBUTOS
    public int idVoluntario;
    public String disponibilidade;
    public String habilidades;


    //CONSTRUTOR SEM PARÂMETRO
    public Voluntario(){

    }


    //CONSTRUTOR COM PARÂMETRO
    public Voluntario(String nome, String telefone, String email, int idVoluntario, String disponibilidade, String habilidades){
        super(nome,telefone,email);
        this.idVoluntario = idVoluntario;
        this.disponibilidade = disponibilidade;
        this.habilidades = habilidades;
    }


    //GETTERS E SETTERS
    public int getIdVoluntario() {
        return idVoluntario;
    }

    public void setIdVoluntario(int idVoluntario) {
        this.idVoluntario = idVoluntario;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    @Override
    public String toString(){
        return String.format("""
            ______________________________________________________________
            Id Voluntário     | %d
            Nome              | %s
            Telefone          | %s
            E-mail            | %s
            Disponibilidade   | %s
            Habilidades       | %s
            _______________________________________________________________
            """, idVoluntario, super.nome, super.telefone, super.email, disponibilidade, habilidades);
    }
}//fim da classe Voluntario.
