package entidades;

/**
 * Univates - EAD
 * @author Lucas Cenci Beltrame
 */

public class Produto {                   //REGIAO DOS GETTERS AND SETTERS
    private int id;
    private String descricao;
    private double valor_unitario;
    private int qtde_estoque;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public int getQtde_estoque() {
        return qtde_estoque;
    }

    public void setQtde_estoque(int qtde_estoque) {
        this.qtde_estoque = qtde_estoque;
    }

  
}
