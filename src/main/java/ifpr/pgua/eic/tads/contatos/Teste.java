package ifpr.pgua.eic.tads.contatos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.Categoria;
import ifpr.pgua.eic.tads.contatos.model.FabricaConexoes;
import ifpr.pgua.eic.tads.contatos.model.Tarefa;
import ifpr.pgua.eic.tads.contatos.model.daos.CategoriaDAO;
import ifpr.pgua.eic.tads.contatos.model.daos.JDBCCategoriaDAO;

public class Teste {
    

    public static void main(String[] args) {
        CategoriaDAO dao = new JDBCCategoriaDAO(FabricaConexoes.getInstance());

        Resultado<Categoria> res = dao.buscarCategoriaTarefa(new Tarefa(12, null, null));
        System.out.println(res.comoSucesso().getObj().getTitulo());
    }
}
