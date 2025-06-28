package dao;

import modelo.HistoricoMedico;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoMedicoDAO {
    private String nomearquivo = "Historico.txt"; //nome do arquivo onde sera gravado os dados

    //método para criar/salvar histórico médico
    public void salvar(HistoricoMedico novoHistoricoMedico){
        BufferedWriter historicoMedico=null; //cria variável do tipo gravador, apontando pra NULL
        try{
            historicoMedico = new BufferedWriter(new FileWriter(nomearquivo, true)); //abre arquivo no modo append
            historicoMedico.write(novoHistoricoMedico.getIdHistorico()+","+novoHistoricoMedico.getIdAnimal()+","+
                    novoHistoricoMedico.getData()+","+novoHistoricoMedico.getTipo()+","+
                    novoHistoricoMedico.getDescricao());
            historicoMedico.newLine();
            System.out.println("Histórico médico inserido com sucesso!");
        }catch (IOException e){
            System.out.println("Erro ao salvar histórico médico"+e.getMessage());
        }finally { //fechar o arquivo
            try {
                historicoMedico.close();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }//fim do salvar historicoMedico


    //método para listar o histórico médico
    public List<HistoricoMedico> listarTodos(){ //criação do método ListarTodos - do tipo HistoricoMedico
        List<HistoricoMedico> historicos = new ArrayList<>(); //cria a lista pra armazenar os historicos

        try{
            BufferedReader reader = new BufferedReader(new FileReader(nomearquivo));
            String linha; //armazena temporariamente cada linha do arquivo durante a leitura
            while((linha = reader.readLine())!=null){ //lê a linha no arquivo e guarda o conteúdo enquanto != null
                String [] dados = linha.split(","); //Divide a linha em partes com base nas vírgulas
                if(dados.length == 5){ //Verifica se a linha lida do arquivo foi dividida em exatamente 5 partes
                    HistoricoMedico h = new HistoricoMedico(
                            Integer.parseInt(dados[0]), //idHistorico
                            Integer.parseInt(dados[1]), //idAnimal
                            dados[2],  //data
                            dados[3],  //tipo
                            dados[4]); //descrição ---- //Passando todos os 5 valores para o construtor da classe
                    historicos.add(h);//objeto agora é colocado na lista de históricos
                }
            }
            reader.close(); //fecha o BufferedReader depois da leitura para liberar o arquivo do sistema operacional.
        }catch (FileNotFoundException e){
            System.out.println("Arquivo de histórico médico ainda não existe!");
        }catch (IOException e){
            System.out.println("Erro ao abrir o arquivo!"+e.getMessage());
        }
        return historicos;
    }//fim do método listarTodos


    //método para Atualizar histórico médico
    public void atualizar(int idHistoricoAtualizar, HistoricoMedico historicoAtualizado) {
        List<HistoricoMedico> historicos = listarTodos(); // carrega todos os históricos
        boolean atualizou = false;

        for (int i = 0; i < historicos.size(); i++) {
            if (historicos.get(i).getIdHistorico() == idHistoricoAtualizar) {
                historicos.set(i, historicoAtualizado); // substitui pelo novo
                atualizou = true;
                break;
            }
        }

        if (atualizou) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(nomearquivo));
                for (HistoricoMedico h : historicos) {
                    writer.write(h.getIdHistorico() + "," + h.getIdAnimal() + "," + h.getData() + "," +
                            h.getTipo() + "," + h.getDescricao());
                    writer.newLine();
                }
                writer.close();
                System.out.println("Histórico médico atualizado com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao atualizar histórico médico: " + e.getMessage());
            }
        } else {
            System.out.println("Histórico com ID " + idHistoricoAtualizar + " não encontrado!");
        }
    }//Fim do método para Atualizar Histórico médico

    //método para excluir histórico médico
    public void excluir (int idHistorico) {
        List<HistoricoMedico> historicos = listarTodos(); //carrega o arquivo
        boolean removido = historicos.removeIf(h -> h.getIdHistorico() == idHistorico); //se achar vai remover
        if (removido) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(nomearquivo)); //abre aquivo no modo gravação de sobreescrever sem o true
                for (HistoricoMedico h : historicos) {
                    writer.write(h.getIdHistorico() + "," + h.getIdAnimal() + "," + h.getData() + "," +
                            h.getTipo() + "," + h.getDescricao());
                    writer.newLine(); //serve pra escrever uma linha de separação dos contatos
                }
                writer.close(); //fecha arquivo de escrita
                System.out.println("Histórico médico removido com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao exlucir aquivo!");
            }
        }else{
            System.out.println("Histórico médico com id"+idHistorico+"não encontrado!");
        }

    }//fim do método excluir


}//Fim classe HistoricoMedicoDAO
