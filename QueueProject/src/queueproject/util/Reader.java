package queueproject.util;

import java.util.Scanner;

/**
 *
 * @author Luigi Salcedo
 */
public class Reader 
{
    private Scanner read;
    private String message;
    
    public Reader()
    {
        read = new Scanner(System.in);
        message = "";
    }
    
    public Reader(String message)
    {
        read = new Scanner(System.in);
        this.message = message; 
    }
    
    // Métodos
    public String next()
    {
        return read.next();
    }
    
    public String nextLine()
    {
        return read.nextLine();
    }
    
    public int nextInt()
    {
        return Integer.parseInt(next());
    }
    
    public long nextLong()
    {
        return Long.parseLong(next());
    }
    
    public float nextFloat()
    {
        return Float.parseFloat(next());
    }
    
    public double nextDouble()
    {
        return Double.parseDouble(next());
    }
    
    public int nextInt(int minValue)
    {
        int n;
        do
        {
            n = nextInt();
            if(n < minValue)
            {
                System.out.println("\n" + message);
            }
        }while(n < minValue);
        return n;
    }
    
    public int nextInt(int minValue, int maxValue)
    {
        int n;
        do
        {
            n = nextInt();
            if(n < minValue || n > maxValue)
            {
                System.out.println("\n" + message);
            }
        }while(n < minValue || n > maxValue);
        return n;
    }
    
    public long nextLong(long minValue)
    {
        long n;
        do
        {
            n = nextLong();
            if(n < minValue)
            {
                System.out.println("\n" + message);
            }
        }while(n < minValue);
        return n;        
    }
    
    public long nextLong(long minValue, long maxValue)
    {
        long n;
        do
        {
            n = nextLong();
            if(n < minValue || n > maxValue)
            {
                System.out.println("\n" + message);
            }
        }while(n < minValue || n > maxValue);
        return n;         
    }
    
    public float nextFloat(float minValue)
    {
        float n;
        do
        {
            n = nextFloat();
            if(n < minValue)
            {
                System.out.println("\n" + message);
            }
        }while(n < minValue);
        return n;
    }
    
    public float nextFloat(float minValue, float maxValue)
    {
        float n;
        do
        {
            n = nextFloat();
            if(n < minValue || n > maxValue)
            {
                System.out.println("\n" + message);
            }
        }while(n < minValue || n > maxValue);
        return n;        
    }
    
    public double nextDouble(double minValue)
    {
        double n;
        do
        {
            n = nextDouble();
            if(n < minValue)
            {
                System.out.println("\n" + message);
            }
        }while(n < minValue);
        return n;
    }
    
    public double nextDouble(double minValue, double maxValue)
    {
        double n;
        do
        {
            n = nextDouble();
            if(n < minValue || n > maxValue)
            {
                System.out.println("\n" + message);
            }
        }while(n < minValue || n > maxValue);
        return n;
    }
    
    public String nextStringNotVoid()
    {
        String str;
        do
        {
            str = nextLine();
        }while(str.equals(""));
        return str;
    }
}
