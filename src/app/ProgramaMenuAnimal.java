package app;

import colors.ConsoleColors;
import dao.AnimalDAO;
import dao.HistoricoMedicoDAO;
import modelo.Animal;
import modelo.HistoricoMedico;

import java.util.List;
import java.util.Scanner;

public class ProgramaMenuAnimal {

    public static void menuAnimal() {

        Scanner entrada = new Scanner(System.in);

        AnimalDAO animalDAO = new AnimalDAO();
        int opcao;

        do{
            System.out.println(ConsoleColors.GREEN_BOLD + "\n======= MENU ANIMAL =======" + ConsoleColors.RESET);
            System.out.println("1. Inserir Animal");
            System.out.println("2. Listar Animal");
            System.out.println("3. Excluir Animal");
            System.out.println("4. Atualizar Animal");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.println(ConsoleColors.GREEN_BOLD + "===========================" + ConsoleColors.RESET);
            System.out.println("Escolha uma opção: ");
            opcao = entrada.nextInt();
            entrada.nextLine(); // consumir o caracter de quebra de linha (ENTER)

            switch (opcao){
                case 1:
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\n==== Inserindo Animal ====" + ConsoleColors.RESET);

                    System.out.println("Id :");
                    int idAnimal = entrada.nextInt();
                    entrada.nextLine(); //consumir o enter após inserir o Id

                    System.out.println("Nome :");
                    String nomeAnimal = entrada.nextLine();

                    System.out.println("Espécie :");
                    String especieAnimal = entrada.nextLine();

                    System.out.println("Raça :");
                    String racaAnimal = entrada.nextLine();

                    System.out.println("Idade :");
                    int idadeAnimal = entrada.nextInt();
                    entrada.nextLine(); //consumir o enter após inserir a idade

                    System.out.println("Sexo 'M'ou 'F' : ");
                    char sexoAnimal = entrada.nextLine().toUpperCase().charAt(0);

                    System.out.println("Status do animal :");
                    String statusAnimal = entrada.nextLine();

                    System.out.println("Foto :");
                    String fotoAnimal = entrada.nextLine();

                    System.out.println("Animal de resgate ? 'S' ou 'N' :");
                    char resgateAnimal = entrada.nextLine().toUpperCase().charAt(0);

                    // Declarrei a variável antes, pois ela pode ou não ser preenchida dependendo da resposta de cima
                    String resgateDate  = "";
                    String resgateLocal = "";

                    if (resgateAnimal == 'S') {
                        System.out.println("Data do Resgate: ");
                        resgateDate = entrada.nextLine();
                        System.out.println("Local onde ocorreu o resgate:");
                        resgateLocal = entrada.nextLine();
                    }

                    System.out.println("O animal é idoso? 'S' ou 'N':");
                    char adoteCoracao = entrada.nextLine().toUpperCase().charAt(0);

                    // Garantir que a resposta seja apenas 'S' ou 'N'
                    if (adoteCoracao != 'S' && adoteCoracao != 'N') {
                        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Opção inválida, Definir como 'N' por padrão." + ConsoleColors.RESET);
                        adoteCoracao = 'N';
                    }

                    Animal novoAnimal = new Animal(idAnimal,nomeAnimal,especieAnimal,racaAnimal,idadeAnimal,sexoAnimal,
                            statusAnimal,fotoAnimal,resgateDate,resgateLocal,adoteCoracao);
                    animalDAO.criar(novoAnimal);
                    break;

                case 2:
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\n==== Listando Animais ====" + ConsoleColors.RESET);
                    List<Animal> listaAnimais = animalDAO.listarTodos();
                    if(listaAnimais.isEmpty()){
                        System.out.println("Nenhum Animal cadastrado.");
                    } else {
                        for (Animal animal : listaAnimais){
                            System.out.println(ConsoleColors.WHITE_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD_BRIGHT + animal + ConsoleColors.RESET);
                        }
                    }
                    break;

                case 3:
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\n==== Excluindo Animal ====" + ConsoleColors.RESET);
                    System.out.println("Informe o Id do animal a ser excluido:");
                    int idExcluir = entrada.nextInt();
                    animalDAO.excluir(idExcluir);
                    break;

                case 4: {
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\n==== Atualizando Animal ====" + ConsoleColors.RESET);
                    System.out.println("Informe o Id do animal a ser atualizado:");
                    int idAtualizar = entrada.nextInt();

                    System.out.println("Novo id :");
                    int novoIdAnimal = entrada.nextInt();
                    entrada.nextLine(); //consumir o enter após inserir o Id

                    System.out.println("Novo Nome :");
                    String novoNomeAnimal = entrada.nextLine();

                    System.out.println("Nova Espécie :");
                    String novoEspecieAnimal = entrada.nextLine();

                    System.out.println("Nova Raça :");
                    String novoRacaAnimal = entrada.nextLine();

                    System.out.println("Nova Idade :");
                    int novoIdadeAnimal = entrada.nextInt();
                    entrada.nextLine(); //consumir o enter após inserir a idade

                    System.out.println("Novo Sexo 'M' ou 'F' : ");
                    char novoSexoAnimal = entrada.nextLine().toUpperCase().charAt(0);

                    System.out.println("Novo Status do animal :");
                    String novoStatusAnimal = entrada.nextLine();

                    System.out.println("Nova Foto :");
                    String novoFotoAnimal = entrada.nextLine();

                    System.out.println("Animal de resgate ? 'S' ou 'N' :");
                    char novoResgateAnimal = entrada.nextLine().toUpperCase().charAt(0);

                    // Declarrei a variável antes, pois ela pode ou não ser preenchida dependendo da resposta de cima
                    String novoResgateDate  = "";
                    String novoResgateLocal = "";

                    if (novoResgateAnimal == 'S') {
                        System.out.println("Data do Resgate: ");
                        novoResgateDate = entrada.nextLine();
                        System.out.println("Local onde ocorreu o resgate:");
                        novoResgateLocal = entrada.nextLine();
                    }

                    System.out.println("O animal é idoso? 'S' ou 'N':");
                    char novoAdoteCoracao = entrada.nextLine().toUpperCase().charAt(0);

                    // Garantir que a resposta seja apenas 'S' ou 'N'
                    if (novoAdoteCoracao != 'S' && novoAdoteCoracao != 'N') {
                        System.out.println("Opção inválida, Definir como 'N' por padrão.");
                        novoAdoteCoracao = 'N';
                    }
                    Animal animalAtualizado = new Animal(novoIdAnimal, novoNomeAnimal, novoEspecieAnimal, novoRacaAnimal, novoIdadeAnimal, novoSexoAnimal,
                            novoStatusAnimal, novoFotoAnimal, novoResgateDate, novoResgateLocal,novoAdoteCoracao);
                    animalDAO.atualizar(idAtualizar, animalAtualizado);
                    break;
                }
                case 0:
                    System.out.println("Retornando ao menu principal...");
                    return;
                default:
                    System.out.println("Selecione uma opcao valida");
            }
        } while (opcao!=0);
        entrada.close();

    }
}