package app;

import modelo.Voluntario;
import dao.VoluntarioDAO;
import dao.PessoaDAO;
import modelo.Pessoa;
import java.util.List;
import java.util.Scanner;

public class ProgramaMenuVoluntario{
    public static void menuVoluntario(){
        Scanner teclado = new Scanner(System.in);
        VoluntarioDAO voluntarioDAO  = new VoluntarioDAO();
        int op;

        do {
            System.out.println("\n ========= MENU VOLUNTÁRIO =========");
            System.out.println("1. Inserir Voluntário");
            System.out.println("2. Listar Voluntário");
            System.out.println("3. Excluir Voluntário");
            System.out.println("4. Atualizar Voluntário");
            System.out.println("0. Sair");

            System.out.println("Escolha uma opcao: ");
            op = teclado.nextInt();

            teclado.nextLine();

            switch (op){
                case 1:
                    System.out.println("\n====== Inserindo Voluntário ======");

                    // Nome
                    System.out.println("Nome: ");
                    String nomeInserir = teclado.nextLine();

                    // Email
                    System.out.println("Email: ");
                    String emailInserir = teclado.nextLine();

                    // telefone
                    System.out.println("Telefone: ");
                    String telefoneInserir = teclado.nextLine();

                    // ID adotante
                    System.out.println("ID Voluntário: ");
                    int idAdotanteInserir = teclado.nextInt();

                    // Limpando o buffer
                    teclado.nextLine();

                    // disponibilidade
                    System.out.println("Disponibilidade: ");
                    String disponibilidadeInserir = teclado.nextLine();

                    // habilidades
                    System.out.println("Habilidades: ");
                    String habilidadesInserir = teclado.nextLine();

                    Voluntario novoVoluntario = new Voluntario(
                            nomeInserir,
                            telefoneInserir,
                            emailInserir,
                            idAdotanteInserir,
                            disponibilidadeInserir,
                            habilidadesInserir
                    );

                    voluntarioDAO.criar(novoVoluntario);

                    break;
                case 2:
                    System.out.println("\n====== Listando Voluntários ======");
                    List<Voluntario> listaVoluntario = voluntarioDAO.listarTodos();

                    if(listaVoluntario.isEmpty()){
                        System.out.println("Nenhum adotante cadastrado");
                    } else {
                        for (Pessoa contato: listaVoluntario){
                            System.out.println(contato);
                        }
                    }
                    break;
                case 3:
                    System.out.println("\n====== Excluindo Voluntários ======");

                    System.out.println("Informa o ID do voluntário a ser excluido: ");
                    int idVoluntarioExcluir = teclado.nextInt();

                    voluntarioDAO.excluir(idVoluntarioExcluir);
                    break;
                case 4:
                    System.out.println("\n====== Atualizando Voluntário ======");

                    //id Voluntário para poder atualizar
                    System.out.println("Informa o ID Voluntário a ser atualizado: ");
                    int idVoluntarioAtualizar = teclado.nextInt();


                    // Nome
                    System.out.println("Novo Nome: ");
                    String novoNomeInserir = teclado.nextLine();

                    // Email
                    System.out.println("Novo Email: ");
                    String novoEmailInserir = teclado.nextLine();

                    // telefone
                    System.out.println("Novo Telefone: ");
                    String novoTelefoneInserir = teclado.nextLine();

                    // ID adotante
                    System.out.println("Novo ID Voluntário: ");
                    int novoIdVoluntarioInserir = teclado.nextInt();

                    // Limpando o buffer
                    teclado.nextLine();

                    // Disponibilidade
                    System.out.println("Novo Disponibilidade: ");
                    String novoDisponibilidadeInserir = teclado.nextLine();

                    // Habilidades
                    System.out.println("Novo Habilidades: ");
                    String novoHabilidadesInserir = teclado.nextLine();

                    // Criando o novo Objeto atualizado de Agenda
                    Voluntario voluntarioAtualizado = new Voluntario(
                            novoNomeInserir,
                            novoEmailInserir,
                            novoTelefoneInserir,
                            novoIdVoluntarioInserir,
                            novoDisponibilidadeInserir,
                            novoHabilidadesInserir
                    );

                    // Atualizando
                    voluntarioDAO.atualizar(idVoluntarioAtualizar, voluntarioAtualizado);
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
