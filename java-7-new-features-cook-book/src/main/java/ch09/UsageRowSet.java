package ch09;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.io.IOException;
import java.sql.SQLException;

/**
 * {@link javax.sql.RowSet}은 {@link java.sql.ResultSet}과 비슷하지만 이벤트를 핸들링할 수 있다.
 * <a href="https://docs.oracle.com/javase/tutorial/jdbc/basics/rowset.html">Oracle JDBC Tutorial RowSet</a>
 * <a href="http://www.javatpoint.com/jdbc-rowset">jdbc-rowset</a>
 *
 *
 */
public class UsageRowSet {
    public static void main(String[] args) throws SQLException, IOException {
        // https://dev.mysql.com/doc/sakila/en/
        String databaseUrl = "jdbc:mysql://localhost:3306/sakila?useUnicode=true&characterEncoding=utf8";
        String username = "root";
        String password = "";

        // "javax.sql.rowset.RowSetFactory" System 프라퍼티가 지정돼 있다면 해당 값으로 RowSetFactory 생성.
        // 아니면 직접지정.
        RowSetFactory rowSetFactory = RowSetProvider.newFactory("com.sun.rowset.RowSetFactoryImpl", null);

        try (JdbcRowSet rowSet = rowSetFactory.createJdbcRowSet()) {
            rowSet.setUrl(databaseUrl);
            rowSet.setUsername(username);
            rowSet.setPassword(password);
            rowSet.setCommand("SELECT * FROM actor");
            rowSet.execute();

            while(rowSet.next()) {
                System.out.println(rowSet.getInt("actor_id") + " - " + rowSet.getString("first_name"));
            }
        }

    }
}
