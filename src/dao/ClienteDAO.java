package dao;

import apoio.ConexaoBD;
import apoio.IDAOT;
import entidades.Cliente;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Univates - EAD
 * @author Lucas Cenci Beltrame
 */

public class ClienteDAO implements IDAOT<Cliente> {

    @Override
    public String salvar(Cliente o) {  //insercao dos dados fornecidos

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "insert into cliente "
                    + "(nome, e_mail, cpf, telefone) values "
                    + "('" + o.getNome() + "', '" + o.getE_mail() + "', '" + o.getCpf() + "', '" + o.getTelefone() + "')";

            System.out.println("SQL: " + sql);

            int retorno = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Falha ao inserir o cliente: " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Cliente o) {

        Cliente clienteAtualizado = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM cliente WHERE id = " + o.getId();
            System.out.println("SQL: " + sql);
            ResultSet retorno = st.executeQuery(sql);

            if (retorno.next()) {
                String updateSql = "UPDATE cliente SET ";
                boolean isFirstField = true;

                if (!o.getNome().isEmpty()) {
                    updateSql += "nome = '" + o.getNome() + "'";
                    isFirstField = false;
                }

                if (!o.getE_mail().isEmpty()) {
                    if (!isFirstField) {
                        updateSql += ", ";
                    }
                    updateSql += "e_mail = '" + o.getE_mail() + "'";
                    isFirstField = false;
                }

                if (!o.getCpf().isEmpty()) {
                    if (!isFirstField) {
                        updateSql += ", ";
                    }
                    updateSql += "cpf = '" + o.getCpf() + "'";
                    isFirstField = false;
                }

                if (!o.getTelefone().isEmpty()) {
                    if (!isFirstField) {
                        updateSql += ", ";
                    }
                    updateSql += "telefone = '" + o.getTelefone() + "'";
                }

                updateSql += " WHERE id = " + o.getId();

                System.out.println("SQL: " + updateSql);
                st.executeUpdate(updateSql);

                // Criar o objeto cliente atualizado
                clienteAtualizado = new Cliente();
                clienteAtualizado.setId(o.getId());
                clienteAtualizado.setNome(o.getNome());
                clienteAtualizado.setE_mail(o.getE_mail());
                clienteAtualizado.setCpf(o.getCpf());
                clienteAtualizado.setTelefone(o.getTelefone());
            }
        } catch (Exception e) {

            System.out.println("Falha ao atualizar cliente: " + e);

            return e.toString();
        }
        return null;
    }

    @Override
    public String excluir(int id) {

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "DELETE FROM cliente WHERE id = " + id;
            System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);

            if (resultado > 0) {
                System.out.println("Cliente excluído com sucesso!");

            } else {
                System.out.println("Nenhum cliente encontrado com o ID fornecido.");

            }
        } catch (Exception e) {

            System.out.println("Falha ao excluir cliente: " + e);

            return e.toString();
        }

        return null;
    }

    @Override
    public ArrayList<Cliente> consultarTodos() {

        //codigo que consulta o resultado da tabela
        ArrayList<Cliente> clientes = new ArrayList();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from cliente";

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            while (retorno.next()) {
                Cliente cliente = new Cliente();

                cliente.setNome(retorno.getString("nome"));
                cliente.setE_mail(retorno.getString("e_mail"));
                cliente.setCpf(retorno.getString("cpf"));
                cliente.setTelefone(retorno.getString("telefone"));

                clientes.add(cliente);

            }
        } catch (Exception e) {
            System.out.println("Falha ao consultar clientes: " + e);
        }
        return clientes;
    }

    @Override
    public ArrayList<Cliente> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Cliente consultarId(int id) {

        Cliente cliente = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "select * "
                    + "from cliente "
                    + "where id = " + id;

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            while (retorno.next()) {
                cliente = new Cliente();

                cliente.setNome(retorno.getString("nome"));
                cliente.setE_mail(retorno.getString("e_mail"));
                cliente.setCpf(retorno.getString("cpf"));
                cliente.setTelefone(retorno.getString("telefone"));
            }

        } catch (Exception e) {
            System.out.println("Falha ao consultar cliente: " + e);
        }

        return cliente;
    }

    public void popularTabelacliente(JTable tabela, String criterio) {

        ResultSet resultadoQ;

        //cabecalho da tabela 
        Object[] cabecalho = new Object[5];
        cabecalho[0] = "id";
        cabecalho[1] = "nome";
        cabecalho[2] = "e_mail";
        cabecalho[3] = "cpf";
        cabecalho[4] = "telefone";

        try {
            //numero total da pesquisa 
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(
                    "SELECT count(*) "
                    + "FROM cliente "
                    + "WHERE "
                    + "nome ILIKE '%" + criterio + "%' "
                    + "OR e_mail ILIKE '%" + criterio + "%' "
                    + "OR cpf ILIKE '%" + criterio + "%' "
                    + "OR telefone ILIKE '%" + criterio + "%'"
            );

            resultadoQ.next();
            int numRegistros = resultadoQ.getInt(1);

            //num de registros do cabecalho
            Object[][] dadosTabela = new Object[numRegistros][5];

            //conexao ao banco de dados para insercao
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(
                    "SELECT * "
                    + "FROM cliente "
                    + "WHERE "
                    + "nome ILIKE '%" + criterio + "%' "
                    + "OR e_mail ILIKE '%" + criterio + "%' "
                    + "OR cpf ILIKE '%" + criterio + "%' "
                    + "OR telefone ILIKE '%" + criterio + "%'"
                    + "ORDER BY nome ASC");

            int lin = 0;
            while (resultadoQ.next()) {
                dadosTabela[lin][0] = resultadoQ.getInt("id");
                dadosTabela[lin][1] = resultadoQ.getString("nome");
                dadosTabela[lin][2] = resultadoQ.getString("e_mail");
                dadosTabela[lin][3] = resultadoQ.getString("cpf");
                dadosTabela[lin][4] = resultadoQ.getString("telefone");

                lin++;
            }

            tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho));

        } catch (Exception e) {
            System.out.println("Falha ao preencher os dados da tabela.");
            System.out.println(e);
        }
    }
}
