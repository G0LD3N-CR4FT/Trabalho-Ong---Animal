package dao;

import model.Pessoa;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PessoaDAO {
    private String nomeArquivo = "pessoa.txt";

    public void criar(Pessoa novaPessoa) {
        BufferedWriter pessoa=null;
        try{
            pessoa = new BufferedWriter(new FileWriter(nomeArquivo, true));
            pessoa.write(novaPessoa.getNome()+","
                    + novaPessoa.getEmail()+"," +novaPessoa.getTelefone());
            pessoa.newLine();
            System.out.println("Pessoa "+novaPessoa.getNome()+ " inserida com sucesso!");
        } catch (IOException e){
            System.err.println("Erro ao inserir a pessoa: "+novaPessoa.getNome());
        } finally {
            try {
                pessoa.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public List<Pessoa> listarTodos() {
        List<Pessoa> pessoas = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            while ((linha = reader.readLine())!=null){
                String[] dados = linha.split(",");
                if(dados.length == 3){
                    Pessoa pessoa = new Pessoa(dados[0], dados[2], dados[1]);
                    pessoas.add(pessoa);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não existe");
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo "+nomeArquivo);
        }
        return pessoas;
    }

    public void excluir(String nomeExcluir) {
        List<Pessoa> pessoas = new ArrayList<>();
        boolean achou = false;

        BufferedReader reader = null;
        BufferedWriter escrever = null;
        try {
            reader = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados[0].equals(nomeExcluir)) {
                    achou = true;
                    continue;
                }
                Pessoa pessoa = new Pessoa(dados[0], dados[2], dados[1]);
                pessoas.add(pessoa);
            }

            // Mensagem se não achou o pessoa a ser deletado
            if (!achou) {
                System.out.println("Nome da pessoa não foi encontrado");
            }

            // Sobreescrever
            if (achou) {
                escrever = new BufferedWriter(new FileWriter(nomeArquivo));
                for (Pessoa pessoa : pessoas) {
                    try {
                        escrever.write(pessoa.getNome() + ","
                                + pessoa.getEmail() + "," + pessoa.getTelefone());
                        escrever.newLine();
                    } catch (IOException e) {
                        System.err.println("Erro ao inserir a pessoa: " + pessoa.getNome());
                    }
                }
                escrever.close();
                System.out.println("Pessoa excluida com sucesso!");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não existe");
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo " + nomeArquivo);
        }  finally {
            try {
                reader.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void atualizar(String nomeAtualizar, Pessoa pessoaAtualizado) {
        List<Pessoa> pessoas = new ArrayList<>();
        boolean achou = false;

        BufferedReader reader = null;
        BufferedWriter escrever = null;
        try {
            reader = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados[0].equals(nomeAtualizar)) {
                    // inserindo o pessoa atualizado
                    pessoas.add(pessoaAtualizado);
                    achou = true;
                    continue;
                }
                Pessoa pessoa = new Pessoa(dados[0], dados[2], dados[1]);
                pessoas.add(pessoa);
            }

            // Mensagem se não achou o pessoa a ser deletado
            if (!achou) {
                System.out.println("Nome da pessoa não foi encontrado");
            }

            if (achou) {
                // Sobreescrever
                escrever = new BufferedWriter(new FileWriter(nomeArquivo));
                for (Pessoa pessoa : pessoas) {
                    try {
                        escrever.write(pessoa.getNome() + ","
                                + pessoa.getEmail() + "," + pessoa.getTelefone());
                        escrever.newLine();
                    } catch (IOException e) {
                        System.err.println("Erro ao inserir o pessoa: " + pessoa.getNome());
                    }
                }
                escrever.close();
                System.out.println("Pessoa Atualizada com sucesso!");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não existe");
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo " + nomeArquivo);
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
