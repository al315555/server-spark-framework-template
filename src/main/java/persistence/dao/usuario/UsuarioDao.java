package persistence.dao.usuario;

import model.usuario.ApplicationUsuario;
import persistence.connection.DatabaseConnection;
import persistence.dao.ModelDao;
import persistence.model.ApplicationMapper;
import persistence.model.ApplicationMapperList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsuarioDao implements ModelDao {

    private static final String TABLE_USUARIO = "USUARIO";

    private static final ApplicationUsuario createDataInstance(final ResultSet rs) throws Exception{
        ApplicationUsuario usuario = new ApplicationUsuario();
        usuario.setId(rs.getInt("id"));
        usuario.setLast_login(rs.getDate("last_login").getTime());
        usuario.setBirthdate(rs.getDate("birthdate").getTime());
        usuario.setName(rs.getString("name"));
        return usuario;
    }

    /**
     * Update specific data and returns the old data setted.
     *
     * @param filter is a map which wraps fields of the search filter.
     * @throws Exception If newObject is null or another instance distinct of the specific class.
     */
    @Override
    public ApplicationMapperList selectList(final Map filter) throws Exception {
        List<ApplicationMapper> resultListUsuario = new ArrayList<>() ;
        ApplicationUsuario usuario = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        String sql = null;
        try {
            sql = String.format(SELECT_LIST, TABLE_USUARIO);
            connection = DatabaseConnection.getInstance();
            ps = connection.prepareStatement(sql);
            //query execution
            rs = ps.executeQuery();
            //get the result
            while (rs.next()) {
                usuario = createDataInstance(rs);
                resultListUsuario.add(usuario);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            try {
                if (connection != null)
                    connection.close();
            }catch(Exception ex) {
                ex.printStackTrace();
                throw ex;
            }
        }
        return new ApplicationMapperList(resultListUsuario);
    }

    /**
     * Search the specific data of an instance.
     *
     * @param id the identify number (PK) of the specific instance.
     * @throws Exception If newObject is null or another instance distinct of the specific class.
     */
    @Override
    public ApplicationMapper selectOne(final Long id) throws Exception {
        ApplicationUsuario usuario = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        String sql = null;
        try {
            sql = String.format(SELECT_ONE, TABLE_USUARIO, id);
            connection = DatabaseConnection.getInstance();
            ps = connection.prepareStatement(sql);
            //query execution
            rs = ps.executeQuery();
            //get the result
            if (rs.next()) {
                usuario = createDataInstance(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            try {
                if (connection != null)
                    connection.close();
            }catch(Exception ex) {
                ex.printStackTrace();
                throw ex;
            }
        }
        return usuario;
    }

    /**
     * Insert specific data and returns nothing (void).
     *
     * @param newObject the newObject to insert.
     * @throws Exception If newObject is null or another instance distinct of the specific class.
     */
    @Override
    public boolean insertOne(final ApplicationMapper newObject) throws Exception {
        return false;
    }

    /**
     * Update specific data and returns the old data setted.
     *
     * @param newObject the newObject to update.
     * @throws Exception If newObject is null or another instance distinct of the specific class.
     */
    @Override
    public ApplicationMapper updateOne(final ApplicationMapper newObject) throws Exception {
        return null;
    }

    /**
     * Delete the specific data of an instance.
     *
     * @param id the identify number (PK) of the specific instance.
     * @throws Exception If newObject is null or another instance distinct of the specific class.
     */
    @Override
    public ApplicationMapper deleteOne(final Long id) throws Exception {
        return null;
    }
}
