package dao;

import modelo.Animal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {
    private String nomeArquivo = "animal.txt";

    public void criar(Animal novoAnimal) {
        BufferedWriter animal=null;
        try{
            animal = new BufferedWriter(new FileWriter(nomeArquivo, true));
            animal.write(novoAnimal.getId()+ "," + novoAnimal.getNome()+ ","
                    +novoAnimal.getEspecie()+ "," +novoAnimal.getRaca()+","+novoAnimal.getIdade()+","
                    +novoAnimal.getSexo()+","+novoAnimal.getStatus()+","+novoAnimal.getFoto()+","+
                    novoAnimal.getResgateDate()+","+novoAnimal.getResgateLocal()+","+novoAnimal.getAdoteCoracao());
            animal.newLine();
            System.out.println("Animal " +novoAnimal.getNome()+" inserido com sucesso!!");
        }catch (IOException e){
            System.out.println("Erro ao inserir o animal: " +novoAnimal.getNome());
        }finally {
            try {
                if(animal!=null) animal.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }//fim método criar animal

    public List<Animal> listarTodos(){
        List<Animal> animais = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            while ((linha = reader.readLine())!=null){
                String[] dados = linha.split(",");
                if(dados.length == 11){
                    Animal contato = new Animal(Integer.parseInt(dados[0]), dados[1], dados[2],dados[3], Integer.parseInt(dados[4]), dados[5].charAt(0), dados[6], dados[7], dados[8],
                            dados[9], dados[10].charAt(0));
                    animais.add(contato);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não existe");
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo "+nomeArquivo);
        }finally {
            try{
                if(reader != null) reader.close();
            }catch (IOException e){
                System.out.println("Erro ao fechar o arquivo!"+e.getMessage());
            }
        }
        return animais;


    }//fim método listar todos

    public void excluir(String nomeExcluir) {
        List<Animal> animais = listarTodos();
        boolean removido = animais.removeIf(animal -> animal.getNome().equalsIgnoreCase(nomeExcluir));

        if (removido) {
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(this.nomeArquivo));

                for (Animal novoAnimal : animais) {
                    writer.write(novoAnimal.getId() + "," + novoAnimal.getNome() + "," +
                            novoAnimal.getEspecie() + "," + novoAnimal.getRaca() + "," + novoAnimal.getIdade() + "," +
                            novoAnimal.getSexo() + "," + novoAnimal.getStatus() + "," + novoAnimal.getFoto() + "," +
                            novoAnimal.getResgateDate() + "," + novoAnimal.getResgateLocal() + "," + novoAnimal.getAdoteCoracao());
                    writer.newLine();
                }
                System.out.println("Animal removido com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao escrever no arquivo! " + nomeArquivo);
            } finally {
                try {
                    if (writer != null) writer.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar o arquivo! " + e.getMessage());
                }
            }
        } else {
            System.out.println("Contato não existe!");
        }
    }//Fim do método excluir

    public void atualizar(String nomeAtualizar, Animal animalAtualizado) {
        List<Animal> animais = listarTodos(); //carrega lista dos contatos cadastrados
        boolean atualizou = false; //pra poder saber se houve algum contato atualizado

        for (int i = 0; i < animais.size(); i++) { //percorre os contatos //contatos.size já sabe quantos elementos tem na lista
            if (animais.get(i).getNome().equalsIgnoreCase(nomeAtualizar)) {
                animais.set(i, animalAtualizado);
                atualizou = true;
                break;
            }
        }
        if(atualizou){
            BufferedWriter writer = null;
            try{
                writer = new BufferedWriter(new FileWriter(this.nomeArquivo));
                for (Animal novoAnimal : animais){
                    writer.write(novoAnimal.getId()+ "," + novoAnimal.getNome()+ ","
                            +novoAnimal.getEspecie()+ "," +novoAnimal.getRaca()+","+novoAnimal.getIdade()+","
                            +novoAnimal.getSexo()+","+novoAnimal.getStatus()+","+novoAnimal.getFoto()+","+
                            novoAnimal.getResgateDate()+","+novoAnimal.getResgateLocal()+","+novoAnimal.getAdoteCoracao());
                    writer.newLine();
                }
                System.out.println("Animal atulizado com exito!!");
            }catch (IOException e){
                System.out.println("Erro ao atualizar cadastro de animal!"+e.getMessage());
            }finally {
                try{
                    if (writer != null) {
                        writer.close();
                    }
                }catch (IOException e){
                    System.out.println("Erro ao fechar o arquivo!"+e.getMessage());
                }
            }
        }else{
            System.out.println("Animal não encontrado!");
        }
    }//fim do método atualizar


}//final da classe AnimalDAO