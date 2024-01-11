
package dao;

import apoio.ConexaoBD;
import entidades.Endereco;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import apoio.IDAOT;

/**
 * Univates - EAD
 * @author Lucas Cenci Beltrame
 */

public class EnderecoDAO implements IDAOT<Endereco> {
    
    @Override
    public String salvar(Endereco o) {  //insercao dos dados fornecidos

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "insert into endereco "
                    + "(descricao, cep) values "
                    + "('" + o.getDescricao()+ "', '" + o.getCep() + "')";

            System.out.println("SQL: " + sql);

            int retorno = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Falha ao inserir o endereco: " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Endereco o) {

        Endereco enderecoAtualizado = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM endereco WHERE id = " + o.getId();
            System.out.println("SQL: " + sql);
            ResultSet retorno = st.executeQuery(sql);

            if (retorno.next()) {
                String updateSql = "UPDATE endereco SET ";
                boolean isFirstField = true;

                if (!o.getDescricao().isEmpty()) {
                    updateSql += "descricao = '" + o.getDescricao()+ "'";
                    isFirstField = false;
                }
                
                if (!o.getCep().isEmpty()) {
                    updateSql += "cep = '" + o.getCep()+ "'";
                    isFirstField = false;
                }

                updateSql += " WHERE id = " + o.getId();

                System.out.println("SQL: " + updateSql);
                st.executeUpdate(updateSql);

                // Criar o objeto cliente atualizado
                enderecoAtualizado = new Endereco();
                enderecoAtualizado.setId(o.getId());
                enderecoAtualizado.setDescricao(o.getDescricao());
                enderecoAtualizado.setCep(o.getCep());
            }
        } catch (Exception e) {

            System.out.println("Falha ao atualizar endereco: " + e);

            return e.toString();
        }
        return null;
    }

    @Override
    public String excluir(int id) {

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "DELETE FROM endereco WHERE id = " + id;
            System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);

            if (resultado > 0) {
                System.out.println("Endereco excluído com sucesso!");

            } else {
                System.out.println("Nenhum endereco encontrado com o ID fornecido.");

            }
        } catch (Exception e) {

            System.out.println("Falha ao excluir endereco: " + e);

            return e.toString();
        }

        return null;
    }

    @Override
    public ArrayList<Endereco> consultarTodos() {

        //codigo que consulta o resultado da tabela
        ArrayList<Endereco> enderecos = new ArrayList();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from endereco";

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            while (retorno.next()) {
                Endereco endereco = new Endereco();

                endereco.setDescricao(retorno.getString("descricao"));
                endereco.setCep(retorno.getString("cep"));
             
                enderecos.add(endereco);

            }
        } catch (Exception e) {
            System.out.println("Falha ao consultar enderecos: " + e);
        }
        return enderecos;
    }

    @Override
    public ArrayList<Endereco> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Endereco consultarId(int id) {

        Endereco endereco = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "select * "
                    + "from endereco "
                    + "where id = " + id;

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            while (retorno.next()) {
                endereco = new Endereco();

                endereco.setDescricao(retorno.getString("descricao"));
                endereco.setCep(retorno.getString("cep"));
          
            }

        } catch (Exception e) {
            System.out.println("Falha ao consultar cliente: " + e);
        }

        return endereco;
    }

    public void popularTabelaendereco(JTable tabela, String criterio) {

        ResultSet resultadoQ;

        //cabecalho da tabela 
        Object[] cabecalho = new Object[3];
        cabecalho[0] = "id";
        cabecalho[1] = "descricao";
        cabecalho[2] = "cep";
  
        try {
            //numero total da pesquisa 
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(
                    "SELECT count(*) "
                    + "FROM endereco "
                    + "WHERE "
                    + "descricao ILIKE '%" + criterio + "%'"
                    + "OR cep ILIKE '%" + criterio + "%'"
            );

            resultadoQ.next();
            int numRegistros = resultadoQ.getInt(1);

            //num de registros do cabecalho
            Object[][] dadosTabela = new Object[numRegistros][3];

            //conexao ao banco de dados para insercao
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(
                    "SELECT * "
                    + "FROM endereco "
                    + "WHERE "
                    + "descricao ILIKE '%" + criterio + "%'"
                    + "OR cep ILIKE '%" + criterio + "%'"
                    
            );

            int lin = 0;
            while (resultadoQ.next()) {
                dadosTabela[lin][0] = resultadoQ.getInt("id");
                dadosTabela[lin][1] = resultadoQ.getString("descricao");
                dadosTabela[lin][2] = resultadoQ.getString("cep");

                lin++;
            }

            tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho));

        } catch (Exception e) {
            System.out.println("Falha ao preencher os dados da tabela.");
            System.out.println(e);
        }
    }
}
