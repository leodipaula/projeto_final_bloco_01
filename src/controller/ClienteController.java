package controller;

import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import repository.ClienteRepository;

public class ClienteController implements ClienteRepository {
    private List<Cliente> listaClientes = new ArrayList<>();

    @Override
    public void cadastrarCliente(Cliente cliente) {
        if (buscarNaCollection(cliente.getEmail()) != null) {
            System.out.println("Esse email já está registrado em nosso banco de dados.");
            return;
        }
        listaClientes.add(cliente);
    }

    @Override
    public void listarClientes() {
        listaClientes.forEach(System.out::println);
    }

    @Override
    public void atualizarCliente(Cliente cliente) {
        var buscaCliente = buscarNaCollection(cliente.getEmail());

        if (buscaCliente != null) {
            listaClientes.set(listaClientes.indexOf(buscaCliente), cliente);
            System.out.println("\nA Cliente: " + cliente.getNome().split(" ")[0]
                    + " foi atualizado com sucesso!");
            return;
        }
        System.out.println("\nO cliente não foi achado no banco de dados!");
    }

    @Override
    public void deletarCliente(Cliente cliente) {
        var buscaCliente = buscarNaCollection(cliente.getEmail());

        if (buscaCliente != null) {
            if (listaClientes.remove(cliente)) {
                System.out.println("Cliente deletado com sucesso!");
                return;
            }
        }
        System.out.println("O cliente não foi encontrado!");
    }

    public Cliente buscarNaCollection(String email) {
        return listaClientes.stream().filter(cliente -> cliente.getEmail() == email).findAny()
                .orElse(null);
    }
}
