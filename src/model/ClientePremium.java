package model;

public class ClientePremium extends Cliente {
    public ClientePremium(String nome, int idade, String email, String telefone) {
        super(nome, idade, email, telefone, 3);
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nDireito aos 3 jogos promocionais: Sorteio, Matemática e Caça Níquel. \nTem direito a frete grátis.";
    }
}
