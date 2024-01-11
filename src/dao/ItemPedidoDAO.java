package dao;

import apoio.ConexaoBD;
import apoio.IDAOT;
import entidades.ItemPedido;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * Univates - EAD
 * @author Lucas Cenci Beltrame
 */

public class ItemPedidoDAO implements IDAOT<ItemPedido> {

    @Override
    public String salvar(ItemPedido o) {     //insercao dos dados fornecidos
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "insert into item_pedido " + "values" + "(default, " + "'" + o.getProduto_id() + "', "
                    + "'" + o.getPedido_id() + "', '" + o.getQtde() + "', '" + o.getValor_item() + "')";

            System.out.println("SQL: " + sql);

            int retorno = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Falha ao salvar item_pedido: " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(ItemPedido o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(int idPedido) {  // CODIGO DE EXCLUSAO
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "delete "
                    + "from item_pedido "
                    + "where "
                    + "id = " + idPedido;

            System.out.println("SQL: " + sql);

            int retorno = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Falha ao excluir item_pedido! " + e);
            return e.toString();
        }
    }

    @Override
    public ArrayList<ItemPedido> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ItemPedido> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItemPedido consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void popularTabela(JTable tabela, String criterio, int idPedido) {    // POPULA A TABELA
        ResultSet resultadoQ;

        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[5];
        cabecalho[0] = "Id";
        cabecalho[1] = "Id Item";
        cabecalho[2] = "Descrição";
        cabecalho[3] = "Qtde";
        cabecalho[4] = "Valor";

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "select count(*) "
                    + "from item_pedido, produto "
                    + "where "
                    + "item_pedido.produto_id = produto.id and "
                    + "item_pedido.pedido_id = " + idPedido);

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][5];

        } catch (Exception e) {
            System.out.println("Falhas ao consultar a tabela item_pedido: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT item_pedido.id, item_pedido.produto_id, item_pedido.qtde, produto.descricao, item_pedido.valor_item "
                    + "FROM item_pedido, produto "
                    + "WHERE "
                    + "item_pedido.produto_id = produto.id AND "
                    + "item_pedido.pedido_id = " + idPedido);

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("id");
                dadosTabela[lin][1] = resultadoQ.getInt("produto_id");
                dadosTabela[lin][2] = resultadoQ.getString("descricao");
                dadosTabela[lin][3] = resultadoQ.getDouble("qtde");
                dadosTabela[lin][4] = resultadoQ.getDouble("valor_item");

                lin++;
            }
        } catch (Exception e) {
            System.out.println("falhas ao popular a tabela...");
            System.out.println(e);
        }

        // configuracoes adicionais no componente tabela
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            // alteracao no metodo que determina a coluna em que o objeto ImageIcon devera aparecer
            @Override
            public Class getColumnClass(int column) {

                if (column == 2) {

                }
                return Object.class;
            }
        });

        // permite selecao de apenas uma linha da tabela
        tabela.setSelectionMode(0);

        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            column = tabela.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(17);
                    break;
                case 1:
                    column.setPreferredWidth(140);
                    break;
            }
        }
    }
}
