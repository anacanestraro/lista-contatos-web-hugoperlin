package ifpr.pgua.eic.tads.contatos;

import ifpr.pgua.eic.tads.contatos.controllers.AddController;
import ifpr.pgua.eic.tads.contatos.controllers.AddTarefaController;
import ifpr.pgua.eic.tads.contatos.controllers.IndexController;
import ifpr.pgua.eic.tads.contatos.controllers.ListController;
import ifpr.pgua.eic.tads.contatos.controllers.ListTarefaController;
import ifpr.pgua.eic.tads.contatos.model.FabricaConexoes;
import ifpr.pgua.eic.tads.contatos.model.daos.CategoriaDAO;
import ifpr.pgua.eic.tads.contatos.model.daos.ContatoDAO;
import ifpr.pgua.eic.tads.contatos.model.daos.JDBCCategoriaDAO;
import ifpr.pgua.eic.tads.contatos.model.daos.JDBCContatoDAO;
import ifpr.pgua.eic.tads.contatos.model.daos.JDBCTarefaDAO;
import ifpr.pgua.eic.tads.contatos.model.daos.TarefaDAO;
import ifpr.pgua.eic.tads.contatos.model.repositories.CategoriaRepository;
import ifpr.pgua.eic.tads.contatos.model.repositories.ContatoRepository;
import ifpr.pgua.eic.tads.contatos.model.repositories.ImplCategoriaRepository;
import ifpr.pgua.eic.tads.contatos.model.repositories.ImplContatoRepository;
import ifpr.pgua.eic.tads.contatos.model.repositories.ImplTarefaRepository;
import ifpr.pgua.eic.tads.contatos.model.repositories.TarefaRepository;
import ifpr.pgua.eic.tads.contatos.utils.JavalinUtils;
import io.javalin.Javalin;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Javalin app = JavalinUtils.makeApp(8080);
        
        ContatoDAO contatoDao = new JDBCContatoDAO(FabricaConexoes.getInstance());
        ContatoRepository contatoRepository = new ImplContatoRepository(contatoDao);

        CategoriaDAO categoriaDao = new JDBCCategoriaDAO(FabricaConexoes.getInstance());
        CategoriaRepository categoriaRepository = new ImplCategoriaRepository(categoriaDao);

        TarefaDAO tarefaDao = new JDBCTarefaDAO(FabricaConexoes.getInstance());
        TarefaRepository tarefaRepository = new ImplTarefaRepository(tarefaDao,categoriaDao);

        IndexController indexController = new IndexController();
        AddController addController = new AddController(contatoRepository);
        ListController listController = new ListController(contatoRepository);

        AddTarefaController addTarefaController = new AddTarefaController(tarefaRepository,categoriaRepository);
        ListTarefaController listTarefaController = new ListTarefaController(tarefaRepository);


        app.get("/",indexController.get);
        app.get("/add",addController.get);
        app.post("/add",addController.post);
        app.get("/list",listController.get);

        app.get("/addTarefa",addTarefaController.get);
        app.post("/addTarefa",addTarefaController.post);
        app.get("/listTarefa",listTarefaController.get);
        
    }
}
