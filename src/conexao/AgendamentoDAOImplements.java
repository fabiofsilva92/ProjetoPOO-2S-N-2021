package conexao;

import DAO.AgendamentoDAO;
import entity.Agendamento;

import java.util.List;

public class AgendamentoDAOImplements implements AgendamentoDAO {

    private static final String DBURL = "jdbc:mariadb://localhost/POO_ACADEMIA";
    private static final String DBUSER = "root";
    private static final String DBPASS = "";


    public AgendamentoDAOImplements(){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void adicionar(Agendamento a) {

    }

    @Override
    public List<Agendamento> pesquisarPorNome(String nome) {
        return null;
    }

    @Override
    public void atualizar(long id, Agendamento a) {

    }

    @Override
    public void remover(long id) {

    }
}
