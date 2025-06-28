package dao;
import modelo.Adotante;
import modelo.Animal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class AdotanteDAO {
    private String nomeArquivo = "Adotante.txt";

    public void criar(Adotante novoAdotante){
        BufferedWriter writer=null;
        try{
            writer = new BufferedWriter(new FileWriter(nomeArquivo, true));
            writer.write(novoAdotante.getIdPessoa()+ "," +novoAdotante.getNome()+ "," +novoAdotante.getEmail()+ "," +
                            novoAdotante.getTelefone()+","+novoAdotante.getIdAdotante()+","+novoAdotante.getQuestionario());
            writer.newLine();
        System.out.println("Adotante " +novoAdotante.getNome()+" inserido com sucesso!!");
    }catch (IOException e){
        System.out.println("Erro ao inserir adotante: " +novoAdotante.getNome());
    }finally {
            try{
                if(writer!=null) writer.close();
            }catch (IOException e){
                System.out.println("Erro ao fechar o arquivo: " +e.getMessage());
            }
        }
        }//fim do criar


    public List<Adotante> listarTodos() {
        List<Adotante> listaAdotantes = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 6) {
                    Adotante adotante = new Adotante(Integer.parseInt(dados[0]), dados[1], dados[2], dados[3],
                            Integer.parseInt(dados[4]), dados[5]);
                    listaAdotantes.add(adotante);
                }//fim if
         }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não existe");
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo " + nomeArquivo);
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar o arquivo!" + e.getMessage());
            }
        }
        return listaAdotantes;
}//fim da classe listarTodos().

    public void excluir(int idAdotanteExcluir){
        List<Adotante> listaAdotantes = listarTodos();
        boolean removido = listaAdotantes.removeIf(adotante -> adotante.getIdAdotante()==idAdotanteExcluir);

        if (removido) {
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(this.nomeArquivo));

                for (Adotante novoAdotante : listaAdotantes) {
                    writer.write(novoAdotante.getIdPessoa() + "," + novoAdotante.getNome() + "," +
                            novoAdotante.getEmail()+ "," + novoAdotante.getTelefone() + "," + novoAdotante.getIdAdotante() + "," +
                            novoAdotante.getQuestionario());
                    writer.newLine();
                }
                System.out.println("Adotante removido com sucesso!");
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
            System.out.println("Adotante não existe!");
        }
    }//fim da classe Excluir

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
                    writer.write(novoAdotante.getIdPessoa() + "," + novoAdotante.getNome() + "," +
                            novoAdotante.getEmail()+ "," + novoAdotante.getTelefone() + "," + novoAdotante.getIdAdotante() + "," +
                            novoAdotante.getQuestionario());
                    writer.newLine();
                }
                System.out.println("Adotante atualizado com exito!!");
            }catch (IOException e){
                System.out.println("Erro ao atualizar cadastro de adotante!"+e.getMessage());
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
            System.out.println("Adotante não encontrado!");
        }
    }//fim do método atualizar


}//fim da classe AdotanteDAO