package dao;

import apoio.ConexaoBD;
import apoio.IDAOT;
import entidades.Usuario;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 * Univates - EAD
 * @author Lucas Cenci Beltrame
 */

public class UsuarioDAO implements IDAOT<Usuario> {

    @Override
    public String salvar(Usuario o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String atualizar(Usuario o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Usuario> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Usuario> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean autenticar(Usuario u) {             // AUTENTICACAO DO LOGIN

        try {
            String sql
                    = "SELECT email, senha "
                    + "FROM usuario "
                    + "WHERE "
                    + "email = '" + u.getEmail() + "'  and "
                    + "senha = md5 ('" + u.getSenha() + "') and "
                    + "situacao = 'a'";

            System.out.println("SQL: " + sql);

            ResultSet resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            if (resultadoQ.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Falha ao verificar o usuário: " + e);
            return false;
        }
    }
}
