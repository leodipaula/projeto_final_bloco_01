package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class JogoMatematica {
    public static boolean jogarMatematica(Scanner scanner) {
        Random random = new Random();

        // Criar expressão com 3 a 4 operações
        StringBuilder expressao = new StringBuilder();
        int numOperacoes = random.nextInt(2) + 3; // entre 3 e 4
        expressao.append(random.nextInt(9) + 1); // número inicial

        char[] operadores = {'+', '-', '*'};
        for (int i = 0; i < numOperacoes; i++) {
            char operador = operadores[random.nextInt(operadores.length)];
            int numero = random.nextInt(9) + 1;
            expressao.append(" ").append(operador).append(" ").append(numero);
        }

        int respostaCorreta = avaliarExpressao(expressao.toString());

        System.out.println("\n>>> 🔥🔥🔥😈 JOGO MATEMÁTICO 😈🔥🔥🔥 <<<");
        System.out.println("Você tem até 5 tentativas para acertar o resultado da expressão.");
        System.out.println(
                "Lembre-se da ordem de precedência (PEMDAS): Parênteses, Expoentes, Multiplicação/Divisão, Adição/Subtração.");
        System.out.println("Qual o resultado da seguinte expressão?");
        System.out.println(expressao);

        int tentativas = 0;

        if (scanner.hasNext())
            scanner.nextLine();

        while (tentativas < 5) {

            System.out.println("Tentativa " + (tentativas + 1) + ":");
            String input = scanner.nextLine();

            try {
                int resposta = Integer.parseInt(input);

                if (resposta == respostaCorreta) {
                    System.out.println("✅ Parabéns! Você acertou! ✅");
                    return true;
                } else {
                    System.out.println("❌ Resposta incorreta. ❌");
                }
                tentativas++;
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada inválida. Por favor, digite um número inteiro. ⚠️");
            }
        }

        System.out.println(
                "☹️ Suas tentativas acabaram. A resposta correta era: " + respostaCorreta + " ☹️");
        return false;
    }

    private static int avaliarExpressao(String expressao) {
        try {
            List<String> tokens = new ArrayList<>(Arrays.asList(expressao.split(" ")));

            // Primeiro, resolve multiplicações
            for (int i = 0; i < tokens.size(); i++) {
                if (tokens.get(i).equals("*")) {
                    int resultado = Integer.parseInt(tokens.get(i - 1))
                            * Integer.parseInt(tokens.get(i + 1));
                    tokens.set(i - 1, String.valueOf(resultado));
                    tokens.remove(i); // remove operador
                    tokens.remove(i); // remove número à direita
                    i--; // volta para reavaliar posição
                }
            }

            // Depois resolve adições e subtrações
            int resultado = Integer.parseInt(tokens.get(0));
            for (int i = 1; i < tokens.size(); i += 2) {
                String operador = tokens.get(i);
                int numero = Integer.parseInt(tokens.get(i + 1));

                if (operador.equals("+")) {
                    resultado += numero;
                } else if (operador.equals("-")) {
                    resultado -= numero;
                }
            }

            return resultado;
        } catch (Exception e) {
            System.out.println("Erro ao avaliar expressão: " + expressao);
            return 0;
        }
    }
}
