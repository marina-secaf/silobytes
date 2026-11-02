package util;

/**
 * Exceção personalizada para quando se tenta acessar um objeto que não existe. 
 * @author José
 */
public class ExcecaoObjetoNaoEncontrado extends Exception {
    
    public ExcecaoObjetoNaoEncontrado(){
        super("Objeto não encontrado"); //mensagem padrão
    }
    
    public ExcecaoObjetoNaoEncontrado(String mensagem){
        super(mensagem);
    }
}
