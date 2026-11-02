package dao;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import util.ExcecaoObjetoNaoEncontrado;

/**
 * Classe responsável pela persistência de objetos.
 *
 * @author José
 * @param <T> Parâmetro de tipo: classe do objeto a ser persistido.
 */
// DAO - Data Access Object 
public class Dao<T> {

    private final String URI = "mongodb://localhost:27017";
    private final String DATABASE = "silobytes";   // Dê o nome de sua preferência 
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final String colecao;  // nome da coleção 
    private final MongoCollection<T> collection;

    public Dao(Class<T> classe) {
        this.colecao = classe.getName();
        mongoClient = MongoClients.create(URI);
        CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                org.bson.codecs.configuration.CodecRegistries.
                        fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        database = mongoClient.getDatabase(DATABASE).withCodecRegistry(pojoCodecRegistry);
        collection = database.getCollection(colecao, classe);
    }

//    /**
//     * 
//     * @param chave O nome do atributo pelo qual o objeto vai ser buscado, ex: codigo. 
//     * @param valor O valor do atributo identificador do objeto a ser alterado, exemplo: 20 (vai buscar o objeto cujo código seja 20).
//     * @param novo O objeto com os novos valores que devem substituir os antigos. 
//     */
    public void alterar(String identificador, String valorIdentificador, T valorAlterado) throws ExcecaoObjetoNaoEncontrado {
        UpdateResult resultado = collection.replaceOne(new Document(identificador,
                valorIdentificador), valorAlterado);

        if (resultado.wasAcknowledged()) {
            long documentosModificados = resultado.getModifiedCount();
            if(documentosModificados == 0){
                throw new ExcecaoObjetoNaoEncontrado();
            }
        } else {
            throw new ExcecaoObjetoNaoEncontrado();
        }
    }

    /**
     * Apaga um objeto no banco.
     *
     * @param chave O nome do atributo pelo qual o objeto vai ser buscado, ex:
     * codigo.
     * @param valor O valor do atributo identificador do objeto a ser alterado,
     * exemplo: 20 (vai excluir o objeto cujo código seja 20).
     * @return True se um objeto foi excluído ou false caso contrário.
     * @throws util.ExcecaoObjetoNaoEncontrado
     */
    public boolean excluir(String chave, String valor) throws ExcecaoObjetoNaoEncontrado {
        Document filter = new Document(chave, valor);
        DeleteResult result = collection.deleteOne(filter);
        if (result.getDeletedCount() == 0) {
            throw new ExcecaoObjetoNaoEncontrado();
        }
        return true;
    }

    /**
     * Retorna o objeto cuja chave for igual ao valor passado.
     *
     * @param chave o campo pelo qual o objeto vai ser buscado
     * @param valor o valor da chave
     * @return O objeto correspondente à chave ou null caso não exista.
     * @throws util.ExcecaoObjetoNaoEncontrado
     */
    public T buscarPorChave(String chave, String valor) throws ExcecaoObjetoNaoEncontrado {
        T retorno = collection.find(new Document(chave, valor)).first();
        if (retorno == null) {
            throw new ExcecaoObjetoNaoEncontrado();
        }
        return retorno;
    }

    /**
     * Retorna o objeto cuja chave for igual ao valor passado.
     *
     * @param chave o campo pelo qual o objeto vai ser buscado
     * @param valor o valor da chave
     * @return O objeto correspondente à chave ou null caso não exista.
     * @throws util.ExcecaoObjetoNaoEncontrado
     */
    public T buscarPorChave(String chave, Integer valor) throws ExcecaoObjetoNaoEncontrado {
        T retorno = collection.find(new Document(chave, valor)).first();
        if (retorno == null) {
            throw new ExcecaoObjetoNaoEncontrado();
        }
        return retorno;
    }

    public void inserir(T objeto) {
        collection.insertOne(objeto);
    }

    /**
     * Retorna todos os objetos de uma coleção do tipo T.
     *
     * @return
     */
    public List<T> listarTodos() {
        ArrayList<T> todos = new ArrayList();
        MongoCursor<T> cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            T elemento = (T) cursor.next();
            todos.add(elemento);
        }
        return todos;
    }

    /**
     * Retorna os objetos que atendam um deteminado critério, por exemplo,
     * veículos com determinada placa.
     *
     * @param campoDaColecao: o nome do atributo do objeto. Exemplo: "nome"
     * @param criterio: o valor do atributo. Exemplo: "Gasparzinho".
     * @return uma lista de todos os objetos que atendam ao critério.
     */
    public List<T> filtrar(String campoDaColecao, String criterio) {
        Bson filtro = Filters.eq(campoDaColecao, criterio);
        FindIterable<T> resultados = collection.find(filtro);
        // converte em List/ArrayList        
        List<T> retorno = new ArrayList();
        resultados.into(retorno);
        return retorno;
    }

    /**
     * Filtro com mais de um critério. Filters.eq() verifica igualdade. Encontra
     * o objeto que satisfaz os dois critérios de igualdade.
     *
     * @param campoDaColecao1: o nome do atributo do objeto. Exemplo: "nome"
     * @param criterio1: o valor do atributo. Exemplo: "Gasparzinho".
     * @param campoDaColecao2: o nome do atributo do objeto. Exemplo: "cidade"
     * @param criterio2: o valor do atributo. Exemplo: "Bandeirantes".
     * @return
     */
    public List<T> filtrar(String campoDaColecao1, String criterio1,
            String campoDaColecao2, String criterio2) {
        Bson filtro1 = Filters.eq(campoDaColecao1, criterio1);
        Bson filtro2 = Filters.eq(campoDaColecao2, criterio2);
        Bson filtros = Filters.and(filtro1, filtro2);
        FindIterable<T> resultados = collection.find(filtros);
        // converte em List/ArrayList        
        List<T> retorno = new ArrayList();
        resultados.into(retorno);
        return retorno;
    }

}
