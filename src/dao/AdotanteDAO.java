package dao;
import colors.ConsoleColors;
import modelo.Adotante;
import modelo.Animal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class AdotanteDAO {
    private static String nomeArquivo = "adotante.txt";

    public void criar(Adotante novoAdotante){
        BufferedWriter writer=null;
        try{
            writer = new BufferedWriter(new FileWriter(nomeArquivo, true));
            writer.write(novoAdotante.getNome()+ "," +novoAdotante.getEmail()+ "," +
                            novoAdotante.getTelefone()+","+novoAdotante.getIdAdotante()+","+novoAdotante.getQuestionario());
            writer.newLine();
        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\nAdotante " +novoAdotante.getNome()+" inserido com sucesso!!" + ConsoleColors.RESET);
    }catch (IOException e){
        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erro ao inserir adotante: " +novoAdotante.getNome() + " " + ConsoleColors.RESET);
    }finally {
            try{
                if(writer!=null) writer.close();
            }catch (IOException e){
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erro ao fechar o arquivo: " +e.getMessage()+ " " + ConsoleColors.RESET);
            }
        }
    }//fim do criar


    public static List<Adotante> listarTodos() {
        List<Adotante> listaAdotantes = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 5) {
                    Adotante adotante = new Adotante(dados[0], dados[1], dados[2],
                            Integer.parseInt(dados[3]), dados[4]);
                    listaAdotantes.add(adotante);
                }//fim if
         }
        } catch (FileNotFoundException e) {
            System.err.println(ConsoleColors.RED_BOLD_BRIGHT + "Arquivo não existe" + ConsoleColors.RESET);
        } catch (IOException e) {
            System.err.println(ConsoleColors.RED_BOLD_BRIGHT + "Erro ao ler o arquivo " + nomeArquivo + " " + ConsoleColors.RESET);
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erro ao fechar o arquivo!" + e.getMessage() + " " + ConsoleColors.RESET);
            }
        }
        return listaAdotantes;
}//fim listarTodos().

    public void excluir(int idAdotanteExcluir){
        List<Adotante> listaAdotantes = listarTodos();
        boolean removido = listaAdotantes.removeIf(adotante -> adotante.getIdAdotante()==idAdotanteExcluir);

        if (removido) {
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(this.nomeArquivo));

                for (Adotante novoAdotante : listaAdotantes) {
                    writer.write(novoAdotante.getNome() + "," +
                            novoAdotante.getEmail()+ "," + novoAdotante.getTelefone() + "," + novoAdotante.getIdAdotante() + "," +
                            novoAdotante.getQuestionario());
                    writer.newLine();
                }
                System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\nAdotante removido com sucesso!" + ConsoleColors.RESET);
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
            System.out.println("Adotante não existe!");
        }
    }//fim do Excluir

    public void atualizar(int idAdotanteAtualizar, Adotante adotanteAtualizado) {
        List<Adotante> listaAdotantes = listarTodos(); //carrega lista dos adotantes cadastrados
        boolean atualizou = false; //pra poder saber se houve algum adotante atualizado

        for (int i = 0; i < listaAdotantes.size(); i++) { //percorre os adotantes //adotantes.size já sabe quantos elementos tem na lista
            if (listaAdotantes.get(i).getIdAdotante()==idAdotanteAtualizar) {
                listaAdotantes.set(i, adotanteAtualizado);
                atualizou = true;
                break;
            }
        }
        if(atualizou){
            BufferedWriter writer = null;
            try{
                writer = new BufferedWriter(new FileWriter(this.nomeArquivo));
                for (Adotante novoAdotante : listaAdotantes){
                    writer.write(novoAdotante.getNome() + "," +
                            novoAdotante.getEmail()+ "," + novoAdotante.getTelefone() + "," + novoAdotante.getIdAdotante() + "," +
                            novoAdotante.getQuestionario());
                    writer.newLine();
                }
                System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\nAdotante atualizado com exito!!" + ConsoleColors.RESET);
            }catch (IOException e){
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erro ao atualizar cadastro de adotante!"+e.getMessage() +" "+ ConsoleColors.RESET);
            }finally {
                try{
                    if (writer != null) {
                        writer.close();
                    }
                }catch (IOException e){
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erro ao fechar o arquivo!"+e.getMessage() + " " + ConsoleColors.RESET);
                }
            }
        }else{
            System.out.println("Adotante não encontrado!");
        }
    }//fim do método atualizar

    public static Adotante buscarPorId(int idBuscado) {
        List<Adotante> listaAdotantes = listarTodos(); // carrega todos os adotantes do arquivo

        for (Adotante adotante : listaAdotantes) {
            if (adotante.getIdAdotante() == idBuscado) {
                return adotante; // retorna o primeiro que encontrar com o ID correspondente
            }
        }

        return null; // se não encontrar, retorna null
    }



}//fim da classe AdotanteDAO