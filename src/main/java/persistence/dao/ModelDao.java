package persistence.dao;

import persistence.model.ApplicationMapper;

import java.util.List;
import java.util.Map;

public interface ModelDao {

    /**
     * Body select instance
     */
    static final String SELECT_ONE = "SELECT * FROM %s WHERE ID = %d";
    /**
     * Body select list
     */
    static final String SELECT_LIST = "";
    /**
     * Body update instance
     */
    static final String UPDATE_DATA = "";
    /**
     * Body insert instance
     */
    static final String INSERT_DATA = "";
    /**
     * Body detele instances
     */
    static final String DELETE_DATA = "";

    /**
     * Update specific data and returns the old data setted.
     *
     * @param filter is a map which wraps fields of the search filter.
     * @throws IllegalArgumentException If newObject is null or another instance distinct of the specific class.

     */
    public List<ApplicationMapper> selectList(final Map filter) throws IllegalArgumentException;
    /**
     * Search the specific data of an instance.
     *
     * @param id the identify number (PK) of the specific instance.
     * @throws IllegalArgumentException If newObject is null or another instance distinct of the specific class.

     */
    public ApplicationMapper selectOne(final Long id) throws IllegalArgumentException;
    /**
     * Insert specific data and returns nothing (void).
     *
     * @param newObject the newObject to insert.
     * @throws IllegalArgumentException If newObject is null or another instance distinct of the specific class.

     */
    public boolean insertOne(ApplicationMapper newObject) throws IllegalArgumentException;
    /**
     * Update specific data and returns the old data setted.
     *
     * @param newObject the newObject to update.
     * @throws IllegalArgumentException If newObject is null or another instance distinct of the specific class.

     */
    public ApplicationMapper updateOne(ApplicationMapper newObject) throws IllegalArgumentException;
    /**
     * Delete the specific data of an instance.
     *
     * @param id the identify number (PK) of the specific instance.
     * @throws IllegalArgumentException If newObject is null or another instance distinct of the specific class.

     */
    public ApplicationMapper deleteOne(final Long id) throws IllegalArgumentException;

}
