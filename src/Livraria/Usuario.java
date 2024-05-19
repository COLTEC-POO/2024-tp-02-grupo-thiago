package Livraria;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class Usuario {
    protected String nome, cpf;
    protected Date data;
    protected int id, maxLivros, nLivrosAlugados;
    protected Livros[] livrosAlugados;

    Usuario(String nome, String cpf, Date data, int id, int maxLivros) {
        this.nome = nome;
        this.cpf = cpf;
        this.data = data;
        this.id = id;
        this.maxLivros = maxLivros;
        this.livrosAlugados = new Livros[maxLivros];
        this.nLivrosAlugados = 0;
    }

    Usuario() {
    }

    public int getNLivrosAlugados() {
        return this.nLivrosAlugados;
    }

    public int getMaxLivros() {
        return this.maxLivros;
    }

    public Date getData() {
        return data;
    }

    public int getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void retrieveLivro(int id, Livros[] livros) {
        for (int i = 0; i < Livros.getNLivros(); ++i) {
            if (livros[i].getId() == id) {
                if (nLivrosAlugados != 0) {
                    int k = 0;

                    Livros[] aux = new Livros[livrosAlugados.length];
                    for (int j = 0; j < nLivrosAlugados; ++j) {
                        if (livrosAlugados[j].getId() != id) {
                            aux[k] = livrosAlugados[j];
                            ++k;
                        }
                    }
                    livrosAlugados = aux;
                    livros[i].setQuantidade(livros[i].getQuantidade() + 1);
                    livros[i].setDisponivel(true);
                    --nLivrosAlugados;
                }
            }
        }
    }

    public void rentLivro(int id, Livros[] livros) {
        if (nLivrosAlugados >= maxLivros) {
            System.out.println("Você já atingiu o limite de empréstimos.");
            return;
        }
        System.out.println(Livros.getNLivros());
        for (int i = 0; i < Livros.getNLivros(); ++i) {
            if (livros[i].getId() == id && livros[i].isDisponivel()) {
                livros[i].setQuantidade(livros[i].getQuantidade() - 1);
                if (livros[i].getQuantidade() == 0)
                    livros[i].setDisponivel(false);
                livrosAlugados[nLivrosAlugados] = livros[i].getLivro();
                ++nLivrosAlugados;
                System.out.println("Livro alugado");
                return;
            }
        }
    }

    public static Usuario cadastrar(String nome, String cpf, String dateTemp, int id, int maxLivros) {
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            date = sdf.parse(dateTemp);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return new Usuario(nome, cpf, date, id, maxLivros);
    }

    public static Usuario cadastrar() {
        try {
            Scanner scan = new Scanner(System.in);
            String nome, cpf, dataTemp;
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

            return new Usuario(nome, cpf, data, id, 2);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void imprimirLivros(String formato) {
        for (int i = 0; i < nLivrosAlugados; ++i) {
            livrosAlugados[i].imprimir(formato);
        }
    }

    public void imprimir(String formato) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dateTemp = df.format(data);
        System.out.format(formato, id, nome, cpf, dateTemp, nLivrosAlugados, "N/A", "N/A");
    }
}
