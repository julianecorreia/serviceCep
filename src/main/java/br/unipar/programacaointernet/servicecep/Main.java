package br.unipar.programacaointernet.servicecep;

import br.unipar.programacaointernet.servicecep.dao.UsuarioDAO;
import br.unipar.programacaointernet.servicecep.dao.UsuarioDAOImpl;
import br.unipar.programacaointernet.servicecep.model.Usuario;
import br.unipar.programacaointernet.servicecep.util.EntityManagerUtil;

public class Main {

    public static void main(String[] args) {
        try {
            EntityManagerUtil.getEntityManagerFactory();

            //salvarUsuario();
            editarUsuario();

            EntityManagerUtil.closeEntityManagerFactory();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void salvarUsuario() {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl(
                EntityManagerUtil.getManager());

        Usuario usuario = new Usuario();

        usuario.setNome("Zézinho");
        usuario.setLogin("zezinho123");
        usuario.setSenha("123456");

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
