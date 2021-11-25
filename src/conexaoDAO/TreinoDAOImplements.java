package conexaoDAO;

import DAO.TreinoDAO;
import entity.Treino;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreinoDAOImplements implements TreinoDAO {

    private static final String DBURL = "jdbc:mariadb://localhost/POO_ACADEMIA";
    private static final String DBUSER = "root";
    private static final String DBPASS = "123456";


    public TreinoDAOImplements() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void adicionar(Treino t) {

        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

            String sql =
                    "INSERT INTO treino(id, nome, sobrenome, tipo, musculo, aparelho, repeticoesQTD, series)" +
                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

            System.out.println("salvando Treino " + sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, t.getId());
            stmt.setString(2, t.getNome());
            stmt.setString(3, t.getSobrenome());
            stmt.setString(4, t.getTipo());
            System.out.println("Esta Chegando isto " + t.getTipo());
            stmt.setString(5, t.getMusculo());
            stmt.setInt(6, t.getAparelho());
            stmt.setInt(7, t.getRepeticoes());
            stmt.setInt(8, t.getSeries());

            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Treino> pesquisarPorNome(String nome) {
        List<Treino> listaEncontrados = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "SELECT * FROM cliente WHERE nome LIKE '%" + nome + "%'";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Treino t = new Treino();

                t.setId(rs.getLong("id"));
                t.setNome(rs.getString("nome"));
                t.setSobrenome(rs.getString("sobrenome"));

                listaEncontrados.add(t);

            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaEncontrados;
    }

    @Override
    public List<Treino> pesquisarPorId(Long id) {
        List<Treino> listaEncontrados = new ArrayList<>();
        if(id != 0) {
            try {
                Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

                String sql2 = "SELECT * FROM treino WHERE id = " + id;
                System.out.println(sql2);
                PreparedStatement stmt2 = con.prepareStatement(sql2);

                ResultSet rs = stmt2.executeQuery();

                while (rs.next()) {
                    Treino t = new Treino();

                    t.setId(rs.getLong("id"));//
                    t.setTipo(rs.getString("tipo"));
                    t.setMusculo(rs.getString("musculo"));
                    t.setAparelho(rs.getInt("aparelho"));
                    t.setRepeticoes(rs.getInt("repeticoesQTD"));
                    t.setSeries(rs.getInt("series"));

                    System.out.println("O aparelho é " + rs.getInt("aparelho"));

                    listaEncontrados.add(t);
                }

                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return listaEncontrados;
    }

    @Override
    public void atualizar(long id, Treino t) {

    }

    @Override
    public void remover(long id) {

        //Outra Maneira de criar uma conexão
        try (Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS)) {

            String sql = "DELETE FROM treino WHERE id = ? ";

            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
