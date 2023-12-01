package ifpr.pgua.eic.tads.contatos.model.repositories;

import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.Categoria;
import ifpr.pgua.eic.tads.contatos.model.Tarefa;
import ifpr.pgua.eic.tads.contatos.model.daos.CategoriaDAO;
import ifpr.pgua.eic.tads.contatos.model.daos.TarefaDAO;

public class ImplTarefaRepository implements TarefaRepository{
    private TarefaDAO dao;
    private CategoriaDAO categoriaDao;


    public ImplTarefaRepository(TarefaDAO dao, CategoriaDAO categoriaDao) {
        this.dao = dao;
        this.categoriaDao = categoriaDao;
    }

    @Override
    public Resultado<Tarefa> cadastrar(String titulo, String descricao, Categoria categoria) {
        if (titulo.isBlank() || titulo.isEmpty()) {
            return Resultado.erro("Nome inválido!");
        }

        if (descricao.isBlank() || descricao.isEmpty()) {
            return Resultado.erro("Telefone inválido!");
        }

        Tarefa tarefa = new Tarefa(titulo, descricao,categoria);
        return dao.criar(tarefa);
    }

    @Override
    public Resultado<List<Tarefa>> listarTodas() {
        Resultado<List<Tarefa>> resultado = dao.listar();

        if(resultado.foiSucesso()){
            List<Tarefa> lista = resultado.comoSucesso().getObj();
            for(Tarefa t:lista){
                Resultado<Categoria> res2 = categoriaDao.buscarCategoriaTarefa(t);
                if(res2.foiErro()){
                    return res2.comoErro();
                }else{
                    t.setCategoria(res2.comoSucesso().getObj());
                }
            }
        }
        return resultado;
    }
    
}
