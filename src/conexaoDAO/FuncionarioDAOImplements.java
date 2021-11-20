package conexaoDAO;

import DAO.FuncionarioDAO;
import entity.Funcionario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAOImplements implements FuncionarioDAO {

    private static final String DBURL = "jdbc:mariadb://localhost:3306/POO_ACADEMIA";
    private static final String DBUSER = "root";
    private static final String DBPASS = "123456";

    public FuncionarioDAOImplements() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void adicionar(Funcionario f) {

        try {
            Connection con = DriverManager.getConnection(DBURL,DBUSER, DBPASS);
            String sql = "INSERT INTO funcionario (id, nome, sobrenome, cpf, dtNascimento, dtInicio, sexo, salario, ddd, telefone, email)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, f.getId());
            stmt.setString(2,f.getNome());
            stmt.setString(3, f.getSobrenome());
            stmt.setString(4, f.getCpf());
            stmt.setDate(5, java.sql.Date.valueOf(f.getDataNascimento()));
            stmt.setDate(6,java.sql.Date.valueOf(f.getDataInicio()));
            stmt.setString(7,f.getSexo());
            stmt.setDouble(8, f.getSalario());
            stmt.setInt(9, f.getDdd());
            stmt.setLong(10, f.getTelefone());
            stmt.setString(11, f.getEmail());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Funcionario> pesquisarPorNome(String nome) {

        List<Funcionario> encontrados = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(DBURL,DBUSER, DBPASS);
            String sql = "SELECT * FROM funcionario WHERE nome like '%"+ nome + "%'";
            System.out.println(sql);

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId( rs.getLong("id"));
                f.setNome(rs.getString("nome"));
                f.setSobrenome(rs.getString("sobrenome"));
                f.setCpf(rs.getString("cpf"));
                f.setDataNascimento(rs.getDate("dtNascimento").toLocalDate());
                f.setDataInicio(rs.getDate("dtInicio").toLocalDate());
                f.setSexo(rs.getString("sexo"));
                f.setSalario(rs.getDouble("salario"));
                f.setDdd(rs.getInt("ddd"));
                f.setTelefone(rs.getLong("telefone"));
                f.setEmail(rs.getString("email"));

                encontrados.add(f);

            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encontrados;
    }

    @Override
    public void atualizar(long id, Funcionario f) {

    }

    @Override
    public void remover(long id) {
        try {
            Connection con = DriverManager.getConnection(DBURL,DBUSER, DBPASS);
            String sql = "DELETE FROM funcionario WHERE id = ?";
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
