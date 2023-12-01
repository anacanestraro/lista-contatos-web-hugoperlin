package ifpr.pgua.eic.tads.contatos.model.repositories;

import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.Categoria;
import ifpr.pgua.eic.tads.contatos.model.Tarefa;

public interface TarefaRepository {
    Resultado<Tarefa> cadastrar(String titulo, String descricao, Categoria categoria);
    Resultado<List<Tarefa>> listarTodas();
}
