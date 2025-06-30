package app;

import colors.ConsoleColors;
import dao.HistoricoMedicoDAO;
import modelo.Animal;
import modelo.HistoricoMedico;
import java.util.Scanner;
import java.util.List;
import dao.AnimalDAO;

public class ProgramaMenuHistoricoMedico{

    public static void menuHistorico(){
        Scanner entrada = new Scanner(System.in);
        int opcaoHistorico;
        HistoricoMedicoDAO historicoDAO = new HistoricoMedicoDAO(); // cria DAO
        AnimalDAO animalDAO = new AnimalDAO();// cria DAO

        do {
            System.out.println(ConsoleColors.RED_BOLD + "\n====== HISTÓRICO MÉDICO ======" + ConsoleColors.RESET);
            System.out.println("1. Inserir Histórico Médico");
            System.out.println("2. Listar Históricos Médicos");
            System.out.println("3. Excluir Histórico Médico");
            System.out.println("4. Atualizar Histórico Médico");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.println(ConsoleColors.RED_BOLD + "==============================" + ConsoleColors.RESET);
            System.out.print("Escolha uma opção: ");
            opcaoHistorico = entrada.nextInt();
            entrada.nextLine(); // limpar o Enter
            System.out.println();

            switch (opcaoHistorico) {
                case 1:
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "\n==== Inserindo Histórico Médico ===="+ ConsoleColors.RESET);
                    System.out.println("Id do histórico médico: ");
                    int idHistorico = entrada.nextInt();
                    entrada.nextLine(); //pular line

                    System.out.println("Escolha um animal cadastrado: ");
                    List<Animal> animais = animalDAO.listarTodos();
                    for(Animal a : animais){
                        System.out.println("Id: "+a.getId()+ " Nome: "+a.getNome());
                    }

                    System.out.println();
                    System.out.println("Id animal: ");
                    int idAnimalHistorico = entrada.nextInt();
                    entrada.nextLine();

                    System.out.println("Data: ");
                    String data = entrada.nextLine();


                    System.out.println("Tipo: (ex: vacina,castração..)");
                    String tipo = entrada.nextLine();

                    System.out.println("Descrição: (ex antirrábica...");
                    String descricao = entrada.nextLine();

                    HistoricoMedico novoHistorico = new HistoricoMedico(idHistorico, idAnimalHistorico, data, tipo, descricao);
                    historicoDAO.salvar(novoHistorico);
                    break;
                case 2:
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "\n==== Listando Histórico Médico ===="+ ConsoleColors.RESET);
                    List<HistoricoMedico> listaHistoricos = historicoDAO.listarTodos();
                    if (listaHistoricos.isEmpty()) {
                        System.out.println("Nenhum histórico médico cadastrado.");
                    } else {
                        for (HistoricoMedico h : listaHistoricos) {
                            System.out.println(ConsoleColors.WHITE_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD_BRIGHT + h + ConsoleColors.RESET);
                        }
                    }
                    break;
                case 3:
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "\n==== Excluindo Histórico Médico ===="+ ConsoleColors.RESET);
                    List<HistoricoMedico> listaHistorico = historicoDAO.listarTodos();
                    if (listaHistorico.isEmpty()) {
                        System.out.println("Nenhum histórico médico cadastrado.");
                    } else {
                        for (HistoricoMedico h : listaHistorico) {
                            System.out.println(h);
                        }
                    }
                    System.out.println("Informe o Id do histórico a ser excluido:");
                    int idExcluir = Integer.parseInt(entrada.nextLine());
                    historicoDAO.excluir(idExcluir);
                    break;
                case 4:
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "\n==== Atualizando Histórico Médico ====");
                    List<HistoricoMedico> listaHistoricosAtualizar = historicoDAO.listarTodos();
                    if (listaHistoricosAtualizar.isEmpty()) {
                        System.out.println("Nenhum histórico médico cadastrado.");
                    } else {
                        for (HistoricoMedico h : listaHistoricosAtualizar) {
                            System.out.println(h);
                        }

                        System.out.println("Informe o ID do histórico que deseja atualizar:");
                        int idAtualizar = Integer.parseInt(entrada.nextLine());

                        System.out.println("Novo ID do animal:");
                        int novoIdAnimal = Integer.parseInt(entrada.nextLine());

                        System.out.println("Nova data:");
                        String novaData = entrada.nextLine();

                        System.out.println("Novo tipo (ex: vacina, cirurgia):");
                        String novoTipo = entrada.nextLine();

                        System.out.println("Nova descrição:");
                        String novaDescricao = entrada.nextLine();

                        HistoricoMedico historicoAtualizado = new HistoricoMedico(idAtualizar, novoIdAnimal, novaData, novoTipo, novaDescricao);
                        historicoDAO.atualizar(idAtualizar, historicoAtualizado);
                    }
                    break;
                case 0:
                    System.out.println("Retornando ao menu principal...");
                    return;
                default:
                    System.out.println("Selecione uma opcao valida");
            }

        } while (opcaoHistorico != 0);
        entrada.close();
    }

}//fim da classe ProgramaMenuHistoricoMedico.


