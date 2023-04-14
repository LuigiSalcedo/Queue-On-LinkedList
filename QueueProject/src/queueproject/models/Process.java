package queueproject.models;

import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author Luigi Salcedo
 */
public class Process 
{
    private String name;
    private Priority priority;
    private int time;
    
    public Process()
    {
        
    }
    
    public Process(String name, Priority priority, int time)
    {
        this.name = name;
        this.priority = priority;
        this.time = time;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setPriority(Priority priority)
    {
        this.priority = priority;
    }
    
    public void setTime(int time)
    {
        this.time = time;
    }
    
    public String getName()
    {
        return name;
    }
    
    public Priority getPriority()
    {
        return priority;
    }
    
    public int getTime()
    {
        return time;
    }
    
    @Override
    public String toString()
    {
        return name + " - Prioridad -> " + priority.toString() + " -  Orden llegada: " + time;
    }
    
    @Override
    public boolean equals(Object o)
    {
        Process other = (Process) o;
        return name.equals(other.name) && priority == other.priority;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.priority);
        return hash;
    }
    
    public static Process parseProcess(String str)
    {
        Process result = new Process();
        try
        {
            String [] data = str.split(";");
            result.name = data[0];
            result.time = Integer.parseInt(data[2]);
            data[1] = data[1].toUpperCase();
            switch (data[1]) {
                case "MAX":
                    result.priority = Priority.MAX;
                    break;
                case "MED":
                    result.priority = Priority.MED;
                    break;
                case "MIN":
                    result.priority = Priority.MIN;
                    break;
                default:
                    throw new IOException("Formato de proceso no valida");
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
 
        return result;
    }
}
