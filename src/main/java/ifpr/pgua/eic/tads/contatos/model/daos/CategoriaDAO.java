package ifpr.pgua.eic.tads.contatos.model.daos;

import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.Categoria;
import ifpr.pgua.eic.tads.contatos.model.Tarefa;

public interface CategoriaDAO {
    Resultado<Categoria> buscarCategoriaTarefa(Tarefa tarefa);
    Resultado<List<Categoria>> listar();
}
