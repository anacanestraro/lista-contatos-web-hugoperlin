package ifpr.pgua.eic.tads.contatos.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.Contato;
import ifpr.pgua.eic.tads.contatos.model.repositories.ContatoRepository;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class ListController {
    
    private ContatoRepository repositorio;

    public ListController(ContatoRepository repositorio){
        this.repositorio = repositorio;
    }

    public Handler get = (Context ctx)->{
        
        Resultado<List<Contato>> resultado = repositorio.listarTodos();
        
        Map<String,Object> model = new HashMap<>();

        model.put("resultado", resultado);
        if(resultado.foiSucesso()){
            model.put("lista", resultado.comoSucesso().getObj());
        }

        ctx.render("templates/listContatos.peb", model);
        
    };


}
