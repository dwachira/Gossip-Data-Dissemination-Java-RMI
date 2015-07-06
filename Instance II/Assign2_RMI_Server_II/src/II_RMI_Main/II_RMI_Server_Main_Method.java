 /*************************************************************************
 *                                                                        *
 *                   Mureithi David Wachira	                          *
 *                                                                        *
 *                   P15/2204/2011                                        *
 *                                                                        *
 *                   Mobile: +254 (0) 722 413 699                         *
 *                   Email:  davidwachira90@students.uonbi.ac.ke          *
 *                                                                        *
 *                   Date:   Tuesday 28th October 2014                    *
 *                                                                        *
 *                   CSC 315 DISTRIBUTED SYSTEMS                          *
 *                                                                        *
 *                   A program that demonstrates Multicast                *
 *                   Communication in Distributes Systems using Java      *
 *                                                                        *
 *************************************************************************/

package II_RMI_Main;

      /********************************************************
      *                                                       *
      *   Imports from JAVA RMI Libraries                     *
      *                                                       *
      ********************************************************/
import II_RMI_Main.II_Looking_inside_a_File;
import II_RMI_Message_Package.II_Message;
import II_RMI_Message_Package.II_Message_Implementation_at_Server;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
 
public class II_RMI_Server_Main_Method {
    //public String updated_Message_to_Pass = "";
    
    int Instance_number = 2; 
    File Log_file_for_CSC315 =new File("Log File - Instance " + Instance_number + ".txt");
    public int Counter_for_Spreading_Infection = 0;
    
    public int array_size_number_of_connected_nodes = 0;
    int Instance_Selected_Randomly = 0;
    
    Integer[] a = {1, 4, 5};
    int[] Array_Instance_Selected_Randomly = new int[a.length];
            
    
    private void Start_RMI_Server(){
        try { 
            Log_Information_to_Text_file ("Starting Server - Instance 2");
      /********************************************************
      *                                                       *
      *   The Port assigned is Port Number 2202               *
      *                                                       *
      ********************************************************/
            Registry registry = LocateRegistry.createRegistry(2202);
            Log_Information_to_Text_file ("Port 2202 dedicated for the Creation of a New Service"); 

      /********************************************************
      *                                                       *
      *   Create a new service named New_Message_Service      *
      *                                                       *
      ********************************************************/
            registry.rebind("New_Message_Service_Instance_2", new II_Message_Implementation_at_Server());
            Log_Information_to_Text_file ("New_Message_Service_Instance_2 created");
            
        } catch (Exception e) {
            e.printStackTrace();
            Log_Information_to_Text_file ("Error within Start_RMI_Server() --> "+e);
        } 
        Log_Information_to_Text_file ("Instance II is Ready; Listening on Port 2202");
        

        
        File_Monitor_for_Changes monitor = new File_Monitor_for_Changes();
        try {
            monitor.File_Monitor_for_Changes();
            } 
        catch (Exception ex) 
            {
                Logger.getLogger(II_RMI_Server_Main_Method.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
   
   public void Test_Message_Implementation()
   {
        Counter_for_Spreading_Infection++;
        try {
            
      /********************************************************
      *                                                       *
      *   Fire to localhost Port Number XXXX                  *
      *                                                       *
      ********************************************************/
            
            int Port_Number_to_be_Used = 0;
            String IP_Address_to_be_Used = "";
            String Procedure_Service_Name = "";

      /********************************************************
      *                                                       *
      *   Select a random number                              *
      *                                                       *
      ********************************************************/
        /*  Random random_instance = new Random();
            int Instance_Selected_Randomly = random_instance.nextInt(3) + 1;
            Log_Information_to_Text_file ("Random number selected is "+ Instance_Selected_Randomly);
        */   
            if(Counter_for_Spreading_Infection == 1)
            {   
                int counter_random = 0;
                
            
        	List<Integer> nums = new ArrayList<Integer>(Arrays.asList(a));
        	for(Integer i : a) 
                {
        	    Integer score = nums.remove((int)(Math.random() * nums.size()));
        	    Array_Instance_Selected_Randomly[counter_random] = score;
                    counter_random++;
                    //System.out.println(score);
                }
                
                Instance_Selected_Randomly =  Array_Instance_Selected_Randomly[0];
                
             }

            else if(Counter_for_Spreading_Infection == 2)
            {
                Instance_Selected_Randomly =  Array_Instance_Selected_Randomly[1];
                
            }
            
            else if(Counter_for_Spreading_Infection == 3)
            {
                Instance_Selected_Randomly =  Array_Instance_Selected_Randomly[2];
                
            }
            String Particular_Instance = "INSTANCE "+ Instance_Selected_Randomly;
            System.out.println(Instance_Selected_Randomly);
            
            String[] Array_with_Information_on_Instance = new String[4];
            Array_with_Information_on_Instance = Get_Information_on_a_Particular_Instance(Particular_Instance);
            
            IP_Address_to_be_Used = Array_with_Information_on_Instance[1];
            Port_Number_to_be_Used = Integer.parseInt(Array_with_Information_on_Instance[2]);
            Procedure_Service_Name = Array_with_Information_on_Instance[3];
                    
            Registry registry = LocateRegistry.getRegistry(IP_Address_to_be_Used, Port_Number_to_be_Used);
            Log_Information_to_Text_file ("Trying to find access Port: "+ Port_Number_to_be_Used + " on IP address: " + IP_Address_to_be_Used);
      /********************************************************
      *                                                       *
      *   Search for the "New_Message_Service"  service       *
      *                                                       *
      ********************************************************/
           
            II_Message impl = (II_Message) registry.lookup(Procedure_Service_Name);
            Log_Information_to_Text_file ("Looking up Service: "+ Procedure_Service_Name + "in the Registry");
      /********************************************************
      *                                                       *
      *   Print out the message sent to server on the         *
      *   Client screen                                       *
      ********************************************************/        
            String Message_to_be_Communicated = II_Message_Implementation_at_Server.Msg_Update_Received +" - Update by Instance II";
            //System.out.println("Message Sent - ["+ Message_to_be_Communicated +"]");
            Log_Information_to_Text_file("Message Sent - ["+ Message_to_be_Communicated +"]");
      /********************************************************
      *                                                       *
      *   Call the Server's method                            *
      *                                                       *
      ********************************************************/        
     
            //System.out.println(""+impl.Pass_Message(Message_to_be_Communicated));
            Log_Information_to_Text_file(""+impl.Pass_Message(Message_to_be_Communicated));
            

        } catch (Exception e) {
            e.printStackTrace();
            Log_Information_to_Text_file ("Error within Test_Message_Implementation() --> "+e);
        }       
    }

	

          

   
   
    public static void main(String[] args) {
        II_RMI_Server_Main_Method main = new II_RMI_Server_Main_Method();
      /********************************************************
      *                                                       *
      *   Start the Server                                    *
      *                                                       *
      ********************************************************/
        main.Start_RMI_Server();
        

            
    }
    
    
    public void Log_Information_to_Text_file (String Message_to_Log)
    {
        try
        {
            SimpleDateFormat variable_datetimeFt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Message_to_Log =  " ["+ variable_datetimeFt.format(Calendar.getInstance().getTime()) + "] " + Message_to_Log + "\n";

                try
                {
                        /********************************************************
                        *                                                       *
                        *   If file doesnt exists, then create it               *
                        *                                                       *
                        ********************************************************/
                        
                        if(!Log_file_for_CSC315.exists())
                        {
                                Log_file_for_CSC315.createNewFile();
                        }
                        /********************************************************
                        *                                                       *
                        *   true = append file                                  *
                        *                                                       *
                        ********************************************************/

                        FileWriter fileWritter = new FileWriter(Log_file_for_CSC315.getName(),true);
                        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
                        bufferWritter.write(Message_to_Log);
                        bufferWritter.close();

                        java.util.Date date= new java.util.Date();
                        System.out.println("Done --> " + Message_to_Log + "[" + new Timestamp(date.getTime()) + "] ");

                        //System.out.println("Done --> " + Message_to_Log);

                }
                
                catch(IOException exception_in_appending_text_to_file)
                {
                        System.out.println("Error in Log_Information_to_Text_file (String Message_to_Log) -->"+ exception_in_appending_text_to_file);
        
                }
        } 

        catch (Exception exception_in_writing_to_file) 
        {
            System.out.println("Error in Log_Information_to_Text_file (String Message_to_Log) -->"+ exception_in_writing_to_file);
        }
     
    }
    
    public String[] Get_Information_on_a_Particular_Instance(String Instance_Number)
    {
        int Line_Number = 0;
        II_Looking_inside_a_File New_Object_for_Class_Looking_inside_a_File = new II_Looking_inside_a_File();
            
        try 
        {
            Line_Number = New_Object_for_Class_Looking_inside_a_File.Find_Out_which_line_Search_Text_is(Instance_Number);
            System.out.println("Line Number --> "+ Line_Number);
        } 
        
        catch (Exception ex) 
        {
            Logger.getLogger(II_RMI_Server_Main_Method.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String[] Information_on_Instance = new String[4];
        

        
        Information_on_Instance = New_Object_for_Class_Looking_inside_a_File.Get_Info_from_Particular_lines_of_Text_File (Line_Number, Information_on_Instance); 
        
        return Information_on_Instance;
    
    }
}
