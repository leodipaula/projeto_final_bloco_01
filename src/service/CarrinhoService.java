package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.ItemCarrinho;

public class CarrinhoService {
    private final List<ItemCarrinho> carrinho = new ArrayList<>();

    public void adicionarOuAtualizarItem(int codigo, int quantidade) {
        Optional<ItemCarrinho> itemExistente =
                carrinho.stream().filter(item -> item.getCodigoProduto() == codigo).findFirst();

        if (itemExistente.isPresent()) {
            itemExistente.get().setQuantidade(quantidade);
        } else {
            carrinho.add(criarItem(codigo, quantidade));
        }
    }

    public void adicionarOuAtualizarItem(int codigo, int quantidade, String url) {
        Optional<ItemCarrinho> itemExistente =
                carrinho.stream().filter(item -> item.getCodigoProduto() == codigo).findFirst();

        if (itemExistente.isPresent()) {
            itemExistente.get().setQuantidade(quantidade);
        } else {
            carrinho.add(criarItem(codigo, quantidade, url));
        }
    }

    public void removerItem(int codigo) {
        carrinho.removeIf(item -> item.getCodigoProduto() == codigo);
    }

    public void listarItens() {
        if (carrinho.isEmpty()) {
            System.out.println("Carrinho vazio.");
            return;
        }

        carrinho.forEach(System.out::println);
        System.out.printf("TOTAL DA COMPRA: R$%.2f%n", calcularTotal());
    }

    public void listarItens(double promocao) {
        if (carrinho.isEmpty()) {
            System.out.println("Carrinho vazio.");
            return;
        }

        carrinho.forEach(System.out::println);
        System.out.printf("TOTAL DA COMPRA: R$%.2f%n", calcularTotal() * (1 - promocao));
    }

    public double calcularTotal() {
        return carrinho.stream().mapToDouble(ItemCarrinho::getTotal).sum();
    }

    private ItemCarrinho criarItem(int codigo, int quantidade) {
        return switch (codigo) {
            case 1 -> new ItemCarrinho(1, "Camiseta da Saudade", 100.0, quantidade);
            case 2 -> new ItemCarrinho(2, "Imagem de Gato", 10.0, quantidade);
            case 3 -> new ItemCarrinho(3, "Imagem de Cachorro", 10.0, quantidade);
            default -> throw new IllegalArgumentException("Código inválido de produto.");
        };
    }

    private ItemCarrinho criarItem(int codigo, int quantidade, String url) {
        return switch (codigo) {
            case 1 -> new ItemCarrinho(1, "Camiseta da Saudade", 100.0, quantidade);
            case 2 -> new ItemCarrinho(2, "Imagem de Gato", 10.0, quantidade, url);
            case 3 -> new ItemCarrinho(3, "Imagem de Cachorro", 10.0, quantidade, url);
            default -> throw new IllegalArgumentException("Código inválido de produto.");
        };
    }

    public void limparCarrinho() {
        carrinho.clear();
    }
}
