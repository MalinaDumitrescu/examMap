import java.util.List;

/**
 * Service class that provides CRUD operations for entities extending BaseEntity.
 *
 * @param <T> the type of entity
 */
public class Service<T extends BaseEntity> {
    private final Repository<T> repository;

    /**
     * Constructs a Service with the specified repository.
     *
     * @param repository the repository to be used by this service
     */
    public Service(Repository<T> repository) {
        this.repository = repository;
    }

    /**
     * Adds an entity to the repository.
     *
     * @param entity the entity to be added
     */
    public void add(T entity) {
        repository.add(entity);
    }

    /**
     * Retrieves an entity by its ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the specified ID
     */
    public T get(int id) {
        return repository.get(id);
    }

    /**
     * Updates an entity in the repository.
     *
     * @param id     the ID of the entity to be updated
     * @param entity the new entity data
     */
    public void update(int id, T entity) {
        repository.update(id, entity);
    }

    /**
     * Deletes an entity from the repository by its ID.
     *
     * @param id the ID of the entity to be deleted
     */
    public void delete(int id) {
        repository.delete(id);
    }

    /**
     * Retrieves all entities from the repository.
     *
     * @return a list of all entities
     */
    public List<T> getAll() {
        return repository.getAll();
    }

    /**
     * Filters entities in the repository based on a predicate.
     *
     * @param predicate the predicate to filter entities
     * @return a list of entities that match the predicate
     */
    public List<T> filter(java.util.function.Predicate<T> predicate) {
        return repository.filter(predicate);
    }
}