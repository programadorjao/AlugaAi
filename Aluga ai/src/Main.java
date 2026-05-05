import model.Categoria;
import model.CategoriaService;
import model.Produto;
import model.Usuario;
import model.Aluguel;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        
        CategoriaService categoriaService = new CategoriaService();
        ArrayList<Produto> produtos = new ArrayList<>();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Aluguel> alugueis = new ArrayList<>();

    
        categoriaService.adicionarCategoria("Automóveis");
        categoriaService.adicionarCategoria("Imóveis");
        categoriaService.adicionarCategoria("Casa e Decoração");
        categoriaService.adicionarCategoria("Móveis");
        categoriaService.adicionarCategoria("Construção");
        categoriaService.adicionarCategoria("Eletro");
        categoriaService.adicionarCategoria("Celulares");
        categoriaService.adicionarCategoria("Informática");
        categoriaService.adicionarCategoria("Games");
        categoriaService.adicionarCategoria("Tvs");
        categoriaService.adicionarCategoria("Agro");
        categoriaService.adicionarCategoria("Serviços");

        Usuario usuarioLogado = null;

        int opcao = 0;

        do {
            System.out.println("\n==============================");
            System.out.println("         ALUGA AÍ             ");
            System.out.println("==============================");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Login");
            System.out.println("3 - Anunciar Produto");
            System.out.println("4 - Ver Produtos Disponíveis");
            System.out.println("5 - Alugar Produto");
            System.out.println("6 - Gerenciar Meus Aluguéis");
            System.out.println("7 - Editar Meus Dados");
            System.out.println("8 - Categorias");
            System.out.println("9 - Sair");
            System.out.println("==============================");
            System.out.print("Escolha uma opção: ");
            opcao = input.nextInt();
            input.nextLine(); // limpa o buffer

            switch (opcao) {


                case 1:
                    System.out.println("\n--- Cadastro de Usuário ---");
                    System.out.print("Nome: ");
                    String nome = input.nextLine();
                    System.out.print("E-mail: ");
                    String email = input.nextLine();
                    System.out.print("Senha: ");
                    String senha = input.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = input.nextLine();

                    Usuario novoUsuario = new Usuario(nome, email, senha, telefone);
                    novoUsuario.verificarCadastro();
                    usuarios.add(novoUsuario);
                    break;

                case 2:
                    System.out.println("\n--- Login ---");
                    System.out.print("E-mail: ");
                    String emailLogin = input.nextLine();
                    System.out.print("Senha: ");
                    String senhaLogin = input.nextLine();

                    boolean logado = false;
                    for (Usuario u : usuarios) {
                        if (u.login(emailLogin, senhaLogin)) {
                            usuarioLogado = u;
                            logado = true;
                            break;
                        }
                    }

                    if (logado) {
                        System.out.println("Bem-vindo(a), " + usuarioLogado.getNome() + "!");
                    } else {
                        System.out.println("E-mail ou senha incorretos.");
                    }
                    break;


                case 3:
                    if (usuarioLogado == null) {
                        System.out.println("Você precisa fazer login primeiro.");
                        break;
                    }

                    System.out.println("\n--- Anunciar Produto ---");
                    System.out.print("Nome do produto: ");
                    String nomeProduto = input.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = input.nextLine();
                    System.out.print("Preço por dia (R$): ");
                    double preco = input.nextDouble();
                    input.nextLine();
                    System.out.print("Categoria: ");
                    String nomeCategoria = input.nextLine();

                    try {
                        Categoria cat = categoriaService.buscarPorNome(nomeCategoria);
                        Produto novoProduto = new Produto(nomeProduto, descricao, preco, cat);

                        if (novoProduto.produtoValido()) {
                            produtos.add(novoProduto);
                            System.out.println("Produto \"" + nomeProduto + "\" anunciado com sucesso! ID: " + novoProduto.getId());
                        } else {
                            System.out.println("Dados do produto inválidos. Verifique o nome e o preço.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;


                case 4:
                    System.out.println("\n--- Produtos Disponíveis ---");

                    if (produtos.isEmpty()) {
                        System.out.println("Nenhum produto cadastrado ainda.");
                        break;
                    }

                    boolean encontrouDisponivel = false;
                    for (Produto p : produtos) {
                        if (p.estaDisponivel()) {
                            System.out.println("---------------------------");
                            System.out.println("ID:        " + p.getId());
                            System.out.println("Nome:      " + p.getNome());
                            System.out.println("Descrição: " + p.getDescricao());
                            System.out.printf("Preço/dia: R$ %.2f%n", p.getPrecoPorDia());
                            System.out.println("Categoria: " + p.getCategoria().getNome());
                            encontrouDisponivel = true;
                        }
                    }

                    if (!encontrouDisponivel) {
                        System.out.println("Nenhum produto disponível no momento.");
                    }
                    break;


                case 5:
                    if (usuarioLogado == null) {
                        System.out.println("Você precisa fazer login primeiro.");
                        break;
                    }

                    System.out.println("\n--- Alugar Produto ---");
                    System.out.print("Digite o ID do produto que deseja alugar: ");
                    int idProduto = input.nextInt();
                    input.nextLine();

                    Produto produtoEscolhido = null;
                    for (Produto p : produtos) {
                        if (p.getId() == idProduto) {
                            produtoEscolhido = p;
                            break;
                        }
                    }

                    if (produtoEscolhido == null) {
                        System.out.println("Produto não encontrado.");
                        break;
                    }

                    Aluguel novoAluguel = new Aluguel(produtoEscolhido, usuarioLogado, null);
                    novoAluguel.solicitarAluguel(); // valida disponibilidade
                    alugueis.add(novoAluguel);
                    System.out.println("Solicitação de aluguel do produto \"" + produtoEscolhido.getNome() + "\" enviada! Aguardando aprovação.");
                    break;


                case 6:
                    if (usuarioLogado == null) {
                        System.out.println("Você precisa fazer login primeiro.");
                        break;
                    }

                    System.out.println("\n--- Gerenciar Aluguéis ---");
                    System.out.println("1 - Aprovar aluguel");
                    System.out.println("2 - Cancelar aluguel");
                    System.out.println("3 - Finalizar aluguel");
                    System.out.print("Opção: ");
                    int subOpcaoAluguel = input.nextInt();
                    input.nextLine();

                    System.out.print("Digite o índice do aluguel (começa em 0): ");
                    int idxAluguel = input.nextInt();
                    input.nextLine();

                    if (idxAluguel < 0 || idxAluguel >= alugueis.size()) {
                        System.out.println("Aluguel não encontrado.");
                        break;
                    }

                    Aluguel aluguelSelecionado = alugueis.get(idxAluguel);

                    switch (subOpcaoAluguel) {
                        case 1:
                            aluguelSelecionado.aprovarAluguel();
                            System.out.println("Aluguel aprovado com sucesso!");
                            break;
                        case 2:
                            aluguelSelecionado.cancelarAluguel();
                            System.out.println("Aluguel cancelado.");
                            break;
                        case 3:
                            aluguelSelecionado.finalizarAluguel();
                            System.out.println("Aluguel finalizado. Produto disponível novamente.");
                            break;
                        default:
                            System.out.println("Opção inválida.");
                    }
                    break;

                case 7:
                    if (usuarioLogado == null) {
                        System.out.println("Você precisa fazer login primeiro.");
                        break;
                    }

                    System.out.println("\n--- Editar Meus Dados ---");
                    System.out.println("1 - Atualizar nome e telefone");
                    System.out.println("2 - Alterar senha");
                    System.out.print("Opção: ");
                    int subOpcaoUsuario = input.nextInt();
                    input.nextLine();

                    if (subOpcaoUsuario == 1) {
                        System.out.print("Novo nome: ");
                        String novoNome = input.nextLine();
                        System.out.print("Novo telefone: ");
                        String novoTel = input.nextLine();
                        usuarioLogado.atualizarDados(novoNome, novoTel);
                        System.out.println("Dados atualizados com sucesso!");

                    } else if (subOpcaoUsuario == 2) {
                        System.out.print("Senha atual: ");
                        String senhaAtual = input.nextLine();
                        System.out.print("Nova senha: ");
                        String novaSenha = input.nextLine();
                        usuarioLogado.alterarSenha(senhaAtual, novaSenha);
                        System.out.println("Senha alterada com sucesso!");

                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;

                case 8:
                    System.out.println("\n--- Categorias ---");
                    System.out.println("1 - Adicionar categoria");
                    System.out.println("2 - Buscar categoria");
                    System.out.print("Opção: ");
                    int subOpcaoCategoria = input.nextInt();
                    input.nextLine();

                    if (subOpcaoCategoria == 1) {
                        System.out.print("Nome da nova categoria: ");
                        String novaCategoria = input.nextLine();
                        categoriaService.adicionarCategoria(novaCategoria);
                        System.out.println("Categoria \"" + novaCategoria + "\" adicionada!");

                    } else if (subOpcaoCategoria == 2) {
                        System.out.print("Nome da categoria: ");
                        String busca = input.nextLine();
                        try {
                            Categoria resultado = categoriaService.buscarPorNome(busca);
                            System.out.println("Categoria encontrada: " + resultado.getNome());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;

                case 9:
                    System.out.println("\nSaindo do Aluga Aí... Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 9);

        input.close();
    }
}
