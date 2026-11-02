package dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Veiculo;
import util.ExcecaoObjetoNaoEncontrado;

/**
 *
 * @author Usuário
 */
public class DaoTest {
    
    public DaoTest() {
    }

//    @org.junit.jupiter.api.Test
//    public void testInserir() {       
//        Veiculo v = new Veiculo("ABC5E34", "BYD", "DinDin");      
//        Dao<Veiculo> dao = new Dao(Veiculo.class);
//        dao.inserir(v);
//    }
    
//    @org.junit.jupiter.api.Test
//    public void testExcluir(){
//        Dao<Veiculo> dao = new Dao(Veiculo.class);
//        try {
//            dao.excluir("placa", "ZZZ9865x");
//        } catch (ExcecaoObjetoNaoEncontrado ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
    
//    @org.junit.jupiter.api.Test
//    public void testAlterar(){
//        Dao<Veiculo> dao = new Dao(Veiculo.class);
//        Veiculo v = new Veiculo("ABC5E34", "BYD", "Top");
//        try {
//            dao.alterar("placa", "ABC5E34", v);
//        } catch (ExcecaoObjetoNaoEncontrado ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
    
//    @org.junit.jupiter.api.Test
//    public void listar(){
//        Dao<Veiculo> dao = new Dao(Veiculo.class);
//        List<Veiculo> lista = dao.listarTodos(); 
//        
//        for(Veiculo v : lista){
//            System.out.println(v.getPlaca());
//            System.out.println(v.getModelo());
//        }
//        
//    }
    
    @org.junit.jupiter.api.Test
    public void testFiltrar(){
        Dao<Veiculo> dao = new Dao(Veiculo.class);
        List<Veiculo> lista = dao.filtrar("marca", "Ford");
        for(Veiculo v : lista){
            System.out.println(v.getPlaca());
            System.out.println(v.getModelo());
        }
    }
    
    
}
