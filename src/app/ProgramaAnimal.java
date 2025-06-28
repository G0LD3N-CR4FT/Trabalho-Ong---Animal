package app;

import dao.AnimalDAO;
import dao.HistoricoMedicoDAO;
import modelo.Animal;
import modelo.HistoricoMedico;

import java.util.List;
import java.util.Scanner;

public class ProgramaAnimal {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        AnimalDAO animalDAO = new AnimalDAO();
        int opcao;

        do{
            System.out.println("\n ======= MENU ANIMAL =======");
            System.out.println("1. Inserir Animal");
            System.out.println("2. Listar Animal");
            System.out.println("3. Excluir Animal");
            System.out.println("4. Atualizar Animal");
            System.out.println("5. Gerenciar Histórico Médico");
            System.out.println("0. Sair");
            System.out.println("Escolha uma opção: ");
            opcao = entrada.nextInt();
            entrada.nextLine(); // consumir o caracter de quebra de linha (ENTER)

            switch (opcao){
                case 1:
                    System.out.println("\n==== Inserindo Animal ====");

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
                        System.out.println("Opção inválida, Definir como 'N' por padrão.");
                        adoteCoracao = 'N';
                    }

                    Animal novoAnimal = new Animal(idAnimal,nomeAnimal,especieAnimal,racaAnimal,idadeAnimal,sexoAnimal,
                            statusAnimal,fotoAnimal,resgateDate,resgateLocal,adoteCoracao);
                    animalDAO.criar(novoAnimal);
                    break;

                case 2:
                    System.out.println("\n==== Listando Animais ====");
                    List<Animal> listaAnimais = animalDAO.listarTodos();
                    if(listaAnimais.isEmpty()){
                        System.out.println("Nenhum Animal cadastrado.");
                    } else {
                        for (Animal animal : listaAnimais){
                            System.out.println(animal);
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n==== Excluindo Animal ====");
                    System.out.println("Informe o nome do animal a ser excluido:");
                    String nomeExcluir = entrada.nextLine();
                    animalDAO.excluir(nomeExcluir);
                    break;

                case 4: {
                    System.out.println("\n==== Atualizando Animal ====");
                    System.out.println("Informe o nome do animal a ser atualizado:");
                    String nomeAtualizar = entrada.nextLine();

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
                    animalDAO.atualizar(nomeAtualizar, animalAtualizado);
                    break;
                }
                case 5: {
                    int opcaoHistorico;
                    HistoricoMedicoDAO historicoDAO = new HistoricoMedicoDAO(); // cria DAO

                    do {
                        System.out.println("\n====== HISTÓRICO MÉDICO ======");
                        System.out.println("1. Inserir Histórico Médico");
                        System.out.println("2. Listar Históricos Médicos");
                        System.out.println("3. Excluir Histórico Médico");
                        System.out.println("4. Atualizar Histórico Médico");
                        System.out.println("0. Voltar ao Menu Principal");
                        System.out.print("Escolha uma opção: ");
                        opcaoHistorico = entrada.nextInt();
                        entrada.nextLine(); // limpar o Enter
                        System.out.println();

                        switch (opcaoHistorico) {
                            case 1:
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
                                System.out.println("\n==== Listando Histórico Médico ====");
                                List<HistoricoMedico> listaHistoricos = historicoDAO.listarTodos();
                                if (listaHistoricos.isEmpty()) {
                                    System.out.println("Nenhum histórico médico cadastrado.");
                                } else {
                                    for (HistoricoMedico h : listaHistoricos) {
                                        System.out.println(h);
                                    }
                                }
                                break;
                            case 3:
                                System.out.println("\n==== Excluindo Histórico Médico ====");
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
                                System.out.println("\n==== Atualizando Histórico Médico ====");
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
                                break;
                            default:
                                System.out.println("Opção inválida!");
                        }

                    } while (opcaoHistorico != 0);
                    break;
                }
                case 0:
                    System.out.println("Saindo do sistema ...");
                    break;
                default:
                    System.out.println("Selecione uma opção válida");
            }
        } while (opcao!=0);
        entrada.close();

    }
}