package KauaNog.projetoLabCep.service;

import KauaNog.projetoLabCep.model.Client;

public interface ClientService {
    
    Iterable<Client> buscarTodos();

    Client buscarPorId(Long Id);

    void inserir(Client client);

    void atualizar(Long Id, Client client);

    void deletar(Long Id);
}
