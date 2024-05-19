package Livraria;

import java.util.Scanner;

public class Livros {

    private int id; //privando todas as variaveis
    private int anoDePublicacao = 0;
    private int quantidade = 0;
    private String titulo;
    private String autor;
    private String editora;
    private String categoria;
    private boolean disponivel = true;

    private static int nLivros = 0;

    Livros(int id, String titulo, String autor, int anoDePublicacao, String editora, String categoria, boolean disponivel, int quantidade) {
        this.id = id; //metodos construtores
        this.titulo = titulo;
        this.autor = autor;
        this.anoDePublicacao = anoDePublicacao;
        this.editora = editora;
        this.categoria = categoria;
        this.disponivel = disponivel;
        this.quantidade = quantidade;
    }

    public int getId(){
        return id;
    }
    public String getTitulo(){
        return titulo;
    }
    public String getAutor(){
        return autor;
    }
    public int getanoDePublicacao(){
        return anoDePublicacao;
    }
    public String getEditora(){
        return editora;
    }
    public String getCategoria(){
        return categoria;
    }
    public boolean isDisponivel(){
        return disponivel;
    }
    public int getQuantidade(){
        return quantidade;
    }
    public static int getNLivros(){
        return Livros.nLivros;
    }
    public Livros getLivro(){
        return new Livros(id, titulo, autor, anoDePublicacao, editora, categoria, disponivel, quantidade);
    }
    public void setId(int id){
        this.id = id;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }
    public void setanoDePublicacao(int anoDePublicacao){
        this.anoDePublicacao = anoDePublicacao;
    }
    public void setEditora(String editora){
        this.editora = editora;
    }
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
    public void setDisponivel(boolean disponivel){
        this.disponivel = disponivel;
    }
    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }
    public static void setNLivros(int nLivros){
        Livros.nLivros = nLivros;
    }

    public static Livros cadastrarLivro(int id, String titulo, String autor, int anoDePublicacao, String editora, String categoria, int quantidade){
        Livros.setNLivros(Livros.getNLivros()+1);
        return new Livros(id, titulo, autor, anoDePublicacao, editora, categoria, (quantidade!=0 ? true : false), quantidade);
    }

    public static Livros cadastrarLivro(){
        Scanner scan = new Scanner(System.in);

        int id, anoDePublicacao, quantidade;
        String titulo, autor, categoria, editora;

        System.out.print("ID: ");
        id = scan.nextInt();
        scan.nextLine();
        System.out.print("Título: ");
        titulo = scan.nextLine();
        System.out.print("Autor: ");
        autor = scan.nextLine();
        System.out.print("Categoria: ");
        categoria = scan.nextLine();

        do{
            System.out.print("Ano de Publicação: ");
            anoDePublicacao = scan.nextInt();
            scan.nextLine();
            if(anoDePublicacao<0) System.out.println("Ano Inválido");
        }while(anoDePublicacao<0);

        System.out.print("Editora: ");
        editora = scan.nextLine();

        do{
            System.out.print("Quantidade: ");
            quantidade = scan.nextInt();
            scan.nextLine();
            if(quantidade<0) System.out.println("Quantidade Inválida");
        }while(quantidade<0);

        Livros.setNLivros(Livros.getNLivros()+1);

        return new Livros(id, titulo, autor, anoDePublicacao, editora, categoria, (quantidade!=0 ? true : false), quantidade);
    }

    public void imprimir(String formato) {
        if(quantidade==0) disponivel = false;
        System.out.format(formato, id, titulo, autor, anoDePublicacao, editora, categoria, (disponivel ? "Sim" : "Não"), quantidade);
        Biblioteca.linha();
    }
}
