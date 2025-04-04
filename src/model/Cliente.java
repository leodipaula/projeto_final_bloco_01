package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class Cliente {

    private static int proximoId = 1;

    private int id;
    private String nome;
    private int idade;
    private String email;
    private String telefone;
    private TipoDaConta tipoConta;

    public Cliente() {
        this.id = proximoId++;
    }

    public Cliente(String nome, int idade, String email, String telefone, int tipoConta) {
        this.id = proximoId++;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.telefone = telefone;
        this.tipoConta = TipoDaConta.pegarTipo(tipoConta);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public int getTipoConta() {
        return tipoConta.codigo;
    }

    public void setTipoConta(int tipoConta) {
        this.tipoConta = TipoDaConta.pegarTipo(tipoConta);
    }

    private enum TipoDaConta {
        CONTAPADRAO(1, "Conta Padrão"), CONTAPLUS(2, "Conta Plus"), CONPREMIUM(3, "Conta Premium");

        private final String descricao;
        private final int codigo;
        private static final Map<Integer, TipoDaConta> MAP = new HashMap<>();

        static {
            for (var tipo : values())
                MAP.put(tipo.codigo, tipo);
        }

        TipoDaConta(int codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public static TipoDaConta pegarTipo(int codigo) {
            return Optional.ofNullable(MAP.get(codigo))
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Código inválido para o tipo da conta " + codigo));
        }

        @Override
        public String toString() {
            return descricao;
        }
    }

    @Override
    public String toString() {
        return "\n\nCliente id: " + id + "\nnome: " + nome + "\nidade: " + idade + "\nemail: "
                + email + "\ntelefone: " + telefone + "\ntipoConta: " + tipoConta;
    }


}
