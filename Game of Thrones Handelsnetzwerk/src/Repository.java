import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Repository<T extends BaseEntity> {
    private final Map<Integer, T> entities = new HashMap<>();
    private int nextId = 1;

    public void add(T entity) {
        entity.setId(nextId++);
        entities.put(entity.getId(), entity);
    }

    public T get(int id) {
        return entities.get(id);
    }

    public void update(int id, T entity) {
        entities.put(id, entity);
    }

    public void delete(int id) {
        entities.remove(id);
    }

    public List<T> getAll() {
        return entities.values().stream().toList();
    }

    public List<T> filter(java.util.function.Predicate<T> predicate) {
        return entities.values().stream().filter(predicate).collect(Collectors.toList());
    }
}
