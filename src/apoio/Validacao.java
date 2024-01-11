package apoio;

import javax.swing.JFormattedTextField;

/**
 * Univates - EAD
 * @author Lucas Cenci Beltrame
 */

public class Validacao {   
 
                       //REGIAO DE VALIDACOES DOS CAMPOS   
  
 private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
   private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static boolean validarCPF(String cpf) {
    
        if ((cpf == null) || (cpf.length() != 11)) {
            return false;
        }
        Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
    }
 
    public static boolean validarCNPJ(String cnpj) {
       
        if ((cnpj == null) || (cnpj.length() != 14)) {
            return false;
        }
        Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
    }

    public static boolean validarDataDMA (int d, int m, int a) {
        
        boolean correto = true;
        
        int[] dias = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        if (a < 0 || m < 1 || m > 12) {
            correto = false;
        } else {
            // valida o dia
           if (a % 4 == 0 && (a % 100 != 0 || a % 400 == 0)) {
              dias[1] = 29;
            }
            if (d < 1 || d > dias[m - 1]) {
                correto = false;
            }
        }
       return (correto);
    }

    public static boolean validarDataFormatada (String dataComFormato) {
         
    //Remover espaços em branco desnecessarios
    dataComFormato = dataComFormato.trim();

    //Verifica se a string esta vazia
    if (dataComFormato.isEmpty()) {
        return false;
    }

    String[] data = dataComFormato.split("/");

    if (data.length != 3) {
        // A string dataComFormato não possui os elementos esperados
        return false;
    }

    int dia;
    int mes;
    int ano;

    try {
        dia = Integer.parseInt(data[0]);
        mes = Integer.parseInt(data[1]);
        ano = Integer.parseInt(data[2]);
    } catch (NumberFormatException e) {
        return false;
    }

    return validarDataDMA(dia, mes, ano);
}

    public static boolean validarDataHoraFormatada(String dataHoraComFormato) {

        dataHoraComFormato = dataHoraComFormato.trim();

        if (dataHoraComFormato.isEmpty()) {
            return false;
        }

        String[] partes = dataHoraComFormato.split(" ");
        if (partes.length != 2) {
         
            return false;
        }

        String dataStr = partes[0];
        String horaStr = partes[1];

        if (!validarDataFormatada(dataStr)) {
            return false;
        }

        if (!validarHoraFormatada(horaStr)) {
            return false;
        }

        return true;
    }
    
    public static boolean validarHoraFormatada(String horaComFormato) {

        // remove os espaços em brancos que sao desnecessarios
        // somente para evitar erros
        horaComFormato = horaComFormato.trim();

        if (horaComFormato.isEmpty()) {
            return false;
        }

        String[] horaMinuto = horaComFormato.split(":");

        if (horaMinuto.length != 2) {
            return false;
        }

        int h;
        int m;

        try {
            h = Integer.parseInt(horaMinuto[0]);
            m = Integer.parseInt(horaMinuto[1]);
        } catch (NumberFormatException e) {
            return false;
        }

        // verifica se a hora e o minuto estão dentro dos limites validos
        if (h < 0 || h > 23 || m < 0 || m > 59) {
            return false;
        }

        return true;
    }
    
    public static boolean validarTelefone(JFormattedTextField campo) {
       
        if (campo.getText().trim().length() < 15) {
            return false;
        } else {
            return true;
       }
    }
}
