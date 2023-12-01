package ifpr.pgua.eic.tads.contatos.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.Categoria;
import ifpr.pgua.eic.tads.contatos.model.FabricaConexoes;
import ifpr.pgua.eic.tads.contatos.model.Tarefa;

public class JDBCCategoriaDAO implements CategoriaDAO {
    private FabricaConexoes fabricaConexao;

    public JDBCCategoriaDAO(FabricaConexoes fabricaConexao) {
        this.fabricaConexao = fabricaConexao;
    }


    @Override
    public Resultado<List<Categoria>> listar() {
        ArrayList<Categoria> categorias = new ArrayList<>();

        try (Connection con = fabricaConexao.getConnection();) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM oo_categorias");

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");

                Categoria categoria = new Categoria(id, titulo, descricao);

                categorias.add(categoria);
            }
            con.close();
            return Resultado.sucesso("Categorias carregadas", categorias);
        } catch (SQLException e) {
            return Resultado.erro("Problema ao fazer seleção!! " + e.getMessage());
        }

    }


    @Override
    public Resultado<Categoria> buscarCategoriaTarefa(Tarefa tarefa) {
        try (Connection con = fabricaConexao.getConnection();) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM oo_categorias inner join oo_tarefas ON oo_categorias.id=oo_tarefas.idCategoria WHERE oo_tarefas.id=?");

            pstm.setInt(1, tarefa.getId());

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("oo_categorias.id");
                String titulo = rs.getString("oo_categorias.titulo");
                String descricao = rs.getString("oo_categorias.descricao");

                Categoria categoria = new Categoria(id, titulo, descricao);
                return Resultado.sucesso("Categorias carregadas", categoria);
        
            }
            return Resultado.erro("Categorias não encontrada");
        
        } catch (SQLException e) {
            return Resultado.erro("Problema ao fazer seleção!! " + e.getMessage());
        }
    
    }

}
