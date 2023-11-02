package SQL;
import java.sql.*;
public class DB {
    private String dbname;
    private String host;
    private String user;
    private String pw;

    public DB(String databaseName, String hostName, String username, String password) {
        dbname = databaseName;
        host = hostName;
        user = username;
        pw = password;
    }

    private Connection Connect(String db, String host, String user, String pw) {
        String connectionUrl = "jdbc:mysql://" + host + ":3306/" + db + "?useSSL=false&serverTimezone=UTC";
        try (Connection connection = DriverManager.getConnection(connectionUrl, user, pw);) {
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void InsertInto(String nome, String cognome, int eta) throws SQLException {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://" + host + ":3306/" + dbname + "?useSSL=false&serverTimezone=UTC", user, pw);
             PreparedStatement ins = conn.prepareStatement("INSERT INTO userdata (Nome, Cognome, Eta) VALUES (?, ?, ?);")) {

            ins.setString(1, nome);
            ins.setString(2, cognome);
            ins.setInt(3, eta);

            ins.execute();
            System.out.print("\nquery executed");

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
