
package datamodel.sd.dao;

public interface Dao<T>
{
    T findById(int id);

    void delete(T objectToDelete);

    void update(T objectToUpdate);

    void insert(T objectToCreate);

    void closeConnection();
}
