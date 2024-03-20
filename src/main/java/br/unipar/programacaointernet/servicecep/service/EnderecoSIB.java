package br.unipar.programacaointernet.servicecep.service;

import br.unipar.programacaointernet.servicecep.Main;
import br.unipar.programacaointernet.servicecep.dao.EnderecoDAO;
import br.unipar.programacaointernet.servicecep.dao.EnderecoDAOImpl;
import br.unipar.programacaointernet.servicecep.dao.UsuarioDAO;
import br.unipar.programacaointernet.servicecep.dao.UsuarioDAOImpl;
import br.unipar.programacaointernet.servicecep.model.Endereco;
import br.unipar.programacaointernet.servicecep.model.Usuario;
import br.unipar.programacaointernet.servicecep.util.EntityManagerUtil;
import jakarta.jws.WebService;

import java.util.List;

@WebService(endpointInterface = "br.unipar.programacaointernet.servicecep.service.EnderecoSEI")
public class EnderecoSIB implements EnderecoSEI {
    public String boasVindas(String nome) {
        return "Bem Vindo(a) " + nome + "!";
    }

    @Override
    public String salvarEndereco(String cep, String logradouro, String complemento, String bairro, String localidade) {
        return null;
    }

    @Override
    public String editarEndereco(Long id, String cep, String logradouro, String complemento, String bairro, String localidade) {
        return null;
    }

    @Override
    public String deletarEndereco(Long id) {
        return null;
    }

    @Override
    public Endereco consultaEnderecoPorID(Long id) {
        return null;
    }

    @Override
    public List<Endereco> consultaEnderecos() {
        EnderecoDAO enderecoDAO = new EnderecoDAOImpl(EntityManagerUtil.getManager());

        return enderecoDAO.findAll();
    }

    @Override
    public Endereco consultaPorCep(String cep) {
        EnderecoDAO enderecoDAO = new EnderecoDAOImpl(EntityManagerUtil.getManager());

        Endereco endereco = enderecoDAO.findByCep(cep);

        if(endereco == null) {
            try {
                endereco = Main.getViaCep(cep);

                if(endereco.getCep() == null) {
                    //validação - retorna pro usuario algo avisando
                } else {
                    enderecoDAO.save(endereco);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return endereco;
    }


}
