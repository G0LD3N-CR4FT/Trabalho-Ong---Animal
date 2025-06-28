package modelo;

public class Animal {
    private int id;
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private char sexo; // 'M' ou 'F'
    private String status;
    private String foto;
    private String resgateDate;
    private String resgateLocal;
    private char adoteCoracao; // 'S' ou 'N'

    public Animal() {
    }

    public Animal(int id, String nome, String especie, String raca, int idade, char sexo,
                  String status, String foto, String resgateDate, String resgateLocal, char adoteCoracao) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.sexo = sexo;
        this.status = status;
        this.foto = foto;
        this.resgateDate = resgateDate;
        this.resgateLocal = resgateLocal;
        this.adoteCoracao = adoteCoracao;
    }

    // Getters e setters simples
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public char getSexo() { return sexo; }
    public void setSexo(char sexo) { this.sexo = sexo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public String getResgateDate() { return resgateDate; }
    public void setResgateDate(String resgateDate) { this.resgateDate = resgateDate; }

    public String getResgateLocal() { return resgateLocal; }
    public void setResgateLocal(String resgateLocal) { this.resgateLocal = resgateLocal; }

    public char getAdoteCoracao() { return adoteCoracao; }
    public void setAdoteCoracao(char adoteCoracao) {
        if(adoteCoracao == 'S' || adoteCoracao == 's') {
            this.adoteCoracao = 'S';
        } else {
            this.adoteCoracao = 'N';
        }
    }

    // Método simples para definir adoteCoracao usando if/else e String
    public void definirAdoteCoracao(String resposta) {
        if (resposta.equalsIgnoreCase("s")) {
            this.adoteCoracao = 'S';
        } else {
            this.adoteCoracao = 'N';
        }
    }

    @Override
    public String toString() {
        String adocao = adoteCoracao == 'S' ? "Sim" : "Nao";
        return String.format("""
            ______________________________________________________________
            ID                | %d
            Nome              | %s
            Espécie           | %s
            Raça              | %s
            Idade             | %d
            Sexo              | %c
            Status            | %s
            Foto              | %s
            Data de Resgate   | %s
            Local Resgate     | %s
            Adote com coração | %s
            ______________________________________________________________
            """, id, nome, especie, raca, idade, sexo, status, foto, resgateDate, resgateLocal, adocao);
    }
}