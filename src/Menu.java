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

        executarTestesIniciais();

        while (true) {
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

            Cliente novoCliente = null;

            switch (opcao) {
                case 1:
                    novoCliente = new ClientePadrao(nome, idade, email, telefone);
                    break;
                case 2:
                    novoCliente = new ClientePlus(nome, idade, email, telefone);
                    break;
                case 3:
                    novoCliente = new ClientePremium(nome, idade, email, telefone);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    return;
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
        carrinhoTeste2.adicionarOuAtualizarItem(2, 1);
        carrinhoTeste3.adicionarOuAtualizarItem(3, 3);

        clienteController.listarClientes();

        carrinhoTeste1.listarItens();
        carrinhoTeste2.listarItens();
        carrinhoTeste3.listarItens();

        carrinhoTeste1.calcularTotal();
        carrinhoTeste2.calcularTotal();
        carrinhoTeste3.calcularTotal();


        System.out.println("Clientes e carrinhos testados!");
    }
}
