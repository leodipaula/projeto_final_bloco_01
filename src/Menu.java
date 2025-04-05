import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import controller.ClienteController;
import model.Cliente;
import model.ClientePadrao;
import model.ClientePlus;
import model.ClientePremium;
import service.ApiConsulta;
import service.CarrinhoService;
import service.JogoCacaNiquel;
import service.JogoMatematica;
import service.JogoSorteio;

public class Menu {
    static Scanner sc = new Scanner(System.in);
    static ClienteController clienteController = new ClienteController();
    static Map<Integer, CarrinhoService> carrinhos = new HashMap<>();

    public static void main(String[] args) {
        executarTestesIniciais();

        while (true) {
            System.out.println("\nVocê deseja acessar como:");
            System.out.println("1 - Admin");
            System.out.println("2 - Cliente");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            int escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1 -> menuAdmin();
                case 2 -> menuCliente();
                case 0 -> {
                    sobre();
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuAdmin() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- MENU ADMIN ---");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Listar clientes");
            System.out.println("3 - Buscar cliente por ID");
            System.out.println("4 - Atualizar cliente");
            System.out.println("5 - Remover cliente");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Idade: ");
                    int idade = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = sc.nextLine();
                    System.out.print("Tipo de cliente (1-Padrão, 2-Plus, 3-Premium): ");
                    int tipo = sc.nextInt();
                    sc.nextLine();

                    Cliente cliente;
                    switch (tipo) {
                        case 1 -> cliente = new ClientePadrao(nome, idade, email, telefone);
                        case 2 -> cliente = new ClientePlus(nome, idade, email, telefone);
                        case 3 -> cliente = new ClientePremium(nome, idade, email, telefone);
                        default -> {
                            System.out.println("Tipo inválido. Cliente não cadastrado.");
                            continue;
                        }
                    }
                    clienteController.cadastrarCliente(cliente);
                    System.out.println("Cliente cadastrado!");
                }
                case 2 -> clienteController.listarClientes();
                case 3 -> {
                    System.out.print("ID do cliente: ");
                    int idBusca = sc.nextInt();
                    sc.nextLine();
                    Cliente encontrado = clienteController.buscarNaCollection(idBusca);
                    System.out.println(encontrado != null ? encontrado : "Cliente não encontrado.");
                }
                case 4 -> {
                    System.out.print("Novo nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Nova idade: ");
                    int idade = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Novo email: ");
                    String email = sc.nextLine();
                    System.out.print("Novo telefone: ");
                    String telefone = sc.nextLine();
                    System.out.print("Novo tipo da conta: ");
                    int tipo = sc.nextInt();
                    sc.nextLine();

                    Cliente novoCliente;
                    switch (tipo) {
                        case 1 -> novoCliente = new ClientePadrao(nome, idade, email, telefone);
                        case 2 -> novoCliente = new ClientePlus(nome, idade, email, telefone);
                        case 3 -> novoCliente = new ClientePremium(nome, idade, email, telefone);
                        default -> {
                            System.out.println("Opção inválida.");
                            novoCliente = null;
                        }
                    }

                    clienteController.atualizarCliente(novoCliente);
                    System.out.println("Cliente atualizado!");
                }
                case 5 -> {
                    System.out.print("ID do cliente: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    clienteController.deletarCliente(id);
                    System.out.println("Cliente removido!");
                }
                case 0 -> continuar = false;
                default -> System.out.println("Opção inválida!");
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
                    sc.nextLine();

                    System.out.print("Digite a quantidade: ");
                    int qtd = sc.nextInt();
                    sc.nextLine();

                    if (cod == 2) {
                        carrinho.adicionarOuAtualizarItem(cod, qtd,
                                ApiConsulta.getImagemAleatoriaGato());
                    } else if (cod == 3) {
                        carrinho.adicionarOuAtualizarItem(cod, qtd,
                                ApiConsulta.getImagemAleatoriaCachorro());
                    } else {
                        carrinho.adicionarOuAtualizarItem(cod, qtd);
                    }
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
                    System.out.println(
                            "Antes de você ir. Você tem direito a " + novoCliente.getTipoConta()
                                    + (novoCliente.getTipoConta() > 1 ? " jogos promocionais!"
                                            : " jogo promocional!"));
                    System.out.println(
                            "Cada jogo ganho aplica uma promoção de 20% sobre o preço total de seu carrinho.\nE é cumulativo! 😱\n\nQue os jogos comecem...");

                    keyPress();

                    int promocoesGanhas = 0;

                    switch (novoCliente.getTipoConta()) {
                        case 1 -> {
                            if (JogoSorteio.iniciarJogo(sc))
                                promocoesGanhas++;
                        }
                        case 2 -> {
                            if (JogoSorteio.iniciarJogo(sc))
                                promocoesGanhas++;
                            keyPress();
                            if (JogoMatematica.jogarMatematica(sc))
                                promocoesGanhas++;
                        }
                        case 3 -> {
                            if (JogoSorteio.iniciarJogo(sc))
                                promocoesGanhas++;
                            keyPress();
                            if (JogoMatematica.jogarMatematica(sc))
                                promocoesGanhas++;
                            keyPress();
                            if (JogoCacaNiquel.iniciarJogo(sc))
                                promocoesGanhas++;
                        }
                    }

                    if (promocoesGanhas > 0) {
                        System.out.println("Meus parabéns! Você ganhou " + promocoesGanhas
                                + (promocoesGanhas == 1 ? " promoção!" : " promoções!"));
                        carrinho.listarItens(promocoesGanhas * 0.2);
                        return;
                    }

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

    static final String MENSAGEM_USUARIO = """

            %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                O que deseja fazer agora?

                1 - Adicionar/Atualizar produto
                2 - Remover produto
                3 - Ver carrinho
                4 - Finalizar compra
                0 - Sair
            %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            """;

    static final String MENSAGEM = """

            *****************************************************************************
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

    static final String MENSAGEMCOMPRODUTOS = """

            #######################################################################################

                Ainda somos uma empresa nova, então só temos as seguintes opções de produtos:

                1 - Camiseta da Saudade (homenageie seu amigo que foi jogar no vasco) => R$100,00
                2 - Compre uma imagem aleatória de um gato => R$10,00
                3 - Compre uma imagem aleatória de um cachorro => R$10,00

                Escolha uma das opções acima.

                                    Agradecemos a compreensão!
            #######################################################################################
            """;

    public static void keyPress() {
        try {
            System.out.println("\n\nPressione Enter para Continuar...");
            System.in.read();
        } catch (IOException e) {
            System.out.println("Você pressionou uma tecla diferente de enter: " + e.getCause());
        }
    }

    public static void sobre() {
        String mensagemSobre = """
                \n**************************************************************************
                    Projeto Desenvolvido por: Leonardo de Paula
                    leofernandes9@gmail.com
                    https://github.com/leodipaula
                **************************************************************************
                """;
        System.out.println(mensagemSobre);
    }

    private static void executarTestesIniciais() {
        Cliente clienteTeste1 = new ClientePadrao("João", 25, "joao@gmail.com", "123456789");
        Cliente clienteTeste2 = new ClientePlus("Maria", 30, "maria@gmail.com", "987654321");
        Cliente clienteTeste3 = new ClientePremium("Carlos", 35, "carlos@gmail.com", "456789123");

        clienteController.cadastrarCliente(clienteTeste1);
        clienteController.cadastrarCliente(clienteTeste2);
        clienteController.cadastrarCliente(clienteTeste3);

        CarrinhoService carrinhoTeste1 = new CarrinhoService();
        CarrinhoService carrinhoTeste2 = new CarrinhoService();
        CarrinhoService carrinhoTeste3 = new CarrinhoService();

        carrinhos.put(clienteTeste1.getId(), carrinhoTeste1);
        carrinhos.put(clienteTeste2.getId(), carrinhoTeste2);
        carrinhos.put(clienteTeste3.getId(), carrinhoTeste3);

        carrinhoTeste1.adicionarOuAtualizarItem(1, 2);
        carrinhoTeste2.adicionarOuAtualizarItem(2, 1, ApiConsulta.getImagemAleatoriaCachorro());
        carrinhoTeste3.adicionarOuAtualizarItem(3, 3, ApiConsulta.getImagemAleatoriaGato());

        clienteController.listarClientes();

        carrinhoTeste1.listarItens();
        carrinhoTeste2.listarItens();
        carrinhoTeste3.listarItens();

        carrinhoTeste1.calcularTotal();
        carrinhoTeste2.calcularTotal();
        carrinhoTeste3.calcularTotal();

        System.out.println("Daqui para cima são testes!");
    }
}
