package ifpr.pgua.eic.tads.contatos.model.repositories;

import java.util.ArrayList;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.Categoria;
import ifpr.pgua.eic.tads.contatos.model.daos.CategoriaDAO;

public class ImplCategoriaRepository implements CategoriaRepository{

    private CategoriaDAO dao;
    private List<Categoria> lista;
    

    public ImplCategoriaRepository(CategoriaDAO dao) {
        this.dao = dao;
        this.lista = new ArrayList<>();
    }

    @Override
    public Resultado<Categoria> getById(int id) {
        if(lista.size() != 0){
            Categoria ret = lista.stream().filter(categoria->categoria.getId()==id).findFirst().get();
            return Resultado.sucesso("Categoria encontrada", ret);
        }
        return Resultado.erro("Problema ao buscar categoria!");
    }

    @Override
    public Resultado<List<Categoria>> listarTodas() {
        var resultado = dao.listar();
        
        if(resultado.foiSucesso()){
            lista.clear();
            lista.addAll(resultado.comoSucesso().getObj());
        }

        return dao.listar();
    }
    
}
