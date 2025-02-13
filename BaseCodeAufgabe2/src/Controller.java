import java.util.List;

public class Controller<T extends BaseEntity> {
    private final Service<T> service;

    public Controller(Service<T> service) {
        this.service = service;
    }

    public void add(T entity) {
        service.add(entity);
    }

    public T get(int id) {
        return service.get(id);
    }

    public void update(int id, T entity) {
        service.update(id, entity);
    }

    public void delete(int id) {
        service.delete(id);
    }

    public List<T> getAll() {
        return service.getAll();
    }

    public List<T> filter(java.util.function.Predicate<T> predicate) {
        return service.filter(predicate);
    }
}
