package app;

import colors.ConsoleColors;
import dao.PessoaDAO;
import modelo.Pessoa;
import dao.AdotanteDAO;
import modelo.Adotante;

import java.util.List;
import java.util.Scanner;

public class ProgramaMenuAdotante{
    public static void menuAdotante(){
            Scanner teclado = new Scanner(System.in);
            AdotanteDAO adotanteDAO  = new AdotanteDAO();
            int op;

            do {
                System.out.println(ConsoleColors.YELLOW_BOLD + "\n========= MENU ADOTANTE =========" + ConsoleColors.RESET);
                System.out.println("1. Inserir Adotante");
                System.out.println("2. Listar Adotante");
                System.out.println("3. Excluir Adotante");
                System.out.println("4. Atualizar Adotante");
                System.out.println("0. Voltar ao Menu Principal");
                System.out.println(ConsoleColors.YELLOW_BOLD + "=================================" + ConsoleColors.RESET);
                System.out.println("Escolha uma opcao: ");
                op = teclado.nextInt();

                teclado.nextLine();

                switch (op){
                    case 1:
                        System.out.println("\n====== Inserindo Adotante ======");

                        // ID adotante
                        System.out.println("ID Adotante: ");
                        int idAdotanteInserir = teclado.nextInt();

                        // Limpando o buffer
                        teclado.nextLine();

                        // Nome
                        System.out.println("Nome: ");
                        String nomeInserir = teclado.nextLine();

                        // Email
                        System.out.println("Email: ");
                        String emailInserir = teclado.nextLine();

                        // telefone
                        System.out.println("Telefone: ");
                        String telefoneInserir = teclado.nextLine();

                        // questionário
                        System.out.println("Questionário: ");
                        String questionarioInserir = teclado.nextLine();

                        Adotante novoAdotante = new Adotante(
                                nomeInserir,
                                telefoneInserir,
                                emailInserir,
                                idAdotanteInserir,
                                questionarioInserir
                        );

                        adotanteDAO.criar(novoAdotante);

                        break;
                    case 2:
                        System.out.println("\n====== Listando Adotantes ======");
                        List<Adotante> listaAdotantes = adotanteDAO.listarTodos();

                        if(listaAdotantes.isEmpty()){
                            System.out.println("Nenhum adotante cadastrado");
                        } else {
                            for (Pessoa contato: listaAdotantes){
                                System.out.println(contato);
                            }
                        }
                        break;
                    case 3:
                        System.out.println("\n====== Excluindo Adotante ======");

                        System.out.println("Informa o ID do adotante a ser excluido: ");
                        int idAdotanteExcluir = teclado.nextInt();

                        adotanteDAO.excluir(idAdotanteExcluir);
                        break;
                    case 4:
                        System.out.println("\n====== Atualizando Adotante ======");

                        //id adotante para poder atualizar
                        System.out.println("Informa o ID adotante a ser atualizado: ");
                        int idAdotanteAtualizar = teclado.nextInt();

                        // ID adotante
                        System.out.println("Novo ID Adotante: ");
                        int novoIdAdotanteInserir = teclado.nextInt();

                        // Limpando o buffer
                        teclado.nextLine();

                        // Nome
                        System.out.println("Novo Nome: ");
                        String novoNomeInserir = teclado.nextLine();

                        // Email
                        System.out.println("Novo Email: ");
                        String novoEmailInserir = teclado.nextLine();

                        // telefone
                        System.out.println("Novo Telefone: ");
                        String novoTelefoneInserir = teclado.nextLine();

                        // questionário
                        System.out.println("Novo Questionário: ");
                        String novoQuestionarioInserir = teclado.nextLine();

                        // Criando o novo Objeto atualizado de Agenda
                        Adotante adotanteAtualizado = new Adotante(
                                novoNomeInserir,
                                novoEmailInserir,
                                novoTelefoneInserir,
                                novoIdAdotanteInserir,
                                novoQuestionarioInserir
                        );

                        // Atualizando
                        adotanteDAO.atualizar(idAdotanteAtualizar, adotanteAtualizado);
                        break;
                    case 0:
                        System.out.println("Retornando ao menu principal...");
                        return;
                    default:
                        System.out.println("Selecione uma opcao valida");
                }
            } while (op != 0);
            // fechando o teclado
            teclado.close();
        }

}//fim da classe ProgramaMenuAdotante
