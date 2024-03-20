package br.unipar.programacaointernet.servicecep;

import br.unipar.programacaointernet.servicecep.dao.EnderecoDAO;
import br.unipar.programacaointernet.servicecep.dao.EnderecoDAOImpl;
import br.unipar.programacaointernet.servicecep.dao.UsuarioDAO;
import br.unipar.programacaointernet.servicecep.dao.UsuarioDAOImpl;
import br.unipar.programacaointernet.servicecep.model.Endereco;
import br.unipar.programacaointernet.servicecep.model.Usuario;
import br.unipar.programacaointernet.servicecep.util.EntityManagerUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            EntityManagerUtil.getEntityManagerFactory();

            //salvarUsuario();
            //editarUsuario();
            //deletarUsuario();
            //buscarUsuarioPorID();
            //buscarTodosUsuarios();

            salvarEndereco();

            EntityManagerUtil.closeEntityManagerFactory();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void salvarEndereco() {
        try {
            EnderecoDAO enderecoDAO =
                    new EnderecoDAOImpl(EntityManagerUtil.getManager());

            enderecoDAO.save(getViaCep("85900120"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Endereco getViaCep(String cep) throws Exception {
        URL url = new URL("http://viacep.com.br/ws/"+cep.replace("-", "")
                .replace(".", "")+"/xml/");

        BufferedReader in = new BufferedReader(	new InputStreamReader(url.openStream()));

        String inputLine;
        String result = "";
        while ((inputLine = in.readLine()) != null)
            result += inputLine;

        in.close();
        return Endereco.unmarshalFromString(result);
    }

    private static void buscarTodosUsuarios() {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl(
                EntityManagerUtil.getManager());

        List<Usuario> usuarios = usuarioDAO.findAll();

        for(Usuario usuario : usuarios) {
            System.out.println("Usuário " + usuario.getNome() +
                    " encontrado com sucesso!");
        }
    }

    private static void buscarUsuarioPorID() {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl(
                EntityManagerUtil.getManager());

        Usuario usuario = usuarioDAO.findById(1L);

        System.out.println("Usuário " + usuario.getNome() +
                " encontrado com sucesso!");
    }

    private static void deletarUsuario() {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl(
                EntityManagerUtil.getManager());

        Usuario usuario = usuarioDAO.findById(1L);
        usuarioDAO.delete(usuario);
    }

    private static void salvarUsuario() {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl(
                EntityManagerUtil.getManager());

        Usuario usuario = new Usuario();

        usuario.setNome("Abreu");
        usuario.setLogin("abreuNemEu");
        usuario.setSenha("321");

        usuarioDAO.save(usuario);
    }

    private static void editarUsuario() {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl(
                EntityManagerUtil.getManager());

        Usuario usuario = usuarioDAO.findById(1L);

        usuario.setSenha("654321");

        usuarioDAO.update(usuario);
    }
}
