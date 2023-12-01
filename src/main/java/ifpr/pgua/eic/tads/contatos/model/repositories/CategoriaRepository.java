package ifpr.pgua.eic.tads.contatos.model.repositories;

import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.Categoria;

public interface CategoriaRepository {
    Resultado<Categoria> getById(int id);
    Resultado<List<Categoria>> listarTodas();
}
