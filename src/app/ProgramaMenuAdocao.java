package app;

import colors.ConsoleColors;
import dao.AdocaoDAO;
import dao.AdotanteDAO;
import modelo.Adocao;
import modelo.Adotante;
import modelo.Animal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;
import dao.AnimalDAO;

public class ProgramaMenuAdocao {

    public static void menuAdocao(){
        Scanner entrada = new Scanner(System.in);
        int opcaoAdocao;
        AdocaoDAO adocaoDAO = new AdocaoDAO();
        AnimalDAO animalDAO = new AnimalDAO();

        do {
            System.out.println(ConsoleColors.CYAN_BOLD + "\n====== MENU ADOCACAO ======" + ConsoleColors.RESET);
            System.out.println("1. Inserir Adocao");
            System.out.println("2. Listar Adocoes");
            System.out.println("3. Excluir Adocao");
            System.out.println("4. Atualizar Adocao");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.println(ConsoleColors.CYAN_BOLD + "===========================" + ConsoleColors.RESET);
            System.out.print("Escolha uma opcao: ");
            opcaoAdocao = entrada.nextInt();
            entrada.nextLine();
            System.out.println();

            switch (opcaoAdocao) {
                case 1:
                    System.out.println("Id da Adocao: ");
                    int idAdocao = entrada.nextInt();
                    entrada.nextLine();

                    System.out.println("Data da Adocao: ");
                    String data = entrada.nextLine();

                    System.out.println("Escolha um adotante cadastrado: ");
                    List<Adotante> adotantes = AdotanteDAO.listarTodos();
                    for(Adotante a : adotantes){
                        System.out.printf("%-5s | %-20s\n", "ID: " + a.getIdAdotante(), "Nome: " + a.getNome());
                    }

                    boolean achou = false;
                    Adotante adotante = new Adotante();

                    do {
                        System.out.println("Id do Adotante: ");
                        int idAdotante = entrada.nextInt();
                        entrada.nextLine();

                        for(Adotante a : adotantes){
                            if(a.getIdAdotante() == idAdotante){
                                achou = true;
                                adotante = a;
                            }
                        }
                        if (!achou) {
                            System.out.println("Adotante nao cadastrado. Tente novamente.");
                        }
                    } while (!achou);

                    String opcaoAnimal = "S";
                    List<Animal> listaAnimais = new ArrayList<>();
                    List<Animal> animais = animalDAO.listarTodos();

                    while(!opcaoAnimal.equalsIgnoreCase("N")) {
                        System.out.println("Animais disponiveis: ");
                        for(Animal a : animais){
                            System.out.printf("%-5s | %-20s\n", "ID: " + a.getId(), "Nome: " + a.getNome());
                        }

                        achou = false;

                        do {
                            System.out.println();
                            System.out.println("Id animal: ");
                            int idAnimal = entrada.nextInt();
                            entrada.nextLine();

                            Iterator<Animal> iterator = animais.iterator();
                            while (iterator.hasNext()) {
                                Animal a = iterator.next();
                                if (a.getId() == idAnimal) {
                                    achou = true;
                                    listaAnimais.add(a);
                                    iterator.remove();
                                    break;
                                }
                            }

                            if (!achou) {
                                System.out.println("Animal nao cadastrado. Tente novamente.");
                            }
                        } while (!achou);

                        System.out.println("Deseja adotar outro animal? (S/N): ");
                        opcaoAnimal = entrada.nextLine();
                    }

                    System.out.println("Adocao aprovada (true/false): ");
                    boolean aprovado = entrada.nextBoolean();

                    Adocao novaAdocao = new Adocao(idAdocao, data, listaAnimais, adotante, aprovado);
                    AdocaoDAO.salvar(novaAdocao);
                    break;
                case 2:
                    System.out.println("\n==== Listando Adocoes ====");
                    List<Adocao> listaAdocoes = AdocaoDAO.listarTodos();
                    if (listaAdocoes.isEmpty()) {
                        System.out.println("Nenhuma adocao cadastrada.");
                    } else {
                        for (Adocao h : listaAdocoes) {
                            System.out.println(h);
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n==== Excluir Adocao ====");
                    List<Adocao> adocoes = AdocaoDAO.listarTodos();
                    if (adocoes.isEmpty()) {
                        System.out.println("Nenhuma adocao cadastrada.");
                    } else {
                        for (Adocao a : adocoes) {
                            System.out.println(a);
                        }

                        System.out.print("Informe o ID da adocao a ser excluida: ");
                        int idExcluir = entrada.nextInt();
                        entrada.nextLine();

                        AdocaoDAO.excluir(idExcluir);
                    }
                    break;

                case 4:
                    System.out.println("\n==== Atualizar Adocao ====");
                    List<Adocao> adocoesAtualizar = AdocaoDAO.listarTodos();
                    if (adocoesAtualizar.isEmpty()) {
                        System.out.println("Nenhuma adocao cadastrada.");
                    } else {
                        for (Adocao a : adocoesAtualizar) {
                            System.out.println(a);
                        }

                        System.out.print("Informe o ID da adocao que deseja atualizar: ");
                        int idAtualizar = entrada.nextInt();
                        entrada.nextLine();

                        System.out.print("Nova data da adocao: ");
                        String novaData = entrada.nextLine();

                        System.out.println("Escolha um novo adotante:");
                        List<Adotante> adotantesAtualizar = AdotanteDAO.listarTodos();
                        for (Adotante adot : adotantesAtualizar) {
                            System.out.printf("%-5s | %-20s\n", "ID: " + adot.getIdAdotante(), "Nome: " + adot.getNome());
                        }

                        Adotante novoAdotante = null;
                        boolean achouNovoAdotante = false;
                        do {
                            System.out.print("Id do novo adotante: ");
                            int novoIdAdotante = entrada.nextInt();
                            entrada.nextLine();

                            for (Adotante adot : adotantesAtualizar) {
                                if (adot.getIdAdotante() == novoIdAdotante) {
                                    novoAdotante = adot;
                                    achouNovoAdotante = true;
                                    break;
                                }
                            }

                            if (!achouNovoAdotante) {
                                System.out.println("Adotante nao encontrado. Tente novamente.");
                            }
                        } while (!achouNovoAdotante);

                        String novaOpcaoAnimal = "S";
                        List<Animal> novaListaAnimais = new ArrayList<>();
                        List<Animal> todosAnimais = animalDAO.listarTodos();

                        while (!novaOpcaoAnimal.equalsIgnoreCase("N")) {
                            System.out.println("Animais disponiveis:");
                            for (Animal a : todosAnimais) {
                                System.out.printf("%-5s | %-20s\n", "ID: " + a.getId(), "Nome: " + a.getNome());
                            }

                            System.out.print("Id do animal: ");
                            int idAnimal = entrada.nextInt();
                            entrada.nextLine();

                            boolean animalAchado = false;
                            Iterator<Animal> iterator = todosAnimais.iterator();
                            while (iterator.hasNext()) {
                                Animal a = iterator.next();
                                if (a.getId() == idAnimal) {
                                    novaListaAnimais.add(a);
                                    iterator.remove(); // remove para nao permitir repeticao
                                    animalAchado = true;
                                    break;
                                }
                            }

                            if (!animalAchado) {
                                System.out.println("Animal nao encontrado. Tente novamente.");
                            }

                            System.out.print("Deseja adicionar outro animal? (S/N): ");
                            novaOpcaoAnimal = entrada.nextLine();
                        }

                        System.out.print("Adocao aprovada (true/false): ");
                        boolean novoAprovado = entrada.nextBoolean();
                        entrada.nextLine();

                        Adocao novaAdocaoAtualizada = new Adocao(idAtualizar, novaData, novaListaAnimais, novoAdotante, novoAprovado);
                        AdocaoDAO.atualizar(idAtualizar, novaAdocaoAtualizada);
                    }
                    break;

                case 0:
                    System.out.println("Retornando ao menu principal...");
                    return;
                default:
                    System.out.println("Selecione uma opcao valida");
            }

        } while (opcaoAdocao != 0);
        entrada.close();
    }

}//fim da classe ProgramaMenuAdocao