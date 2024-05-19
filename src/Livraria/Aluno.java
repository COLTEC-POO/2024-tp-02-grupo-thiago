package Livraria;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Aluno extends Usuario{
    private String escola;

    Aluno(String nome, String cpf, Date data, int id, String escola){
        super(nome, cpf, data, id, 5);
        this.escola = escola;
    }

    public String getEscola() {
        return escola;
    }

    public static Aluno cadastrar(){
        System.out.println("Cadastro do Aluno");
        System.out.println();
        try {
            Scanner scan = new Scanner(System.in);
            String nome, cpf, dataTemp, escola;
            int id;

            System.out.print("Nome: ");
            nome = scan.nextLine();

            System.out.print("CPF: ");
            cpf = scan.nextLine();

            System.out.print("Data (dd/MM/yyyy): ");
            dataTemp = scan.next();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date data = sdf.parse(dataTemp);

            System.out.print("ID: ");
            id = scan.nextInt();
            scan.nextLine();

            System.out.print("Escola: ");
            escola = scan.nextLine();

            return new Aluno(nome, cpf, data, id, escola);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void imprimir(String formato){
        System.out.format(formato, id, nome, cpf, nLivrosAlugados, escola, "N/A");
    }
}


