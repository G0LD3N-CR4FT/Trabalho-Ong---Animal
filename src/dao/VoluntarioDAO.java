package dao;

import colors.ConsoleColors;
import modelo.Adotante;
import modelo.Voluntario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VoluntarioDAO{
    private String nomeArquivo = "Voluntario.txt";

    public void criar(Voluntario novoVoluntario){
        BufferedWriter writer=null;
        try{
            writer = new BufferedWriter(new FileWriter(nomeArquivo, true));
            writer.write(novoVoluntario.getNome()+ "," +novoVoluntario.getEmail()+ "," +
                    novoVoluntario.getTelefone()+","+novoVoluntario.idVoluntario+","+novoVoluntario.disponibilidade+","+novoVoluntario.habilidades);
            writer.newLine();
            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT +"Voluntário " +novoVoluntario.getNome()+" inserido com sucesso!!"+ ConsoleColors.RESET);
        }catch (IOException e){
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT +"Erro ao inserir voluntário: " +novoVoluntario.getNome()+ ConsoleColors.RESET);
        }finally {
            try{
                if(writer!=null) writer.close();
            }catch (IOException e){
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT +"Erro ao fechar o arquivo: " +e.getMessage()+ ConsoleColors.RESET);
            }
        }
    }//fim do método criar


    public List<Voluntario> listarTodos() {
        List<Voluntario> listaVoluntario = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 6) {
                    Voluntario voluntario = new Voluntario(dados[0], dados[1], dados[2],
                            Integer.parseInt(dados[3]), dados[4], dados[5]);
                    listaVoluntario.add(voluntario);
                }//fim if
            }
        } catch (FileNotFoundException e) {
            System.err.println(ConsoleColors.RED_BOLD_BRIGHT +"Arquivo não existe"+ ConsoleColors.RESET);
        } catch (IOException e) {
            System.err.println(ConsoleColors.RED_BOLD_BRIGHT +"Erro ao ler o arquivo " + nomeArquivo+ ConsoleColors.RESET);
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT +"Erro ao fechar o arquivo!" + e.getMessage()+ ConsoleColors.RESET);
            }
        }
        return listaVoluntario;
    }//fim do método listarTodos.


    public void excluir(int idVoluntarioExcluir){
        List<Voluntario> listaVoluntario = listarTodos();
        boolean removido = listaVoluntario.removeIf(voluntario -> voluntario.getIdVoluntario()==idVoluntarioExcluir);

        if (removido) {
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(this.nomeArquivo));

                for (Voluntario novoVoluntario : listaVoluntario) {
                    writer.write(novoVoluntario.getNome() + "," +
                            novoVoluntario.getEmail()+ "," + novoVoluntario.getTelefone() + "," + novoVoluntario.getIdVoluntario() + "," +
                            novoVoluntario.idVoluntario+","+novoVoluntario.disponibilidade+","+novoVoluntario.habilidades);
                    writer.newLine();
                }
                System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT +"Voluntário removido com sucesso!"+ ConsoleColors.RESET);
            } catch (IOException e) {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT +"Erro ao escrever no arquivo! " + nomeArquivo + ConsoleColors.RESET);
            } finally {
                try {
                    if (writer != null) writer.close();
                } catch (IOException e) {
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT +"Erro ao fechar o arquivo! " + e.getMessage()+ ConsoleColors.RESET);
                }
            }
        } else {
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT +"Voluntário não existe!"+ ConsoleColors.RESET);
        }
    }//fim do Excluir


    public void atualizar(int idVoluntarioAtualizar, Voluntario voluntarioAtualizado) {
        List<Voluntario> listaVoluntario = listarTodos(); //carrega lista dos voluntários cadastrados
        boolean atualizou = false; //pra poder saber se houve algum Voluntario atualizado

        for (int i = 0; i < listaVoluntario.size(); i++) { //percorre os voluntarios //voluntarios.size já sabe quantos elementos tem na lista
            if (listaVoluntario.get(i).getIdVoluntario()==idVoluntarioAtualizar) {
                listaVoluntario.set(i, voluntarioAtualizado);
                atualizou = true;
                break;
            }
        }
        if(atualizou){
            BufferedWriter writer = null;
            try{
                writer = new BufferedWriter(new FileWriter(this.nomeArquivo));
                for (Voluntario novoVoluntario : listaVoluntario){
                    writer.write(novoVoluntario.getNome() + "," +
                            novoVoluntario.getEmail()+ "," + novoVoluntario.getTelefone() + "," + novoVoluntario.getIdVoluntario() + "," +
                            novoVoluntario.idVoluntario+","+novoVoluntario.disponibilidade+","+novoVoluntario.habilidades);
                    writer.newLine();
                }
                System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT +"Voluntário atualizado com exito!!"+ ConsoleColors.RESET);
            }catch (IOException e){
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT +"Erro ao atualizar cadastro de voluntário!"+e.getMessage()+ ConsoleColors.RESET);
            }finally {
                try{
                    if (writer != null) {
                        writer.close();
                    }
                }catch (IOException e){
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT +"Erro ao fechar o arquivo!"+e.getMessage()+ ConsoleColors.RESET);
                }
            }
        }else{
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT +"Voluntário não encontrado!"+ ConsoleColors.RESET);
        }
    }//fim do método atualizar

}//fim da classe Voluntario

