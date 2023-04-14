package queueproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.BiFunction;
import queueproject.models.Priority;
import queueproject.structures.LinkedQueue;
import queueproject.structures.PriorityLinkedQueue;
import queueproject.util.Reader;
import queueproject.models.Process;
import queueproject.structures.LinkedList;

/**
 *
 * @author Luigi Salcedo
 */
public class QueueProject 
{
    private static Reader read = new Reader();
    private static LinkedQueue<String> colaCajero = new LinkedQueue(); // Cola para elementos de la parte del cajero automatico
    
    /**
     * Espacio para cargar la información de la cola del cajero.
     * 
     * Utilizar este espacio para carga la información de la cola del cajero.
     * 
     * Por defecto se cargarán los nombres desde un archivo.
     */
    public static void loadData()
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("data\\queue_data.txt"));
            String line;
            while((line = br.readLine()) != null)
            {
                colaCajero.add(line);
            }
        }
        catch(IOException e)
        {
            System.out.println("No se ha encontrado el archivo para cargar datos a la cola.");
        }
    }
    
    public static void main(String[] args) 
    {
        loadData(); // Cargar la información de la cola del cajero.
        int op;
        do
        {
            System.out.println("Menú selección de programa: ");
            System.out.println();
            System.out.println("1. Cola cajero automático. (Colas sin prioridad)");
            System.out.println("2. Palíndromo.  (Colas sin prioridad)");
            System.out.println("3. Tarea con prioridad. (Colas con prioridad)");
            System.out.println();
            System.out.println("4. Salir.");
            System.out.println();
            System.out.print("Seleccionar: "); op = read.nextInt();
            switch(op)
            {
                case 1: 
                    cajeroAutomatico();
                    break;
                case 2: 
                    palindromo();
                    break;
                case 3: 
                    tareasConPrioridad();
                    break;
                case 4: break;
            }
        }while(op != 4);
    }  
    
    private static void cajeroAutomatico()
    {
        int op;
       
        do
        {
            showQueueInfo();
            System.out.println("1. Atender una persona. (Desencolar)");
            System.out.println("2. Agregar una persona. (Encolar)");
            System.out.println();
            System.out.println("3. Regresar.");
            System.out.print("\nSeleccionar: "); op = read.nextInt();
            switch(op)
            {
                case 1: 
                    System.out.println("Se ha atendido a: " + colaCajero.poll());
                    break;
                case 2: 
                    String str;
                    System.out.print("Ingrese el nombre de la persona a encolar: ");
                    str = read.nextStringNotVoid();
                    colaCajero.offer(str);
                    break;
                case 3: break;
                default:
                    System.out.println("\nOpción seleccionada no valida . . . ");
            }
        }while(op != 3);
    }
    
    private static void showQueueInfo()
    {
        System.out.println("\nInformación cajero automático: ");
        System.out.println();
        System.out.println("Número de personas en cola: " + colaCajero.size());
        System.out.println("Siguiente persona a ser atendida: " + colaCajero.peek());
        System.out.println("Ultima persona en la cola: " + colaCajero.getLast());
        System.out.println();        
    }
    
    private static void palindromo()
    {
        String str;

        System.out.print("\nIngrese la palabra para confirmar si es palindroma: "); str = read.nextStringNotVoid().toLowerCase();
        
        int sum = str.length() % 2;
        
        LinkedQueue<Character> primeraMitad = new LinkedQueue();
        LinkedQueue<Character> segundaMitad = new LinkedQueue();
        for(char c : str.substring(0, str.length()/2).toCharArray())
        {
            primeraMitad.offer(c);
        }
        
        char [] c = str.substring(str.length()/2 + sum).toCharArray();
        
        for(int i = c.length-1; i >= 0; i--)
        {
            segundaMitad.offer(c[i]);
        }
        
        while(!primeraMitad.isEmpty() && !segundaMitad.isEmpty())
        {
            if(!primeraMitad.poll().equals(segundaMitad.poll()))
            {
                System.out.println("\nNo es un palíndromo.\n");
                return;
            }
        }
        System.out.println("\nEs un palíndromo.\n");
        
    }
    
    private static void tareasConPrioridad()
    {
        int op;
        do
        {
            System.out.println("\nSeleccione la opción de prioridad: ");
            System.out.println();
            System.out.println("1. Prioridad de ejecución sin orden de llegada.");
            System.out.println("2. Prioridad de ejecución con orden de llegada.");
            System.out.println();
            System.out.println("3. Salir");
            System.out.println();
            System.out.print("Seleccionar: "); op = read.nextInt();
            switch(op)
            {
                case 1: 
                    mostrarOrden((x, y) -> x.getPriority().getPriorityValue() - y.getPriority().getPriorityValue());
                    break;
                case 2:
                    mostrarOrden((x, y) -> 
                    {
                        if(x.getPriority() != y.getPriority())
                        {
                            return x.getPriority().getPriorityValue() - y.getPriority().getPriorityValue();
                        }
                        return x.getTime() - y.getTime();
                    });
                    break;
                case 3: break;
                default:
                    System.out.println("\nOpción seleccionada no valida . . . ");
            }
        }while(op != 3);
    }
    
   private static void mostrarOrden(BiFunction<Process, Process, Integer> comparator)
   {
       PriorityLinkedQueue<Process> colaProcesos = new PriorityLinkedQueue(comparator);
       try
       {
           BufferedReader br = new BufferedReader(new FileReader("data\\process_data.txt"));
           String line;
           while((line = br.readLine()) != null)
           {
               if(!colaProcesos.add(Process.parseProcess(line)))
               {
                   System.out.println("No se pudo agregar: " + line);
               }
           }
           System.out.println("\nOrden de ejecución de los procesos: ");
       }
       catch(IOException e)
       {
           System.out.println("\nArchivo 'process_data.txt' no fue encontrado. Asegurarse que se encuentra en la carpeta 'data'.");
       }
       
       int op;
       do
       {
           System.out.println("\nSeleccione una opción: ");
           System.out.println();
           System.out.println("1. Visualizar orden de los procesos.");
           System.out.println("2. Agregar otro proceso. (Encolar)");
           System.out.println("3. Finalizar un proceso. (Desencolar)");
           System.out.println();
           System.out.println("4. Salir.");
           System.out.println();
           System.out.print("Seleccionar: "); op = read.nextInt();
           switch(op)
           {
               case 1: 
                   visualizarLista(colaProcesos);
                   break;
               case 2:
                   String name;
                   Priority priority;
                   System.out.print("\nIngrese el nombre del proceso: "); name = read.nextStringNotVoid();
                   do
                   {
                       System.out.print("\nIngrese la prioridad del proceso: [1. MAX, 2. MED, 3. MIN]: "); op = read.nextInt();
                       if(op != 1 && op != 2 && op != 3)
                       {
                           System.out.println("\nOpción seleccionada no valida . . . ");
                       }
                   }while(op != 1 && op != 2 && op != 3);
                   Process newProcess = new Process();
                   newProcess.setName(name);
                   switch(op)
                   {
                       case 1: 
                           priority = Priority.MAX;
                           break;
                       case 2: 
                           priority = Priority.MED;
                           break;
                       default: 
                           priority = Priority.MIN;
                           break;
                   }
                   newProcess.setPriority(priority);
                   colaProcesos.add(newProcess);
                   System.out.println("\nProceso: " + newProcess + " agregado correctamente.");
                   break;         
               case 3: 
                   Process process = colaProcesos.poll();
                   if(process != null)
                   {
                       System.out.println("Proceso: " + process + " ha sido finalizado con exito.");
                   }
                   else
                   {
                       System.out.println("\nNo hay procesos en ejecución para finalizar.");
                   }
                   break;
               case 4: break;
           }
       }while(op != 4);
       System.out.println("\nAL VOLVER A SELECCIONAR UNA PRIORIDAD SE CARGARÁN LOS PROCESOS DESDE EL ARCHIVO.");
       System.out.println("LOS CAMBIOS EN EJECUCIÓN NO SON GUARDADOS.");
   }
   
   private static void visualizarLista(LinkedList<?> lista)
   {
        System.out.println("");
        Iterator<?> iterator = lista.iterator();
        int i = 1;
        while(iterator.hasNext())
        {
            System.out.println(i + ". " + iterator.next());
            i++;
        }      
        System.out.println("");
   }
}
