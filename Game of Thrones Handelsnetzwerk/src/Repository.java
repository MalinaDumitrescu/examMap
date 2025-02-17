import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Repository class that provides CRUD operations for entities extending BaseEntity.
 *
 * @param <T> the type of entity
 */
public class Repository<T extends BaseEntity> {
    private final Map<Integer, T> entities = new HashMap<>();
    private int nextId = 1;

    /**
     * Adds an entity to the repository.
     *
     * @param entity the entity to be added
     */
    public void add(T entity) {
        entity.setId(nextId++);
        entities.put(entity.getId(), entity);
    }

    /**
     * Retrieves an entity by its ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the specified ID
     */
    public T get(int id) {
        return entities.get(id);
    }

    /**
     * Updates an entity in the repository.
     *
     * @param id     the ID of the entity to be updated
     * @param entity the new entity data
     */
    public void update(int id, T entity) {
        entities.put(id, entity);
    }

    /**
     * Deletes an entity from the repository by its ID.
     *
     * @param id the ID of the entity to be deleted
     */
    public void delete(int id) {
        entities.remove(id);
    }

    /**
     * Retrieves all entities from the repository.
     *
     * @return a list of all entities
     */
    public List<T> getAll() {
        return entities.values().stream().toList();
    }

    /**
     * Filters entities in the repository based on a predicate.
     *
     * @param predicate the predicate to filter entities
     * @return a list of entities that match the predicate
     */
    public List<T> filter(java.util.function.Predicate<T> predicate) {
        return entities.values().stream().filter(predicate).collect(Collectors.toList());
    }
}