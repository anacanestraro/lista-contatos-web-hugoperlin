package ifpr.pgua.eic.tads.contatos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.Categoria;
import ifpr.pgua.eic.tads.contatos.model.Contato;
import ifpr.pgua.eic.tads.contatos.model.repositories.CategoriaRepository;
import ifpr.pgua.eic.tads.contatos.model.repositories.ContatoRepository;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class AddController {
    
    private ContatoRepository repositorio;

    public AddController(ContatoRepository repositorio){
        this.repositorio = repositorio;
    }

    public Handler get = (Context ctx)->{


        ctx.render("templates/add.peb");
    };

    public Handler post = (Context ctx)->{
        String nome = ctx.formParam("nome");
        String email = ctx.formParam("email");
        String telefone = ctx.formParam("telefone");
        
        Resultado<Contato> resultado = repositorio.cadastrar(nome, email, telefone);
        
        Map<String,Object> model = new HashMap<>();
        model.put("resultado", resultado);
        if(resultado.foiErro()){
            model.put("nome",nome);
            model.put("email",email);
            model.put("telefone",telefone);
            
        }

        ctx.render("templates/add.peb",model);
    };
}
