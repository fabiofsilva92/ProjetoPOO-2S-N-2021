package conexaoDAO;

import DAO.AgendamentoDAO;
import entity.Agendamento;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAOImplements implements AgendamentoDAO {

    private static final String DBURL = "jdbc:mariadb://localhost/POO_ACADEMIA";
    private static final String DBUSER = "root";
    private static final String DBPASS = "";


    public AgendamentoDAOImplements() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void adicionar(Agendamento a) {

        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

            String sql =
                    "INSERT INTO agendamento(id, nome, sobreNome, aula, dataAgendamento, horario)" +
                            "VALUES(?, ?, ?, ?, ?, ?)";

            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, a.getId());
            stmt.setString(2, a.getNome());
            stmt.setString(3, a.getSobreNome());
            stmt.setString(4, a.getAula());
            stmt.setDate(5, Date.valueOf(a.getDataAgendamento()));
            stmt.setString(6, a.getHorario());

            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Agendamento> pesquisarPorNome(String nome) {
        List<Agendamento> listaEncontrados = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "SELECT * FROM agendamento WHERE nome LIKE '%" + nome + "%'";
            System.out.println(sql);

            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Agendamento a = new Agendamento();

                a.setId(rs.getLong("id"));
                a.setNome(rs.getString("nome"));
                a.setSobreNome(rs.getString("sobreNome"));
                a.setDataAgendamento(rs.getDate("dataAgendamento").toLocalDate());
                a.setHorario(rs.getString("horario"));

                listaEncontrados.add(a);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaEncontrados;
    }

    @Override
    public void atualizar(long id, Agendamento a) {   }

    @Override
    public void remover(long id) {

        try ( Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS)){

            String sql = "DELETE FROM agendamento WHERE id = ? ";

            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
