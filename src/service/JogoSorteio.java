package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class JogoSorteio {

    public static boolean iniciarJogo(Scanner sc) {
        int chute = 0;
        int tentativas = 1;

        while (true) {
            List<Integer> numerosAleatorios = getNumerosAleatorios();

            System.out.println(
                    "🎲 Você precisa acertar um número de uma lista com 10 número aleatórios."
                            + " \nOs números vão de 0 a 99..." + " Você terá 5 tentativas! 🎲");

            while (true) {
                if (tentativas > 5) {
                    System.out.println("\n\n💀 Seu número de tentativas se esgotaram. 💀");
                    return false;
                }

                System.out.print("\n\nChute um número: ");
                chute = sc.nextInt();
                sc.nextLine();

                if (numerosAleatorios.contains(chute)) {
                    System.out.println("\n\n🎉 Você acertou!!!! 🎉");
                    return true;
                }

                System.out
                        .print("\n😢 Você errou! Te restam " + (5 - tentativas) + " tentativas 😢");

                tentativas++;
            }
        }
    }

    private static List<Integer> getNumerosAleatorios() {
        List<Integer> vetorTemporario = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            vetorTemporario.add(random.nextInt(99));
        }

        return vetorTemporario;
    }
}
