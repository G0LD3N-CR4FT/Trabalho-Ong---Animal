package app;

import dao.PessoaDAO;
import modelo.Pessoa;

import java.util.List;
import java.util.Scanner;

public class ProgramaMenuPessoa {
    public static void menuPessoa() {
        Scanner teclado = new Scanner(System.in);

        PessoaDAO pessoaDAO  = new PessoaDAO();
        int op;

        do {
            System.out.println("\n ========= MENU PESSOA =========");
            System.out.println("1. Inserir Pessoa");
            System.out.println("2. Listar Pessoas");
            System.out.println("3. Excluir Pessoa");
            System.out.println("4. Atualizar Pessoa");
            System.out.println("0. Sair");

            System.out.println("Escolha uma opcao: ");
            op = teclado.nextInt();

            teclado.nextLine();

            switch (op){
                case 1:
                    System.out.println("\n====== Inserindo Pessoa ======");

                    // Id
                    System.out.println("ID: ");
                    int idInserir = teclado.nextInt();

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

                    Pessoa novaPessoa = new Pessoa(
                            idInserir,
                            nomeInserir,
                            telefoneInserir,
                            emailInserir
                    );

                    pessoaDAO.criar(novaPessoa);

                    break;
                case 2:
                    System.out.println("\n====== Listando os Pessoas ======");
                    List<Pessoa> listaContatos = pessoaDAO.listarTodos();

                    if(listaContatos.isEmpty()){
                        System.out.println("Nenhuma pessoa cadastrada");
                    } else {
                        for (Pessoa contato: listaContatos){
                            System.out.println(contato);
                        }
                    }
                    break;
                case 3:
                    System.out.println("\n====== Excluindo Pessoa ======");

                    System.out.println("Informa o ID da pessoa a ser excluida: ");
                    int idPessoaExcluir = teclado.nextInt();

                    pessoaDAO.excluir(idPessoaExcluir);
                    break;
                case 4:
                    System.out.println("\n====== Atualizando Pessoa ======");


                    System.out.println("Informa o ID da pessoa a ser atualizada: ");
                    int idPessoaAtualizar = teclado.nextInt();

                    System.out.println("Informe o novo id: ");
                    int idPessoa = teclado.nextInt();

                    // Novo Nome
                    System.out.println("Informe o novo nome: ");
                    String novoNome = teclado.nextLine();

                    // Novo Email
                    System.out.println("Informe um novo Email: ");
                    String novoEmail = teclado.nextLine();

                    // Novo telefone
                    System.out.println("informe um novo telefone: ");
                    String novoTelefone = teclado.nextLine();

                    // Criando o novo Objeto atualizado de Agenda
                    Pessoa pessoaAtualizado = new Pessoa(
                            idPessoa,
                            novoNome,
                            novoTelefone,
                            novoEmail
                    );

                    // Atualizando
                    pessoaDAO.atualizar(idPessoaAtualizar, pessoaAtualizado);
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
}
