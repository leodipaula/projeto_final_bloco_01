import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import controller.ClienteController;
import model.Cliente;
import model.ClientePadrao;
import model.ClientePlus;
import model.ClientePremium;
import service.CarrinhoService;

public class Menu {
    static Scanner sc = new Scanner(System.in);
    static ClienteController clienteController = new ClienteController();
    static Map<Integer, CarrinhoService> carrinhos = new HashMap<>();

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        executarTestesIniciais(); // Testes automáticos

        while (true) {
            System.out.println("""
                    ============================================
                        Você é um Cliente ou um Administrador?

                        1 - Cliente
                        2 - Administrador
                        0 - Sair
                    ============================================
                    """);

            int perfil = sc.nextInt();
            sc.nextLine();

            switch (perfil) {
                case 1 -> menuCliente();
                case 2 -> menuAdministrador();
                case 0 -> {
                    System.out.println("Encerrando o programa...");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }


    private static void menuCliente() {
        System.out.println(MENSAGEM);
        int opcao = sc.nextInt();
        sc.nextLine();

        System.out.print("\nDigite o seu nome: ");
        String nome = sc.nextLine();

        System.out.print("\nDigite sua idade: ");
        int idade = sc.nextInt();
        sc.nextLine();

        System.out.print("\nDigite seu email: ");
        String email = sc.nextLine();

        System.out.println("\nDigite seu telefone: ");
        String telefone = sc.nextLine();

        Cliente novoCliente;
        switch (opcao) {
            case 1 -> novoCliente = new ClientePadrao(nome, idade, email, telefone);
            case 2 -> novoCliente = new ClientePlus(nome, idade, email, telefone);
            case 3 -> novoCliente = new ClientePremium(nome, idade, email, telefone);
            default -> {
                System.out.println("Opção inválida.");
                return;
            }
        }

        clienteController.cadastrarCliente(novoCliente);
        CarrinhoService carrinho = new CarrinhoService();
        carrinhos.put(novoCliente.getId(), carrinho);

        keyPress();

        boolean continuar = true;
        while (continuar) {
            System.out.println(MENSAGEM_USUARIO);

            int acao = sc.nextInt();
            sc.nextLine();

            switch (acao) {
                case 1 -> {
                    System.out.println(MENSAGEMCOMPRODUTOS);
                    int cod = sc.nextInt();
                    System.out.print("Digite a nova quantidade: ");
                    int qtd = sc.nextInt();
                    carrinho.adicionarOuAtualizarItem(cod, qtd);
                    System.out.println("Produto adicionado/atualizado!");
                    keyPress();
                }
                case 2 -> {
                    System.out.print("Digite o código do produto para remover: ");
                    int cod = sc.nextInt();
                    carrinho.removerItem(cod);
                    System.out.println("Removido!");
                    keyPress();
                }
                case 3 -> {
                    carrinho.listarItens();
                    keyPress();
                }
                case 4 -> {
                    carrinho.listarItens();
                    System.out.println("Compra finalizada. Obrigado!");
                    carrinho.limparCarrinho();
                    continuar = false;
                    keyPress();
                }
                case 0 -> continuar = false;
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void menuAdministrador() {
        boolean adminLoop = true;

        while (adminLoop) {
            System.out.println("""
                    ================= Menu do Administrador =================

                    1 - Listar todos os clientes
                    2 - Buscar cliente por ID
                    3 - Atualizar cliente
                    4 - Deletar cliente
                    0 - Voltar

                    =========================================================
                    """);

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> clienteController.listarClientes();
                case 2 -> {
                    System.out.print("Digite o ID do cliente: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    clienteController.buscarPorId(id);
                }
                case 3 -> {
                    System.out.print("Novo nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Nova idade: ");
                    int idade = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Novo email: ");
                    String email = sc.nextLine();

                    System.out.print("Novo telefone: ");
                    String telefone = sc.nextLine();

                    Cliente clienteAtualizado = new ClientePadrao(nome, idade, email, telefone);
                    clienteController.atualizarCliente(clienteAtualizado);
                }
                case 4 -> {
                    System.out.print("Digite o ID do cliente para deletar: ");
                    int id = sc.nextInt();
                    clienteController.deletarCliente(id);
                }
                case 0 -> adminLoop = false;
                default -> System.out.println("Opção inválida.");
            }
            keyPress();
        }
    }

    public static void sobre() {
        String sobre = """
                \n\n********************************************************************
                Esse projeto foi feito individualmente por Leonardo de Paula.
                Meus contatos:

                leofernandes9@gmail.com
                https://github.com/leodipaula

                ********************************************************************\n\n
                """;
        System.out.println(sobre);
    }

    public static void keyPress() {
        try {
            System.out.println("\n\nPressione Enter para Continuar...");
            System.in.read();
        } catch (IOException e) {
            System.out.println("Você pressionou uma tecla diferente de enter: " + e.getCause());
        }
    }

    static final String MENSAGEM_USUARIO = """
            \n\n%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            O que deseja fazer agora?

            1 - Adicionar/Atualizar produto
            2 - Remover produto
            3 - Ver carrinho
            4 - Finalizar compra
            0 - Sair
            %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

            """;

    static final String MENSAGEM = """
            \n*****************************************************************************
                                    Boas vindas ao e-legal!

                Aqui proporcionamos uma experiência inovadora aos nossos clientes.
            *****************************************************************************

                Primeiro vamos fazer o seu cadastro.

                Escolha uma das seguintes opções:

                1 - Cliente Padrão: você tem acesso a somente um dos nossos jogos promocionais.
                2 - Cliente Plus: você tem acesso a dois jogos promocionais.
                3 - Cliente Premium: você tem acesso a 3 jogos promocionais e frete grátis.

                Sua escolha:
            """;

    static final String MENSAGEMCOMPRODUTOS =
            """
                    \n\n#######################################################################################

                            Ainda somos uma empresa nova, então só temos as seguintes opções de produtos:

                        1 - Camiseta da Saudade (homenageie seu amigo que foi jogar no vasco) => R$100,00
                        2 - Compre uma imagem aleatória de um gato => R$10,00
                        3 - Compre uma imagem aleatória de um cachorro => R$10,00

                        Escolha uma das opções acima.

                                                Agradecemos a compreensão!
                    #######################################################################################
                    """;

    private static void executarTestesIniciais() {
        System.out.println("========== Executando Testes Iniciais ==========");

        Cliente clienteTeste1 =
                new ClientePadrao("João sobrenome", 25, "joao@gmail.com", "123456789");
        Cliente clienteTeste2 =
                new ClientePlus("Maria sobrenome", 30, "maria@gmail.com", "987654321");
        Cliente clienteTeste3 =
                new ClientePremium("Carlos sobrenome", 35, "carlos@gmail.com", "456789123");

        clienteController.cadastrarCliente(clienteTeste1);
        clienteController.cadastrarCliente(clienteTeste2);
        clienteController.cadastrarCliente(clienteTeste3);

        CarrinhoService carrinhoTeste1 = new CarrinhoService();
        CarrinhoService carrinhoTeste2 = new CarrinhoService();
        CarrinhoService carrinhoTeste3 = new CarrinhoService();

        carrinhos.put(clienteTeste1.getId(), carrinhoTeste1);
        carrinhos.put(clienteTeste2.getId(), carrinhoTeste2);
        carrinhos.put(clienteTeste3.getId(), carrinhoTeste3);

        carrinhoTeste1.adicionarOuAtualizarItem(1, 2); // João comprou 2 camisetas
        carrinhoTeste2.adicionarOuAtualizarItem(2, 1); // Maria comprou 1 imagem de gato
        carrinhoTeste2.adicionarOuAtualizarItem(3, 2); // Maria comprou 2 imagens de cachorro
        carrinhoTeste3.adicionarOuAtualizarItem(1, 1); // Carlos comprou 1 camiseta
        carrinhoTeste3.adicionarOuAtualizarItem(2, 1); // Carlos comprou 1 imagem de gato
        carrinhoTeste3.adicionarOuAtualizarItem(3, 1); // Carlos comprou 1 imagem de cachorro

        System.out.println("\nClientes cadastrados:");
        clienteController.listarClientes();

        System.out.println("\nCarrinho de João:");
        carrinhoTeste1.listarItens();

        System.out.println("\nCarrinho de Maria:");
        carrinhoTeste2.listarItens();

        System.out.println("\nCarrinho de Carlos:");
        carrinhoTeste3.listarItens();

        System.out.println("\n========== Testes Iniciais Finalizados ==========\n");
    }

}
