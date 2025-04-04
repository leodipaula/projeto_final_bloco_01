package repository;

import model.Cliente;

public interface ClienteRepository {

    public void cadastrarCliente(Cliente cliente);

    public void listarClientes();

    public void atualizarCliente(Cliente cliente);

    public void deletarCliente(int id);

    public void buscarPorId(int id);

}
