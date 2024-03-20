package br.unipar.programacaointernet.servicecep.service;


import br.unipar.programacaointernet.servicecep.model.Usuario;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface UsuarioSEI {

    @WebMethod
    String boasVindas(@WebParam(name = "nome") String nome);

    @WebMethod
    String salvarUsuario(@WebParam(name = "nome") String nome,
                       @WebParam(name = "login") String login,
                       @WebParam(name = "senha") String senha);

    @WebMethod
    String editarUsuario(@WebParam(name = "id") Long id,
                       @WebParam(name = "nome") String nome,
                       @WebParam(name = "login") String login,
                       @WebParam(name = "senha") String senha);

    @WebMethod
    String deletarUsuario(@WebParam(name = "id") Long id);

    @WebMethod
    Usuario consultaUsuarioPorID(@WebParam(name = "idUsuario") Long id);
    @WebMethod
    List<Usuario> consultaUsuarios();

}
