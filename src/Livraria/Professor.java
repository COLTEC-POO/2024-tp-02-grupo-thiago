package Livraria;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Professor extends Usuario{
    private String formacao;


    Professor(String nome, String cpf, Date data, int id, String formacao){
        super(nome, cpf, data, id, 10);
        this.formacao = formacao;
    }

    public String getFormacao() {
        return formacao;
    }

    public static Professor cadastrar(){
        try {
            Scanner scan = new Scanner(System.in);
            String nome, cpf, dataTemp, formacao;
            int id;

            System.out.println("Nome: ");
            nome = scan.nextLine();

            System.out.println("CPF: ");
            cpf = scan.nextLine();

            System.out.println("Data (dd/MM/yyyy): ");
            dataTemp = scan.next();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date data = sdf.parse(dataTemp);

            System.out.println("ID: ");
            id = scan.nextInt();
            scan.nextLine();

            System.out.println("Formação: ");
            formacao = scan.nextLine();

            return new Professor(nome, cpf, data, id, formacao);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void imprimir(String formato){
        System.out.format(formato, id, nome, cpf, nLivrosAlugados, "N/A", formacao);
    }
}
