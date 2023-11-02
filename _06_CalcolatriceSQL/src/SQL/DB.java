package SQL;
import javax.swing.*;
import java.sql.*;
import java.util.Objects;

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

        this.CreateTables();
    }

    private void CreateTables() {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://" + host + ":3306/" + dbname + "?useSSL=false&serverTimezone=UTC", user, pw);
             PreparedStatement userdata = conn.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS `userdata` (`Username` varchar(120) PRIMARY KEY NOT NULL,`Password` varchar(120) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;");
             PreparedStatement history = conn.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS `history` (`Id` int(11) PRIMARY KEY AUTO_INCREMENT,`Operation` varchar(120) NOT NULL,`Username` varchar(120) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;")) {
            userdata.execute();
            history.execute();
        } catch (SQLException e) {
            System.out.print("SQL State: %s\n%s" + e.getSQLState() + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InsertIntoUserdata(String username, String password) throws SQLException {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://" + host + ":3306/" + dbname + "?useSSL=false&serverTimezone=UTC", user, pw);
             PreparedStatement insert = conn.prepareStatement("INSERT INTO userdata (Username, Password) VALUES (?, ?);")) {

            insert.setString(1, username);
            insert.setString(2, password);

            insert.execute();
            System.out.print("\nquery executed");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "username già in uso");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InsertIntoHistory(String operation, String username) throws SQLException {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://" + host + ":3306/" + dbname + "?useSSL=false&serverTimezone=UTC", user, pw);
             PreparedStatement insert = conn.prepareStatement("INSERT INTO history (Operation, Username) VALUES (?, ?);")) {

            insert.setString(1, operation);
            insert.setString(2, username);

            insert.execute();
            System.out.print("\nquery executed");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String GetUsername(String username, String password) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://" + host + ":3306/" + dbname + "?useSSL=false&serverTimezone=UTC", user, pw);
             PreparedStatement query = conn.prepareStatement("SELECT Username FROM userdata WHERE Username LIKE ? AND Password LIKE ?;")) {
            query.setString(1, username);
            query.setString(2, password);

            ResultSet rs = query.executeQuery();
            String result = null;
            while (rs.next()) {
                result = rs.getString("Username");
            }

            System.out.print("\nquery executed");
            return result;

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, "password errata");
        return null;
    }

    public void PrintHistory(String username) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://" + host + ":3306/" + dbname + "?useSSL=false&serverTimezone=UTC", user, pw);
             PreparedStatement query = conn.prepareStatement("SELECT * FROM history WHERE Username LIKE ?;")) {
            query.setString(1, username);
            ResultSet rs = query.executeQuery();
            String s = "";
            while (rs.next()) {
                s += rs.getString("Id") + "\t";
                s += rs.getString("Operation") + "\n";
            }
            JOptionPane.showMessageDialog(null, new JTextArea(s));
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
