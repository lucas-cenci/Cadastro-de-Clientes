package dao;

import apoio.ConexaoBD;
import apoio.IDAOT;
import entidades.Produto;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * Univates - EAD
 * @author Lucas Cenci Beltrame
 */

public class ProdutoDAO implements IDAOT<Produto> {

    @Override
    public String salvar(Produto o) { 
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String atualizar(Produto o) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String excluir(int id) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Produto> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Produto> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Produto consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void popularTabelaproduto(JTable tabela, String criterio) {    // POPULA A TABELA

        ResultSet resultadoQ;

        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[4];
        cabecalho[0] = "Id";
        cabecalho[1] = "Descrição";
        cabecalho[2] = "Valor_Unitário";
        cabecalho[3] = "Qtde_Estoque";

        // cria matriz de acordo com o nº de registros da tabela 
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) "
                    + "FROM produto "
                    + "WHERE "
                    + "descricao ILIKE '%" + criterio + "%' ");

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][4];

        } catch (Exception e) {
            System.out.println("Falha ao consultar produto: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * "
                    + "FROM produto "
                    + "WHERE "
                    + "descricao ILIKE '%" + criterio + "%' ");

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("id");
                dadosTabela[lin][1] = resultadoQ.getString("descricao");
                dadosTabela[lin][2] = resultadoQ.getString("valor_unitario");
                dadosTabela[lin][3] = resultadoQ.getString("qtde_estoque");

                lin++;
            }
        } catch (Exception e) {
            System.out.println("falhas para popular tabela...");
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
  //                case 2:
  //                    column.setPreferredWidth(14);
 //                  break;
            }
        }
    }
}

