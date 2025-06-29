package modelo;

public class HistoricoMedico {
    private int idHistorico;
    private int idAnimal;
    private String data;
    private String tipo; //vacina, cirurgia, castração.. etc
    private String descricao;

    public HistoricoMedico(){

    } //construtor sem parâmetro

    public HistoricoMedico (int idHistorico, int idAnimal, String data, String tipo, String descricao){
        this.idHistorico = idHistorico;
        this.idAnimal = idAnimal;
        this.data = data;
        this.tipo = tipo;
        this.descricao = descricao;
    } //construtor com parâmetro

    //getters e setters
    public int getIdHistorico(){
        return idHistorico;
    }
    public void setIdHistorico(){
        this.idHistorico = idHistorico;
    }

    public int getIdAnimal(){
        return  idAnimal;
    }
    public void setIdAnimal(){
        this.idAnimal = idAnimal;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return String.format("""
            ______________________________________________________________
            ID Histórico      | %d
            ID Animal         | %d
            Data              | %s
            Tipo              | %s
            Descrição         | %s
            ______________________________________________________________
            """, idHistorico, idAnimal, data, tipo, descricao);
    }

}//fim da classe HistoricoMedico