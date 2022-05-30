package KauaNog.projetoLabCep.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import KauaNog.projetoLabCep.model.Client;
import KauaNog.projetoLabCep.model.ClientRepository;
import KauaNog.projetoLabCep.model.Endereco;
import KauaNog.projetoLabCep.model.EnderecoRepository;
import KauaNog.projetoLabCep.service.ClientService;
import KauaNog.projetoLabCep.service.ViaCepService;

@Service
public class ClientServerImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;


    @Override
    public Iterable<Client> buscarTodos() {
        //retorna todas as entitades encontradas
        return clientRepository.findAll();
    }

    @Override
    public Client buscarPorId(Long id) {
        //retorna os ids
        Optional<Client> client = clientRepository.findById(id);
        //retorna apenas se existir
        if(client.isPresent()){
            return client.get();
        }else{
            return null;
        }
        
    }

    @Override
    public void inserir(Client client) {
        //insere A partir do cep
        saveClientWCep(client);
    }

    

    @Override
    public void atualizar(Long id, Client client) {
        //atualiza a partir do cep
        Optional<Client> clientBanco = clientRepository.findById(id);
        //salva apenas se existir
        if(clientBanco.isPresent()){
            saveClientWCep(client);
        }
    }

    @Override
    public void deletar(Long id) {
        //deleta via id
       clientRepository.deleteById(id); 
    }
    
    private void saveClientWCep(Client client) {
        //salva clientes via cep
        //Extraído para método para manter DRY
        String cep = client.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        client.setEndereco(endereco);
        clientRepository.save(client);
    }
}
