package persistence.dao.usuario;

import model.usuario.ApplicationUsuario;
import persistence.connection.DatabaseConnection;
import persistence.dao.ModelDao;
import persistence.model.ApplicationMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsuarioDao implements ModelDao {

    private static final UsuarioDao instance = new UsuarioDao();



    private static final String TABLE_USUARIO = "USUARIO";

    /**
     * Update specific data and returns the old data setted.
     *
     * @param filter is a map which wraps fields of the search filter.
     * @throws IllegalArgumentException If newObject is null or another instance distinct of the specific class.
     */
    @Override
    public List<ApplicationMapper> selectList(final Map filter) throws IllegalArgumentException {
        ArrayList<ApplicationMapper> resultListUsuario = new ArrayList<ApplicationMapper>();
        //TODO
        return resultListUsuario;
    }

    /**
     * Search the specific data of an instance.
     *
     * @param id the identify number (PK) of the specific instance.
     * @throws IllegalArgumentException If newObject is null or another instance distinct of the specific class.
     */
    @Override
    public ApplicationMapper selectOne(final Long id) throws IllegalArgumentException {
        ApplicationUsuario usuario = new ApplicationUsuario();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = String.format(SELECT_ONE, TABLE_USUARIO, id);
            ps = DatabaseConnection.getInstance()
                    .prepareStatement(sql);
            //query execution
            rs = ps.executeQuery();
            //get the result
            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
                usuario.setLast_login(rs.getDate("last_login").getTime());
                usuario.setBirthdate(rs.getDate("birthdate").getTime());
                usuario.setName(rs.getString("name"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return usuario;
    }

    /**
     * Insert specific data and returns nothing (void).
     *
     * @param newObject the newObject to insert.
     * @throws IllegalArgumentException If newObject is null or another instance distinct of the specific class.
     */
    @Override
    public boolean insertOne(final ApplicationMapper newObject) throws IllegalArgumentException {
        return false;
    }

    /**
     * Update specific data and returns the old data setted.
     *
     * @param newObject the newObject to update.
     * @throws IllegalArgumentException If newObject is null or another instance distinct of the specific class.
     */
    @Override
    public ApplicationMapper updateOne(final ApplicationMapper newObject) throws IllegalArgumentException {
        return null;
    }

    /**
     * Delete the specific data of an instance.
     *
     * @param id the identify number (PK) of the specific instance.
     * @throws IllegalArgumentException If newObject is null or another instance distinct of the specific class.
     */
    @Override
    public ApplicationMapper deleteOne(final Long id) throws IllegalArgumentException {
        return null;
    }
}
