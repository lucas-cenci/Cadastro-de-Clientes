package dao;

import apoio.ConexaoBD;
import apoio.Formatacao;
import apoio.IDAOT;
import entidades.Pedido;
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

public class PedidoDAO implements IDAOT<Pedido> {

    @Override
    public String salvar(Pedido o) {        //insercao dos dados fornecidos
        String retornoConsulta = "";

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "insert into pedido "
                    + "values"
                    + "(default, "
                    + "'" + o.getData() + "', "
                    + "'" + o.getEndereco_entrega()+ "', "
                    + "'" + o.getObservacao()+ "', "
                    + "" + o.getCliente_id() + ") returning id";

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            if (retorno.next()) {
                retornoConsulta = retorno.getString(1);
            }

        } catch (Exception e) {
            System.out.println("Falha ao inserir Pedido: " + e);
            retornoConsulta = "ERROR:" + e.toString();
        }
        return retornoConsulta;
    }

    @Override
    public String atualizar(Pedido o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Pedido> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Pedido> consultar(String criterio) {
      ArrayList<Pedido> pedidos = new ArrayList();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select pedido.*, clt.nome\n"
                    + "from pedido, cliente clt\n"
                    + "where\n"
                    + "pedido.cliente_id = clt.id;";

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            while (retorno.next()) {
                Pedido pedido = new Pedido();

                pedido.setId(retorno.getInt("id"));
                pedido.setData(retorno.getString("data_os"));
                pedido.setObservacao(retorno.getString("observacao"));
                pedido.setCliente_id(retorno.getInt("cliente_id"));

                pedidos.add(pedido);
            }

        } catch (Exception e) {
            System.out.println("Falha ao consultar Pedido: " + e);
        }
        return pedidos;
    }

    @Override
    public Pedido consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void popularTabelapedido(JTable tabela, String dataInicio, String dataFim) {
    
    ResultSet resultadoQ;

        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[6];
        cabecalho[0] = "Id";
        cabecalho[1] = "Data";
        cabecalho[2] = "Cliente Id";
        cabecalho[3] = "Cliente Nome";
        cabecalho[4] = "Total";
        cabecalho[5] = "Observação";

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "select count(*) "
                    + "from pedido, cliente clt\n"
                    + "where\n"
                    + "pedido.cliente_id = clt.id and\n"
                    + "pedido.data >= '" + dataInicio + "' and "
                    + "pedido.data <= '" + dataFim + "';");

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][6];

        } catch (Exception e) {
            System.out.println("Falha ao consultar pedido: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "select pedido.*, clt.nome, (select sum (qtde * valor_item) as total from item_pedido where pedido_id = pedido.id) "
                    + "from pedido, cliente clt\n"
                    + "where\n"
                    + "pedido.cliente_id = clt.id and\n"
                    + "pedido.data >= '" + dataInicio + "' and "
                    + "pedido.data <= '" + dataFim + "' "
                    + "order by pedido.id");

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("id");
                dadosTabela[lin][1] = Formatacao.ajustaDataDMA(resultadoQ.getString("data"));
                dadosTabela[lin][2] = resultadoQ.getString("cliente_id");
                dadosTabela[lin][3] = resultadoQ.getString("nome");
                dadosTabela[lin][4] = resultadoQ.getDouble("total");
                dadosTabela[lin][5] = resultadoQ.getString("observacao");

                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela...");
            System.out.println(e);
        }

        // configuracoes adicionais no componente tabela
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column) {
                return false;
                /*  
                 if (column == 3) {  // apenas a coluna 3 sera editavel
                 return true;
                 } else {
                 return false;
                 }
                 */
            }

            // alteracao no metodo que determina a coluna em que o objeto ImageIcon devera aparecer
            @Override
            public Class getColumnClass(int column) {

                if (column == 2) {
//                    return ImageIcon.class;
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
                    column.setPreferredWidth(70);
                    break;
                case 2:
                    column.setPreferredWidth(30);
                    break;
                case 3:
                    column.setPreferredWidth(200);
                    break;
                case 4:
                    column.setPreferredWidth(60);
                    break;
                case 5:
                    column.setPreferredWidth(50);
                    break;
            }
        }
        // renderizacao das linhas da tabela = mudar a cor
//        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value,
//                    boolean isSelected, boolean hasFocus, int row, int column) {
//                super.getTableCellRendererComponent(table, value, isSelected,
//                        hasFocus, row, column);
//                if (row % 2 == 0) {
//                    setBackground(Color.GREEN);
//                } else {
//                    setBackground(Color.LIGHT_GRAY);
//                }
//                return this;
//            }
//        });
    }
}
