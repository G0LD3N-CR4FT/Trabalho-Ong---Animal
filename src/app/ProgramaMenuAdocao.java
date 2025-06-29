package app;

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
        AdocaoDAO adocaoDAO = new AdocaoDAO(); // cria DAO
        AnimalDAO animalDAO = new AnimalDAO();// cria DAO

        do {
            System.out.println("\n====== MENU ADOÇÃO ======");
            System.out.println("1. Inserir Adoção");
            System.out.println("2. Listar Adoções");
            System.out.println("3. Excluir Adoção");
            System.out.println("4. Atualizar Adoção");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcaoAdocao = entrada.nextInt();
            entrada.nextLine(); // limpar o Enter
            System.out.println();

            switch (opcaoAdocao) {
                case 1:
                    System.out.println("Id da Adoção: ");
                    int idAdocao = entrada.nextInt();
                    entrada.nextLine(); //pular line

                    System.out.println("Data da Adocao: ");
                    String data = entrada.nextLine();

                    System.out.println("Escolha um adotante cadastrado: ");
                    List<Adotante> adotantes = AdotanteDAO.listarTodos();
                    for(Adotante a : adotantes){
                        System.out.println("Id: "+a.getIdAdotante()+ " Nome: "+a.getNome());
                    }

                    boolean achou = false;
                    Adotante adotante = new Adotante();

                    // Loop até encontrar um ID válido
                    do {
                        System.out.print("Id do Adotante: ");
                        int idAdotante = entrada.nextInt();
                        entrada.nextLine();

                        for(Adotante a : adotantes){
                            if(a.getIdAdotante() == idAdotante){
                                achou = true;
                                adotante = a;
                            }
                        }
                        if (!achou) {
                            System.out.println("Adotante não cadastrado. Tente novamente.");
                        }
                    } while (!achou);

                    String opcaoAnimal = "S";
                    List<Animal> listaAnimais = new ArrayList<Animal>();
                    List<Animal> animais = animalDAO.listarTodos();

                    while(!opcaoAnimal.equalsIgnoreCase("N")) {
                        System.out.println("Escolha um animal cadastrado: ");
                        for(Animal a : animais){
                            System.out.println("Id: "+a.getId()+ " Nome: "+a.getNome());
                        }

                    achou = false;

                        // Loop até encontrar um ID válido
                        do {
                            System.out.println();
                            System.out.println("Id animal: ");
                            int idAnimal = entrada.nextInt();
                            entrada.nextLine();
                            List<Animal> copiaAnimais = animais;

                            Iterator<Animal> iterator = animais.iterator();
                            while (iterator.hasNext()) {
                                Animal a = iterator.next();
                                if (a.getId() == idAnimal) {
                                    achou = true;
                                    listaAnimais.add(a);
                                    animais.remove(a); // forma segura de remover
                                    break;
                                }
                            }

                            if (!achou) {
                                System.out.println("Animal não cadastrado. Tente novamente.");
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
                    System.out.println("\n==== Listando Adoções ====");
                    List<Adocao> listaAdocoes = AdocaoDAO.listarTodos();
                    if (listaAdocoes.isEmpty()) {
                        System.out.println("Nenhuma adoção cadastrada.");
                    } else {
                        for (Adocao h : listaAdocoes) {
                            System.out.println(h);
                        }
                    }
                    break;
                case 3:
//                    System.out.println("\n==== Excluindo Histórico Médico ====");
//                    List<HistoricoMedico> listaHistorico = historicoDAO.listarTodos();
//                    if (listaHistorico.isEmpty()) {
//                        System.out.println("Nenhum histórico médico cadastrado.");
//                    } else {
//                        for (HistoricoMedico h : listaHistorico) {
//                            System.out.println(h);
//                        }
//                    }
//                    System.out.println("Informe o Id do histórico a ser excluido:");
//                    int idExcluir = Integer.parseInt(entrada.nextLine());
//                    historicoDAO.excluir(idExcluir);
                      break;
                case 4:
//                    System.out.println("\n==== Atualizando Histórico Médico ====");
//                    List<HistoricoMedico> listaHistoricosAtualizar = historicoDAO.listarTodos();
//                    if (listaHistoricosAtualizar.isEmpty()) {
//                        System.out.println("Nenhum histórico médico cadastrado.");
//                    } else {
//                        for (HistoricoMedico h : listaHistoricosAtualizar) {
//                            System.out.println(h);
//                        }
//
//                        System.out.println("Informe o ID do histórico que deseja atualizar:");
//                        int idAtualizar = Integer.parseInt(entrada.nextLine());
//
//                        System.out.println("Novo ID do animal:");
//                        int novoIdAnimal = Integer.parseInt(entrada.nextLine());
//
//                        System.out.println("Nova data:");
//                        String novaData = entrada.nextLine();
//
//                        System.out.println("Novo tipo (ex: vacina, cirurgia):");
//                        String novoTipo = entrada.nextLine();
//
//                        System.out.println("Nova descrição:");
//                        String novaDescricao = entrada.nextLine();
//
//                        HistoricoMedico historicoAtualizado = new HistoricoMedico(idAtualizar, novoIdAnimal, novaData, novoTipo, novaDescricao);
//                        historicoDAO.atualizar(idAtualizar, historicoAtualizado);
//                    }
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

}//fim da classe ProgramaMenuHistoricoMedico.


