package conexaoDAO;

import DAO.ClienteDAO;
import entity.Cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImplements implements ClienteDAO {

    private static final String DBURL = "jdbc:mariadb://localhost/POO_ACADEMIA";
    private static final String DBUSER = "root";
    private static final String DBPASS = "123456";

    public ClienteDAOImplements() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void adicionar(Cliente c) {

        try{
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

            String sql = "INSERT INTO cliente (id, nome, sobrenome, cpf, dataNascimento, telefone, sexo)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, c.getId());
            stmt.setString(2, c.getNome());
            stmt.setString(3, c.getSobrenome());
            stmt.setString(4, c.getCpf());
            stmt.setDate(5, java.sql.Date.valueOf(c.getDataNascimento()));
            stmt.setString(6, c.getTelefone());
            stmt.setString(7, c.getSexo());
            stmt.executeUpdate();

            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Cliente> pesquisarPorNome(String nome) {
        List<Cliente> encontrados = new ArrayList<>();

        try{
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

            String query = "SELECT * FROM cliente WHERE nome like '%"+nome+"%'";
            System.out.println(query);

            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Cliente c = new Cliente();
                c.setId(rs.getLong("id"));
                c.setNome(rs.getString("nome"));
                c.setSobrenome(rs.getString("sobrenome"));
                c.setCpf(rs.getString("cpf"));
                c.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
                c.setTelefone("telefone");
                c.setSexo("sexo");

                encontrados.add(c);
            }
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return encontrados;
    }

    @Override
    public void atualizar(long id, Cliente c) {

    }

    @Override
    public void remover(long id) {
        try (Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS)) {
            String sql = "DELETE FROM cliente WHERE id = ?";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
