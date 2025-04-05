package service;

import java.util.Random;
import java.util.Scanner;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class JogoMatematica {
    public static boolean jogarMatematica(Scanner scanner) {
        Random random = new Random();

        // Criando expressão com no mínimo 3 operações
        StringBuilder expressao = new StringBuilder();
        int numOperacoes = random.nextInt(2) + 3; // 3 a 4 operações
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
            // Usamos o mecanismo de script JavaScript para avaliar a expressão
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
            Object resultado = engine.eval(expressao);
            return ((Number) resultado).intValue();
        } catch (Exception e) {
            System.out.println("Erro ao avaliar expressão: " + expressao);
            return 0;
        }
    }
}
