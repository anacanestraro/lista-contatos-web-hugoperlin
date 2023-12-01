package ifpr.pgua.eic.tads.contatos.model.daos;

import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.Tarefa;

public interface TarefaDAO {
    Resultado<Tarefa> criar(Tarefa tarefa);
    Resultado<List<Tarefa>> listar();
}
