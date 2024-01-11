package entidades;

/**
 * Univates - EAD
 * @author Lucas Cenci Beltrame
 */

public class Endereco {                   //REGIAO DOS GETTERS AND SETTERS
    private int id;
    private String descricao;
    private String cep;

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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
}
