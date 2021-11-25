package conexaoDAO;

import DAO.EquipamentoDAO;
import entity.Equipamento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoDAOImplements implements EquipamentoDAO {

    private static final String DBURL = "jdbc:mariadb://localhost:3306/POO_ACADEMIA";
    private static final String DBUSER = "root";
    private static final String DBPASS = "123456";

    public EquipamentoDAOImplements() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void adicionar(Equipamento e) {

        try {
            Connection con = DriverManager.getConnection(DBURL,DBUSER, DBPASS);
            String sql = "INSERT INTO equipamento (id, nome, fabricante, ano, preco)" +
                    "VALUES (?, ?, ?, ?, ?)";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, e.getId());
            stmt.setString(2,e.getNome());
            stmt.setString(3, e.getFabricante());
            stmt.setInt(4, e.getAno());
            stmt.setDouble(5, e.getPreco());

            stmt.executeUpdate();
            con.close();
        } catch (Exception z) {
            z.printStackTrace();
        }

    }

    @Override
    public List<Equipamento> pesquisarPorNome(String nome) {

        List<Equipamento> encontrados = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(DBURL,DBUSER, DBPASS);
            String sql = "SELECT * FROM equipamento WHERE nome like '%"+ nome + "%'";
            System.out.println(sql);

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Equipamento e = new Equipamento();
                e.setId( rs.getLong("id"));
                e.setNome(rs.getString("nome"));
                e.setFabricante(rs.getString("fabricante"));
                e.setAno(rs.getInt("ano"));
                e.setPreco(rs.getDouble("preco"));

                encontrados.add(e);

            }

            con.close();
        } catch (Exception z) {
            z.printStackTrace();
        }
        return encontrados;
    }

    @Override
    public void atualizar(long id, Equipamento e) {

    }

    @Override
    public void remover(long id) {
        try {
            Connection con = DriverManager.getConnection(DBURL,DBUSER, DBPASS);
            String sql = "DELETE FROM equipamento WHERE id = ?";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
