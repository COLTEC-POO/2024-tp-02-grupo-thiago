package Livraria;

import java.util.Scanner;

public class Biblioteca {
    private static Livros[] livros = new Livros[1000];
    private static Usuario[] usuarios;
    private static String nome;
    private static int maxLivros = 1000;
    private int qtdLivros;

    private static Biblioteca instance = null;

    private Biblioteca(String nome) {
        Biblioteca.nome = nome;
        this.qtdLivros = 0;
        this.usuarios = new Usuario[100];
        this.livros[0] = Livros.cadastrarLivro(1, "sla", "sla", 2006, "sla", "sla", 5);
        this.livros[1] = Livros.cadastrarLivro(2, "sla2", "sla2", 2003, "sla2", "sla2", 1);
        this.usuarios[0] = Usuario.cadastrar("gabriel", "1", "15/12/2006", 1, 2);
        this.usuarios[1] = Usuario.cadastrar("gabriel2", "1", "15/12/2006", 2, 2);
        this.usuarios[2] = Usuario.cadastrar("gabriel3", "1", "15/12/2006", 3, 2);
    }

    public static void linha() {
        System.out.println(
                "-------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static Biblioteca getInstance(String nome) {
        if (instance == null) {
            instance = new Biblioteca(nome);
        }
        return instance;
    }

    public static void main(String[] args) {
        Biblioteca biblioteca = Biblioteca.getInstance("Biblioteca da UFABC");

        bemVindoABiblioteca();
        while (true) {
            executarMenu();
        }
    }

    public static void bemVindoABiblioteca() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Seja Bem - Vindo ao Sistema da Livraria!");
        System.out.println("Este é um serviço oferecido pela " + nome);
        System.out.println("----------------------------------------------------------------");
    }

    public static int escolhaOpcao() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Cadastrar Usuário");
        System.out.println("2 - Cadastrar Livro");
        System.out.println("3 - Exibir Usuários");
        System.out.println("4 - Exibir Livros da Biblioteca");
        System.out.println("5 - Exibir Livros de um Usuário");
        System.out.println("6 - Empréstimo de Livro");
        System.out.println("7 - Devolver Livro");
        System.out.println("0 - Sair");
        return scan.nextInt();
    }

    public static void executarMenu() {
        int opcao = 0;
        while (opcao != 6) {
            opcao = escolhaOpcao();
            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    cadastrarLivro();
                    break;
                case 3:
                    exibirUsuarios();
                    break;
                case 4:
                    exibirLivros();
                    break;
                case 5:
                    exibirLivrosPorUsuario();
                    break;
                case 6:
                    emprestimoDeLivros();
                    break;
                case 7:
                    devolverLivro();
                    break;
                case 0:
                    System.out.println("Obrigado por utilizar o sistema da Livraria!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static void cadastrarUsuario() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Escolha o tipo de usuário:");
        System.out.println("1 - Aluno");
        System.out.println("2 - Professor");
        System.out.println("3 - Morador");
        int tipo = scan.nextInt();
        Usuario usuario = null;
        switch (tipo) {
            case 1:
                usuario = Aluno.cadastrar();
                break;
            case 2:
                usuario = Professor.cadastrar();
                break;
            case 3:
                usuario = Morador.cadastrar();
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] == null) {
                usuarios[i] = usuario;
                System.out.println("Usuário cadastrado com sucesso!");
                try {
                    Thread.sleep(1300);
                } catch (InterruptedException e) {
                    System.out.println("Ocorreu um erro ao tentar pausar a thread.");
                }
                for (int j = 0; j < 100; j++) {
                    System.out.println();
                }
                break;
            }
        }
    }

    public static void cadastrarLivro() {
        Scanner scan = new Scanner(System.in);
        Livros livro = Livros.cadastrarLivro();

        for (int i = 0; i < maxLivros; i++) {
            if (livros[i] == null) {
                livros[i] = livro;
                System.out.println("Livro cadastrado com sucesso!");
                try {
                    Thread.sleep(1100); // pausa de 2 segundos
                } catch (InterruptedException e) {
                    System.out.println("Ocorreu um erro ao tentar pausar a thread.");
                }
                for (int j = 0; j < 100; j++) { // imprime 100 linhas em branco
                    System.out.println();
                }
                break;
            }
        }
    }

    public static void exibirLivros() {
        String formato = "|%1$-4s|%2$-20s|%3$-20s|%4$-15s|%5$-20s|%6$-20s|%7$-15s|%8$-15s|\n";
        System.out.println();
        linha();
        System.out.format(formato, "ID", "Nome", "Autor", "Publicação", "Editora", "Categoria", "Disponível",
                "Quantidade");
        linha();
        for (Livros livro : livros) {
            if (livro != null && livro.isDisponivel()) {
                livro.imprimir(formato);
            }
        }
        System.out.println();
    }

    public static void exibirUsuarios() {
        String formato = "|%1$-4s|%2$-20s|%3$-20s|%4$-15s|%5$-20s|%6$-20s|%7$-20s|\n";
        System.out.println();
        linha();
        System.out.format(formato, "ID", "Nome", "CPF", "Nascimento", "Nº Livros Alugados", "Escola", "Formação");
        linha();
        for (Usuario usuario : usuarios) {
            if (usuario != null) {
                usuario.imprimir(formato);
                linha();
            }
        }
        System.out.println();
    }

    public static void exibirLivrosPorUsuario() {
        String formato = "|%1$-4s|%2$-20s|%3$-20s|%4$-15s|%5$-20s|%6$-20s|\n";
        Scanner scan = new Scanner(System.in);
        int id;

        System.out.print("ID do Usuário: ");
        id = scan.nextInt();
        scan.nextLine();

        Usuario usuario = getUsuarioPorId(id);
        System.out.println();
        linha();
        System.out.format(formato, "ID", "Nome", "Autor", "Publicação", "Editora", "Categoria");
        linha();
        usuario.imprimirLivros(formato);
        System.out.println();
    }

    public static Usuario getUsuarioPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario != null && usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    public static void emprestimoDeLivros() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Digite o ID do usuário: ");
        int userId = scan.nextInt();
        scan.nextLine();
        Usuario usuario = getUsuarioPorId(userId);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }
        System.out.print("Digite o ID do livro que deseja emprestar: ");
        int bookId = scan.nextInt();
        scan.nextLine();
        for (Livros livro : livros) {
            if (livro != null && livro.getId() == bookId) {
                if (livro.isDisponivel()) {
                    usuario.rentLivro(bookId, livros);
                    return;
                } else {
                    System.out.println("Desculpe, este livro não está disponível no momento.");
                    return;
                }
            }
        }
        System.out.println("Livro não encontrado.");
    }

    public static void devolverLivro() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Digite o ID do usuário: ");
        int userId = scan.nextInt();
        scan.nextLine();
        Usuario usuario = getUsuarioPorId(userId);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }
        System.out.print("Digite o ID do livro que deseja devolver: ");
        int bookId = scan.nextInt();
        scan.nextLine();
        for (Livros livro : livros) {
            if (livro != null && livro.getId() == bookId) {
                usuario.retrieveLivro(bookId, livros);
                return;
            }
        }
        System.out.println("Livro não encontrado.");
    }
}