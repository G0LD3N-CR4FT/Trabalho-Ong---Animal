package dao;

import colors.ConsoleColors;
import modelo.Adocao;
import modelo.Adotante;
import modelo.Animal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdocaoDAO {
        private static String nomeArquivo = "adocao.txt";

    public static void salvar(Adocao novoAdocao) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(nomeArquivo, true));

            // Montar string dos IDs dos animais
            String idsAnimaisStr = novoAdocao.getListaAnimal().stream()
                    .map(animal -> String.valueOf(animal.getId()))
                    .collect(Collectors.joining(";")); // para Java 8+

            writer.write(novoAdocao.getIdAdocao() + "," +
                    novoAdocao.getDataAdocao() + "," +
                    novoAdocao.getAdotante().getIdAdotante() + "," +
                    idsAnimaisStr + "," +
                    novoAdocao.isAprovado());
            writer.newLine();

            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\nAdoção com Id " + novoAdocao.getIdAdocao() + " inserida com sucesso!!" + ConsoleColors.RESET);
        } catch (IOException e) {
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erro ao inserir Adoção com Id: " + novoAdocao.getIdAdocao() + " " + ConsoleColors.RESET);
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erro ao fechar o arquivo: " + e.getMessage() + " " + ConsoleColors.RESET);
            }
        }
    }
//fim do criar


    public static List<Adocao> listarTodos() {
        List<Adocao> listaAdocoes = new ArrayList<>();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 5) {
                    int idAdocao = Integer.parseInt(dados[0]);
                    String dataAdocao = dados[1];

                    String[] idsAnimais = dados[3].split(";");
                    List<Animal> listaAnimais = new ArrayList<>();
                    for (String idStr : idsAnimais) {
                        int idAnimal = Integer.parseInt(idStr);
                        Animal animal = AnimalDAO.buscarPorId(idAnimal);
                        if (animal != null) {
                            listaAnimais.add(animal);
                        }
                    }

                    int idAdotante = Integer.parseInt(dados[2]);
                    Adotante adotante = AdotanteDAO.buscarPorId(idAdotante);

                    boolean aprovado = Boolean.parseBoolean(dados[4]);

                    Adocao adocao = new Adocao(idAdocao, dataAdocao, listaAnimais, adotante, aprovado);
                    listaAdocoes.add(adocao);
                }
            }
        } catch (IOException e) {
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erro ao ler o arquivo: " + e.getMessage() + " " + ConsoleColors.RESET);
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erro ao fechar o arquivo: " + e.getMessage() + " " + ConsoleColors.RESET);
            }
        }

        return listaAdocoes;
    }

    public static void excluir(int idAdocao) {
        List<Adocao> listaAdocoes = listarTodos();
        boolean removido = listaAdocoes.removeIf(adocao -> adocao.getIdAdocao() == idAdocao);

        if (removido) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
                for (Adocao adocao : listaAdocoes) {
                    String idsAnimaisStr = adocao.getListaAnimal().stream()
                            .map(animal -> String.valueOf(animal.getId()))
                            .collect(Collectors.joining(";"));

                    writer.write(adocao.getIdAdocao() + "," +
                            adocao.getDataAdocao() + "," +
                            adocao.getAdotante().getIdAdotante() + "," +
                            idsAnimaisStr + "," +
                            adocao.isAprovado());
                    writer.newLine();
                }
                System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\nAdoção removida com sucesso!" + ConsoleColors.RESET);
            } catch (IOException e) {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erro ao reescrever o arquivo: " + e.getMessage()+ " " + ConsoleColors.RESET);
            }
        } else {
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Adoção com ID " + idAdocao + " não encontrada." + ConsoleColors.RESET);
        }
    }

    public static void atualizar(int idAdocao, Adocao adocaoAtualizada) {
        List<Adocao> listaAdocoes = listarTodos();
        boolean atualizou = false;

        for (int i = 0; i < listaAdocoes.size(); i++) {
            if (listaAdocoes.get(i).getIdAdocao() == idAdocao) {
                listaAdocoes.set(i, adocaoAtualizada);
                atualizou = true;
                break;
            }
        }

        if (atualizou) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
                for (Adocao adocao : listaAdocoes) {
                    String idsAnimaisStr = adocao.getListaAnimal().stream()
                            .map(animal -> String.valueOf(animal.getId()))
                            .collect(Collectors.joining(";"));

                    writer.write(adocao.getIdAdocao() + "," +
                            adocao.getDataAdocao() + "," +
                            adocao.getAdotante().getIdAdotante() + "," +
                            idsAnimaisStr + "," +
                            adocao.isAprovado());
                    writer.newLine();
                }
                System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\nAdoção atualizada com sucesso!" + ConsoleColors.RESET);
            } catch (IOException e) {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erro ao reescrever o arquivo: " + e.getMessage() + " " + ConsoleColors.RESET);
            }
        } else {
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Adoção com ID " + idAdocao + " não encontrada." + ConsoleColors.RESET);
        }
    }


}
