import java.io.IOException;
import java.util.Scanner;

public class Menu {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
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

        // TODO: instanciar a classe que o cliente pertence

        do {
            System.out.println(MENSAGEMCOMPRODUTOS);
            int codigoProduto;

            while (true) {
                codigoProduto = sc.nextInt();
                sc.nextLine();
                if (codigoProduto < 1 || codigoProduto > 3) {
                    System.out.println("Opção inválida!");
                    continue;
                }
                break;
            }

            System.out.print("\nQuantidade: ");
            int quantidade = sc.nextInt();
            sc.nextLine();

            switch (codigoProduto) { // TODO: Implementar as opções que estarão na controller
                case 1:

                    keyPress();
                    break;

                default:
                    break;
            }
        } while (opcao != 0); // TODO: atualizar a condição
    }

    // TODO: Talvez seja melhor as mensagens ficarem na pasta utils
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

                        Escolha uma dentre as opções abaixo.

                        1 - Camiseta da Saudade (homenageie seu amigo que foi jogar no vasco) => R$100,00
                        2 - Compre uma imagem aleatória de um gato => R$10,00
                        3 - Compre uma imagem aleatória de um cachorro => R$10,00

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
}
