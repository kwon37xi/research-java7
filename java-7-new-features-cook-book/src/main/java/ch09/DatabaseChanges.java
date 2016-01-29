package ch09;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DatabaseChanges {
    public static void main(String[] args) throws Exception {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila?useUnicode=true&characterEncoding=utf8", "root", "")) {
            System.out.println("Schema: " + con.getSchema());

            final DatabaseMetaData metaData = con.getMetaData();
            System.out.println("Auto Generated Keys: " + metaData.generatedKeyAlwaysReturned());

            final ResultSet rs = metaData.getPseudoColumns("", "", "payment", "");

            while (rs.next()) {
                System.out.println(rs.getShort("TABLE_SCHEM") + " - " + rs.getString("COLUMN_NAME"));
            }
        }

    }
}
