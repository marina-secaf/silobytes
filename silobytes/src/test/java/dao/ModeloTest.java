package dao;

import java.util.Date;
import modelo.Armazenagem;
import modelo.Produtor;
import modelo.Silo;
import modelo.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ModeloTest {

    @Test
    public void testUsuario() throws Exception {
        Dao<Usuario> dao = new Dao(Usuario.class);
        dao.inserir(new Usuario("teste.operador", "Operador Teste", "1234"));

        Usuario lido = dao.buscarPorChave("login", "teste.operador");
        Assertions.assertEquals("Operador Teste", lido.getNome());
        Assertions.assertEquals("1234", lido.getSenha());

        dao.excluir("login", "teste.operador");
    }

    @Test
    public void testProdutor() throws Exception {
        Dao<Produtor> dao = new Dao(Produtor.class);
        dao.inserir(new Produtor("000.000.000-00", "Produtor Teste", "(44) 99999-0000"));

        Produtor lido = dao.buscarPorChave("cpfCnpj", "000.000.000-00");
        Assertions.assertEquals("Produtor Teste", lido.getNome());

        dao.excluir("cpfCnpj", "000.000.000-00");
    }

    @Test
    public void testSilo() throws Exception {
        Dao<Silo> dao = new Dao(Silo.class);
        Silo s = new Silo("Silo Teste", 1000);
        s.setQuantidadeAtual(450);
        dao.inserir(s);

        Silo lido = dao.buscarPorChave("nome", "Silo Teste");
        Assertions.assertEquals(1000, lido.getCapacidadeMaxima());
        Assertions.assertEquals(550, lido.espacoDisponivel());
        Assertions.assertEquals(0.45, lido.percentualOcupado(), 0.0001);

        dao.excluir("nome", "Silo Teste");
    }

    @Test
    public void testArmazenagem() throws Exception {
        Dao<Armazenagem> dao = new Dao(Armazenagem.class);
        Produtor p = new Produtor("111.111.111-11", "Produtor Armaz", "(44) 98888-0000");
        Date entrada = new Date();
        Armazenagem a = new Armazenagem(p, "Silo Teste", 120.5, entrada);
        dao.inserir(a);

        Armazenagem lida = dao.buscarPorChave("codigo", a.getCodigo());
        Assertions.assertEquals("Produtor Armaz", lida.getProdutor().getNome());
        Assertions.assertEquals(120.5, lida.getQuantidadeToneladas());
        Assertions.assertEquals(entrada, lida.getDataEntrada());
        Assertions.assertNull(lida.getDataSaida());
        Assertions.assertFalse(lida.isPago());

        dao.excluir("codigo", a.getCodigo());
    }
}