package apoio;

import java.util.ArrayList;

/**
 * Univates - EAD
 * @author Lucas Cenci Beltrame
 */

public interface IDAOT <T> {   //criacao dos dados
    
    public String salvar(T o);
    
    public String atualizar(T o);
    
    public String excluir(int id);
            
    public ArrayList<T> consultarTodos();
            
    public ArrayList<T> consultar(String criterio);
            
    public T consultarId(int id);       
}
