package dao;

import modelo.HistoricoMedico;
import java.io.BufferedWriter;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import modelo.Animal;

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
    public List<HistoricoMedico> ListarTodos(){ //criação do método ListarTodos - do tipo HistoricoMedico
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

    //método para excluir histórico médico
    public void Excluir (String nomeExcluir){
        List<HistoricoMedico> historicos = ListarTodos();
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.nomeArquivo));
        boolean removido = historicos.removeIf()
    }

}//Fim classe HistoricoMedicoDAO
