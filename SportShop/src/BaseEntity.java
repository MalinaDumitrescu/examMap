/**
 * The BaseEntity class serves as a base class for entities with an identifier.
 * It provides basic functionality for getting and setting the entity's ID.
 */
public abstract class BaseEntity {
    /**
     * The unique identifier for the entity.
     */
    protected int id;

    /**
     * Constructs a new BaseEntity with the specified ID.
     *
     * @param id the unique identifier for the entity
     */
    public BaseEntity(int id) {
        this.id = id;
    }

    /**
     * Returns the unique identifier of the entity.
     *
     * @return the unique identifier of the entity
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the entity.
     *
     * @param id the unique identifier to set
     */
    public void setId(int id) {
        this.id = id;
    }
}