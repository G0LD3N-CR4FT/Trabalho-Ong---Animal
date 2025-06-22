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
                animal.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public List<Animal> listarTodos(){
        List<Animal> animais = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo));
        try {
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
                reader.close();
            }catch (Exception e){
                System.out.println("Erro ao fechar o arquivo!"+e.getMessage());
            }
        }
        return animais;


    }

    public void excluir(String nomeExcluir) {
        List<Animal> animais = listarTodos(); //vai carregar toda a lista de contatos
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.nomeArquivo));
        boolean removido = animais.removeIf(animal->animal.getNome().equalsIgnoreCase(nomeExcluir) ); //retorna verdadeiro se algum contato com esse nome foi removido
        //retorna falso se nenhum contato com aquele nome for encontrado
        if(removido) { //vai verificar se o contato foi removido ou seja true
            try {
                for (Animal novoAnimal : animais) { //vai percorrer o resto dos contato no arquivo
                    writer.write(novoAnimal.getId()+ "," + novoAnimal.getNome()+ ","
                            +novoAnimal.getEspecie()+ "," +novoAnimal.getRaca()+","+novoAnimal.getIdade()+","
                            +novoAnimal.getSexo()+","+novoAnimal.getStatus()+","+novoAnimal.getFoto()+","+
                            novoAnimal.getResgateDate()+","+novoAnimal.getResgateLocal()+","+novoAnimal.getAdoteCoracao());//escreve no arquivo
                    writer.newLine(); //serve pra escrever uma linha de separação dos contatos
                }
                writer.close(); //fecha o arquivo para garantir que vai salvar corretamente
                System.out.println("Animal removido com sucesso!!");
            } catch (IOException e) {
                System.out.println("Erro ao escrever no arquivo!" + nomeArquivo);
            }finally {
                try{
                    writer.close();
                }catch (Exception e){
                    System.out.println("Erro ao fechar o arquivo!"+e.getMessage());
                }
            }
        }else{
            System.out.println("Contato não existe!");
        }

    }//fim do método excluir

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
            try{
                BufferedWriter writer = new BufferedWriter(new FileWriter(this.nomeArquivo));
                for (Animal novoAnimal : animais){
                    writer.write(novoAnimal.getId()+ "," + novoAnimal.getNome()+ ","
                            +novoAnimal.getEspecie()+ "," +novoAnimal.getRaca()+","+novoAnimal.getIdade()+","
                            +novoAnimal.getSexo()+","+novoAnimal.getStatus()+","+novoAnimal.getFoto()+","+
                            novoAnimal.getResgateDate()+","+novoAnimal.getResgateLocal()+","+novoAnimal.getAdoteCoracao());
                    writer.newLine();
                }
                writer.close(); //fecha o arquivo para garantir que vai salvar corretamente
                System.out.println("Animal atulizado com exito!!");
            }catch (IOException e){
                System.out.println("Erro ao atualizar cadastro de animal!"+e.getMessage());
            }finally {
                try{
                    Writer.close();
                }catch (Exception e){
                    System.out.println("Erro ao fechar o arquivo!"+e.getMessage());
                }
            }
        }else{
            System.out.println("Animal não encontrado!");
        }
    }//fim do método atualizar


}//final da classe AnimalDAO
