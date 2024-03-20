package br.unipar.programacaointernet.servicecep.dao;

import br.unipar.programacaointernet.servicecep.model.Endereco;
import br.unipar.programacaointernet.servicecep.model.Usuario;

import java.util.List;

public interface EnderecoDAO {

    void save(Endereco endereco);
    void update(Endereco endereco);
    void delete(Endereco endereco);

    Endereco findById(Long id);
    List<Endereco> findAll();

    Endereco findByCep(String cep);
}
