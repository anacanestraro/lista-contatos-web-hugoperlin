package ifpr.pgua.eic.tads.contatos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.Categoria;
import ifpr.pgua.eic.tads.contatos.model.Tarefa;
import ifpr.pgua.eic.tads.contatos.model.repositories.CategoriaRepository;
import ifpr.pgua.eic.tads.contatos.model.repositories.TarefaRepository;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class AddTarefaController {
    
    private TarefaRepository repository;
    private CategoriaRepository repositorioCategoria;

    public AddTarefaController(TarefaRepository repository, CategoriaRepository repositorioCategoria){
        this.repository = repository;
        this.repositorioCategoria = repositorioCategoria;
    }

    public Handler get = (Context ctx)->{
        Map<String,Object> model = new HashMap<>();
        
        Resultado<List<Categoria>> resultado = repositorioCategoria.listarTodas();
        
        if(resultado.foiSucesso()){
            model.put("categorias", resultado.comoSucesso().getObj());
        }

        ctx.render("templates/addTarefa.peb",model);
    };

    public Handler post = (Context ctx)->{
        String titulo = ctx.formParam("titulo");
        String descricao = ctx.formParam("descricao");
        
        String categoriaId = ctx.formParam("categoria");

        Resultado<Categoria> resultadoCategoria = repositorioCategoria.getById(Integer.valueOf(categoriaId));

        Categoria categoria = resultadoCategoria.comoSucesso().getObj();
        Resultado<Tarefa> resultado = repository.cadastrar(titulo,descricao,categoria);
        
        Map<String,Object> model = new HashMap<>();
        model.put("resultado", resultado);
        if(resultado.foiErro()){
            model.put("titulo",titulo);
            model.put("descricao",descricao);
            model.put("categoriaId",Integer.valueOf(categoriaId));
            Resultado<List<Categoria>> ret = repositorioCategoria.listarTodas();
            model.put("categorias", ret.comoSucesso().getObj());
        }

        ctx.render("templates/addTarefa.peb",model);
        
    };
}
