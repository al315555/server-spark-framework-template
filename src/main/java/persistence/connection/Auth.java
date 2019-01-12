package persistence.connection;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

public class Auth {
    public static final Boolean checkCredentials(final String name, final String password) {
        Boolean correctAuth = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        String sql = null;
        String passEncrypted = null;
        try {
            passEncrypted = md5Encripter(password);//Base64.getEncoder().encodeToString(password.getBytes());
            System.out.println(passEncrypted);
            sql = String.format("SELECT CASE\n" +
                    "    WHEN COUNT(*) = 1 THEN 1\n" +
                    "    ELSE 0\n" +
                    "END AS auth\n" +
                    "FROM USUARIO\n" +
                    "WHERE USERNAME = '%s' AND PASSWORD = '%s'", name, passEncrypted);
            connection = DatabaseConnection.getInstance();
            ps = connection.prepareStatement(sql);
            //query execution
            rs = ps.executeQuery();
            //get the result
            if (rs.next()) {
                correctAuth = rs.getBoolean("auth");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return correctAuth;
    }

    private static final String md5Encripter(final String plainText) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(plainText.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);

        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }
}
