package KauaNog.projetoLabCep.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import KauaNog.projetoLabCep.model.Endereco;

@FeignClient(name = "viacep", url = "viacep.com.br/ws/01001000/json/")
public interface ViaCepService {
    @RequestMapping(method = RequestMethod.GET, value = "/{cep}/{json}/")
    Endereco consultarCep(@PathVariable("cep") String cep);
}
