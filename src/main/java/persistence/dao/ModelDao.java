package persistence.dao;

import persistence.model.ApplicationMapper;
import persistence.model.ApplicationMapperList;

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
    static final String SELECT_LIST = "SELECT * FROM %s";
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
     * @throws Exception If newObject is null or another instance distinct of the specific class.

     */
    public ApplicationMapperList selectList(final Map filter) throws Exception;
    /**
     * Search the specific data of an instance.
     *
     * @param id the identify number (PK) of the specific instance.
     * @throws Exception If newObject is null or another instance distinct of the specific class.

     */
    public ApplicationMapper selectOne(final Long id) throws Exception;
    /**
     * Insert specific data and returns nothing (void).
     *
     * @param newObject the newObject to insert.
     * @throws Exception If newObject is null or another instance distinct of the specific class.

     */
    public boolean insertOne(ApplicationMapper newObject) throws Exception;
    /**
     * Update specific data and returns the old data setted.
     *
     * @param newObject the newObject to update.
     * @throws Exception If newObject is null or another instance distinct of the specific class.

     */
    public ApplicationMapper updateOne(ApplicationMapper newObject) throws Exception;
    /**
     * Delete the specific data of an instance.
     *
     * @param id the identify number (PK) of the specific instance.
     * @throws Exception If newObject is null or another instance distinct of the specific class.

     */
    public ApplicationMapper deleteOne(final Long id) throws Exception;

}
