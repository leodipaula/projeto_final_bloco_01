package model;

public class ClientePadrao extends Cliente {
    public ClientePadrao(String nome, int idade, String email, String telefone) {
        super(nome, idade, email, telefone, 1);
    }

    @Override
    public String toString() {
        return super.toString() + "\nDireito a apenas um jogo promocional: Sorteio";
    }
}
