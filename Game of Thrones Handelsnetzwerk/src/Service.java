import java.util.List;

public class Service<T extends BaseEntity> {
    private final Repository<T> repository;

    public Service(Repository<T> repository) {
        this.repository = repository;
    }

    public void add(T entity) {
        repository.add(entity);
    }

    public T get(int id) {
        return repository.get(id);
    }

    public void update(int id, T entity) {
        repository.update(id, entity);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public List<T> getAll() {
        return repository.getAll();
    }

    public List<T> filter(java.util.function.Predicate<T> predicate) {
        return repository.filter(predicate);
    }
}
