package repository;

import model.Cliente;

public interface ClienteRepository {

    public void CadastrarCliente(Cliente cliente);

    public void ListarClientes();

    public void atualizarQuantidadeDeCliente();

    public void deletarCliente();

}
