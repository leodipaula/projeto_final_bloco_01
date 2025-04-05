package service;

import java.util.Random;
import java.util.Scanner;

public class JogoCacaNiquel {
    private static final String[] SIMBOLOS = {"🍒", "🍋", "🍇", "🍉", "⭐", "🔔"};
    private static final int MAX_TENTATIVAS = 5;

    public static boolean iniciarJogo(Scanner scanner) {
        Random random = new Random();
        int tentativas = 0;

        System.out.println("\n🎰 Bem-vindo ao Caça-Níquel da Promoção! 🎰");
        System.out.println("Você tem até " + MAX_TENTATIVAS + " tentativas para ganhar!");

        while (tentativas < MAX_TENTATIVAS) {
            System.out
                    .print("\nTentativa " + (tentativas + 1) + " - Pressione ENTER para girar...");
            scanner.nextLine();

            String simbolo1 = SIMBOLOS[random.nextInt(SIMBOLOS.length)];
            String simbolo2 = SIMBOLOS[random.nextInt(SIMBOLOS.length)];
            String simbolo3 = SIMBOLOS[random.nextInt(SIMBOLOS.length)];

            System.out.println("🎲 Resultado: " + simbolo1 + " " + simbolo2 + " " + simbolo3);

            if (simbolo1.equals(simbolo2) && simbolo2.equals(simbolo3)) {
                System.out.println("🎉 Parabéns! Você ganhou a promoção! 🎉");
                return true;
            }
            System.out.println("😢 Não foi dessa vez... 😢");
            tentativas++;
        }

        System.out.println("\n💀 Fim das tentativas! Você perdeu a promoção. 💀");

        return false;
    }
}
