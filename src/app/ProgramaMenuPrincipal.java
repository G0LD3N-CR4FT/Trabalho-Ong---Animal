package app;

import dao.PessoaDAO;

import java.util.Scanner;

import static app.ProgramaMenuAdotante.menuAdotante;
import static app.ProgramaMenuAnimal.menuAnimal;
import static app.ProgramaMenuHistoricoMedico.menuHistorico;
import static app.ProgramaMenuPessoa.menuPessoa;

public class ProgramaMenuPrincipal {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        PessoaDAO pessoaDAO  = new PessoaDAO();
        int op;

        do {
            System.out.println("\n ========= MENU PRINCIPAL =========");
            System.out.println("1. Menu Animal");
            System.out.println("2. Histórico Médico");
            System.out.println("3. Menu Pessoa");
            System.out.println("4. Menu Adotante");
            System.out.println("5. Menu Voluntario");
            System.out.println("0. Sair");

            System.out.println("Escolha uma opcao: ");
            op = teclado.nextInt();

            teclado.nextLine();

            switch (op){
                case 1:
                    menuAnimal();
                    break;
                case 2:
                    menuHistorico();
                    break;
                case 3:
                    menuPessoa();
                    break;
                case 4:
                    menuAdotante();
                    break;
                case 0:
                    System.out.println("Saindo do Sistema.... ");
                    break;
                default:
                    System.out.println("Selecione uma opcao valida");
            }
        } while (op != 0);
        // fechando o teclado
        teclado.close();
    }
}
