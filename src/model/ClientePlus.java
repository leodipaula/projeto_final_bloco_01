package model;

public class ClientePlus extends Cliente {
    public ClientePlus(String nome, int idade, String email, String telefone) {
        super(nome, idade, email, telefone, 2);
    }

    @Override
    public String toString() {
        return super.toString() + "\nDireito a apenas 2 jogos promocionais: Sorteio, Matemática.";
    }


}
