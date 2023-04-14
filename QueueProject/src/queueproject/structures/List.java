package queueproject.structures;

/**
 *
 * @author Luigi Salcedo
 */
public interface List<T> 
{
    public int size();
    public boolean isEmpty();
    public boolean add(T value);
    public boolean remove(int index);
    public T get(int index);
    public int indexOf(T value);
}
