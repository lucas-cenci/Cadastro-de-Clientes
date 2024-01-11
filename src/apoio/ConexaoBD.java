package apoio;

/**
 * Univates - EAD
 *
 * @author Lucas Cenci Beltrame
 */
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoBD {

    private static ConexaoBD instancia = null;
    private Connection conexao = null;

    public ConexaoBD() {  //conexao ao banco de dados

        try {
            String dbdriver = "org.postgresql.Driver";
            String dburl = "jdbc:postgresql://localhost:5432/VendaDeMercadorias";
            String dbuser = "postgres";
            String dbsenha = "postgres";

            //carregar driver banco de dados
            Class.forName(dbdriver);

            if (dbuser.length() != 0) // conexao usuario e senha
            {
                conexao = DriverManager.getConnection(dburl, dbuser, dbsenha);
            } else // conexao sem usuario e senha                  
            {
                conexao = DriverManager.getConnection(dburl);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    //retorna instancia
    public static ConexaoBD getInstance() {

        if (instancia == null) {
            instancia = new ConexaoBD();
        }
        return instancia;
    }

    //retorna conexao
    public Connection getConnection() {

        if (conexao == null) {
            throw new RuntimeException("conexao==null");
        }
        return conexao;
    }

    //efetua fechamento da conexao
    public void ShutDown() {

        try {
            conexao.close();
            instancia = null;
            conexao = null;
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void exportToCSV(String sql, String nomearquivo) {          // arquivo csv
        
        try (Statement statement = conexao.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);
             FileWriter writer = new FileWriter(nomearquivo)) {

            // Escreve o cabeçalho do arquivo CSV e os nomes
            writer.append("nome;e_mail;cpf;telefone\n");

            // Percorre os resultados da consulta e escreve no arquivo CSV
            while (resultSet.next()) {
                String coluna1 = resultSet.getString("nome");
                String coluna2 = resultSet.getString("e_mail");
                String coluna3 = resultSet.getString("cpf");
                String coluna4 = resultSet.getString("telefone");

                // Escreve os separadores de linha
                writer.append(coluna1).append(";").append(coluna2).append(";").append(coluna3).append(";").append(coluna4).append("\n");
            }

            System.out.println("Exportação para CSV concluída com sucesso.");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}

