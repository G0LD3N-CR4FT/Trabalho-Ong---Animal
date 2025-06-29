package modelo;

import java.util.List;

public class Adocao {

        private int idAdocao;
        private String dataAdocao;
        private List<Animal> listaAnimal;
        private Adotante adotante;
        private boolean aprovado;

        public Adocao(){

        } //construtor sem parâmetro

        public Adocao (int idAdocao, String dataAdocao, List<Animal> listaAnimal, Adotante adotante, boolean aprovado){
            this.idAdocao = idAdocao;
            this.dataAdocao = dataAdocao;
            this.listaAnimal = listaAnimal;
            this.adotante = adotante;
            this.aprovado = aprovado;
        } //construtor com parâmetro

        //getters e setters

       public int getIdAdocao() {
           return idAdocao;
       }

       public void setIdAdocao(int idAdocao) {
           this.idAdocao = idAdocao;
       }

       public String getDataAdocao() {
           return dataAdocao;
       }

       public void setDataAdocao(String dataAdocao) {
           this.dataAdocao = dataAdocao;
       }

       public List<Animal> getListaAnimal() {
           return listaAnimal;
       }

       public void setListaAnimal(List<Animal> listaAnimal) {
           this.listaAnimal = listaAnimal;
       }

       public Adotante getAdotante() {
           return adotante;
       }

       public void setAdotante(Adotante adotante) {
           this.adotante = adotante;
       }

       public boolean isAprovado() {
           return aprovado;
       }

       public void setAprovado(boolean aprovado) {
           this.aprovado = aprovado;
       }

    @Override
    public String toString() {

        String idsAnimais = listaAnimal.stream()
                .map(animal -> String.valueOf(animal.getId()))
                .collect(java.util.stream.Collectors.joining(";"));

        return String.format("""
            ______________________________________________________________
            ID                | %d
            ID Adotante       | %d
            IDs Animais       | %s
            Data de Adoção    | %s
            Adoção aprovada   | %s
            ______________________________________________________________
            """, idAdocao, getAdotante().getIdAdotante(), idsAnimais, dataAdocao, aprovado);
    }

    public void listarAnimal(){
         for (Animal animal : listaAnimal) {
             System.out.printf("Lista de animais | %s \n", animal.getNome());
         }
    }

}
