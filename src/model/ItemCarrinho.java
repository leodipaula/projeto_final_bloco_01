package model;

public class ItemCarrinho {
    private int codigoProduto;
    private String nomeProduto;
    private double precoUnitario;
    private int quantidade;

    public ItemCarrinho(int codigoProduto, String nomeProduto, double precoUnitario,
            int quantidade) {
        this.codigoProduto = codigoProduto;
        this.nomeProduto = nomeProduto;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getTotal() {
        return precoUnitario * quantidade;
    }

    @Override
    public String toString() {
        return codigoProduto + " - " + nomeProduto + " | Qtd: " + quantidade + " | R$" + getTotal();
    }
}
