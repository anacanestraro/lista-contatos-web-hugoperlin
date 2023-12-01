package ifpr.pgua.eic.tads.contatos.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.Contato;
import ifpr.pgua.eic.tads.contatos.model.Tarefa;
import ifpr.pgua.eic.tads.contatos.model.repositories.TarefaRepository;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class ListTarefaController {
    
    private TarefaRepository repository;

    public ListTarefaController(TarefaRepository repository){
        this.repository = repository;
    }

    public Handler get = (Context ctx)->{
        
        Resultado<List<Tarefa>> resultado = repository.listarTodas();
        
        Map<String,Object> model = new HashMap<>();

        model.put("resultado", resultado);
        if(resultado.foiSucesso()){
            model.put("lista", resultado.comoSucesso().getObj());
        }

        ctx.render("templates/listTarefas.peb", model);
    };


}
