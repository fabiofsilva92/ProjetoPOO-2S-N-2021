package conexaoDAO;

import DAO.TreinoDAO;
import entity.Treino;

import java.util.List;

public class TreinoDAOImplements implements TreinoDAO {

    private static final String DBURL = "jdbc:mariadb://localhost/POO_ACADEMIA";
    private static final String DBUSER = "root";
    private static final String DBPASS = "";


    public TreinoDAOImplements() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void adicionar(Treino t) {

    }

    @Override
    public List<Treino> pesquisarPorNome(String nome) {
        return null;
    }

    @Override
    public void atualizar(long id, Treino t) {

    }

    @Override
    public void remover(long id) {

    }
}
