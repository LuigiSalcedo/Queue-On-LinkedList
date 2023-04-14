package queueproject.models;

/**
 *
 * @author Luigi Salcedo
 */
public enum Priority 
{
    MAX(1),
    MED(2),
    MIN(3);
    
    private final int priorityValue;
    
    Priority(int priorityValue)
    {
        this.priorityValue = priorityValue;
    }
    
    public int getPriorityValue()
    {
        return priorityValue;
    }
}
