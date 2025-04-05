package model;

public class ItemCarrinho {
    private int codigoProduto;
    private String nomeProduto;
    private double precoUnitario;
    private int quantidade;
    private String url;

    public ItemCarrinho(int codigoProduto, String nomeProduto, double precoUnitario,
            int quantidade) {
        this.codigoProduto = codigoProduto;
        this.nomeProduto = nomeProduto;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }


    public ItemCarrinho(int codigoProduto, String nomeProduto, double precoUnitario, int quantidade,
            String url) {
        this.codigoProduto = codigoProduto;
        this.nomeProduto = nomeProduto;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.url = url;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
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
        StringBuilder sb = new StringBuilder();
        sb.append(codigoProduto).append(" - ").append(nomeProduto).append(" | Qtd: ")
                .append(quantidade).append(" | R$").append(String.format("%.2f", getTotal()));

        if (url != null && !url.isEmpty()) {
            sb.append("\nURL da imagem: ").append(url);
        }

        return sb.toString();
    }

}
