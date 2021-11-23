package conexaoDAO;

import DAO.EventoDAO;
import entity.Evento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoDAOImplements implements EventoDAO {


    private static final String DBURL = "jdbc:mariadb://localhost/POO_ACADEMIA";
    private static final String DBUSER = "root";
    private static final String DBPASS = "";

    public EventoDAOImplements(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void adicionar(Evento e) {

        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

            String sql =
                    "INSERT INTO evento(id, nomeEvento, data, horario, duracao)" +
                            "VALUES(?, ?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, e.getId());
            stmt.setString(2, e.getNomeEvento());
            stmt.setDate(3, Date.valueOf(e.getData()));
            stmt.setString(4, e.getHorario());
            stmt.setDouble(5, e.getDuracao());

            stmt.executeUpdate();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public List<Evento> pesquisarPorNome(String s) {

        List<Evento> encontrados = new ArrayList<>();

        try{
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

            String query = "SELECT * FROM evento WHERE nomeEvento like '%"+s+"%'";
            System.out.println(query);

            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Evento e = new Evento();
                e.setId(rs.getLong("id"));
                e.setNomeEvento(rs.getString("nomeEvento"));
                e.setData(rs.getDate("data").toLocalDate());
                e.setHorario(rs.getString("horario"));
                e.setDuracao(rs.getDouble("duracao"));

                encontrados.add(e);
            }
            con.close();

        }catch(Exception ex ){
            ex.printStackTrace();
        }

        return encontrados;

    }

    @Override
    public void atualizar(long id, Evento e) {

    }

    @Override
    public void remover(long id) {
        try(Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS)){
            String sql = "DELETE FROM evento WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
