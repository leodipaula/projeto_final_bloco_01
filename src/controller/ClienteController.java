package controller;

import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import repository.ClienteRepository;

public class ClienteController implements ClienteRepository {
    private List<Cliente> listaClientes = new ArrayList<>();

    @Override
    public void cadastrarCliente(Cliente cliente) {
        if (buscarNaCollection(cliente.getId()) != null) {
            System.out.println("Esse Id já está registrado em nosso banco de dados.");
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
        var buscaCliente = buscarNaCollection(cliente.getId());

        if (buscaCliente != null) {
            listaClientes.set(listaClientes.indexOf(buscaCliente), cliente);
            System.out.println("\nA Cliente: " + cliente.getNome().trim().split(" ")[0]
                    + " foi atualizado com sucesso!");
            return;
        }
        System.out.println("\nO cliente não foi achado no banco de dados!");
    }

    @Override
    public void deletarCliente(int id) {
        var buscaCliente = buscarNaCollection(id);

        if (buscaCliente != null) {
            if (listaClientes.remove(buscaCliente)) {
                System.out.println("Cliente deletado com sucesso!");
                return;
            }
        }
        System.out.println("O cliente não foi encontrado!");
    }



    @Override
    public void buscarPorId(int id) {
        var buscarCliente = buscarNaCollection(id);

        if (buscarCliente != null) {
            System.out.println(buscarCliente);
            return;
        }
        System.out.println("Cliente não encontrado!");
    }

    public Cliente buscarNaCollection(int id) {
        return listaClientes.stream().filter(cliente -> cliente.getId() == id).findAny()
                .orElse(null);
    }
}
