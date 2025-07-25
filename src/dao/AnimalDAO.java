package dao;

import colors.ConsoleColors;
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
            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\nAnimal " +novoAnimal.getNome()+" inserido com sucesso!!" + ConsoleColors.RESET);
        }catch (IOException e){
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erro ao inserir o animal: " +novoAnimal.getNome() +" "+ ConsoleColors.RESET);
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
                    Animal animal = new Animal(Integer.parseInt(dados[0]), dados[1], dados[2],dados[3], Integer.parseInt(dados[4]), dados[5].charAt(0), dados[6], dados[7], dados[8],
                            dados[9], dados[10].charAt(0));
                    animais.add(animal);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(ConsoleColors.RED_BOLD_BRIGHT + "Arquivo não existe" + ConsoleColors.RESET);
        } catch (IOException e) {
            System.err.println(ConsoleColors.RED_BOLD_BRIGHT + "Erro ao ler o arquivo "+nomeArquivo+ " "+ ConsoleColors.RESET);
        }finally {
            try{
                if(reader != null) reader.close();
            }catch (IOException e){
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erro ao fechar o arquivo!"+e.getMessage() + " " + ConsoleColors.RESET);
            }
        }
        return animais;


    }//fim método listar todos

    public void excluir(int idExcluir) {
        List<Animal> animais = listarTodos();
        boolean removido = animais.removeIf(animal -> animal.getId() == (idExcluir));

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
                System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\nAnimal removido com sucesso!" + ConsoleColors.RESET);
            } catch (IOException e) {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erro ao escrever no arquivo! " + nomeArquivo + " " + ConsoleColors.RESET);
            } finally {
                try {
                    if (writer != null) writer.close();
                } catch (IOException e) {
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erro ao fechar o arquivo! " + e.getMessage() + " " + ConsoleColors.RESET);
                }
            }
        } else {
            System.out.println("Animal não existe!");
        }
    }//Fim do método excluir

    public void atualizar(int idAtualizar, Animal animalAtualizado) {
        List<Animal> animais = listarTodos(); //carrega lista dos animais cadastrados
        boolean atualizou = false; //pra poder saber se houve algum animal atualizado

        for (int i = 0; i < animais.size(); i++) { //percorre os animais //animais.size já sabe quantos elementos tem na lista
            if (animais.get(i).getId() == idAtualizar) {
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
                System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\nAnimal atualizado com exito!!" + ConsoleColors.RESET);
            }catch (IOException e){
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erro ao atualizar cadastro de animal!"+e.getMessage() + " " + ConsoleColors.RESET);
            }finally {
                try{
                    if (writer != null) {
                        writer.close();
                    }
                }catch (IOException e){
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erro ao fechar o arquivo!"+e.getMessage()+ " " + ConsoleColors.RESET);
                }
            }
        }else{
            System.out.println("Animal não encontrado!");
        }
    }//fim do método atualizar

    public static Animal buscarPorId(int idBuscado) {
        List<Animal> animais = new AnimalDAO().listarTodos(); // você pode usar um método estático, se preferir

        for (Animal animal : animais) {
            if (animal.getId() == idBuscado) {
                return animal; // Retorna o animal encontrado
            }
        }

        return null; // Retorna null se não encontrar
    }

}//final da classe AnimalDAO