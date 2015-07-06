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

package II_RMI_Message_Package;

      /********************************************************
      *                                                       *
      *   Imports from JAVA RMI Libraries and other Libraries *
      *                                                       *
      ********************************************************/
import II_RMI_Main.II_RMI_Server_Main_Method;
import II_RMI_Main.Write_To_Infection_File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JOptionPane;
 
public class II_Message_Implementation_at_Server  extends UnicastRemoteObject implements II_Message 
{
    public static String Msg_Update_Received = "";
    II_RMI_Server_Main_Method New_Object_for_Class_RMI_Server_Main_Method = new II_RMI_Server_Main_Method();
    
    public II_Message_Implementation_at_Server() throws RemoteException 
    {
        
    }
     
   
    public String Pass_Message(String Message_Received) throws RemoteException 
    {
        Msg_Update_Received = Message_Received;
      /********************************************************
      *                                                       *
      *   Print on the Server screen the message received     *
      *   from the Client.                                    *
      *                                                       *
      ********************************************************/
        //System.out.println("Message received by Instance III ["+ Message_Received+"]");
        
        
        New_Object_for_Class_RMI_Server_Main_Method.Log_Information_to_Text_file("Message received by Instance III ["+ Message_Received+"]");
        JOptionPane.showMessageDialog(null, ""+ Message_Received, "Message Received - Instance III", JOptionPane.INFORMATION_MESSAGE);
        
        String Return_from_Server_to_Client = "";
        Return_from_Server_to_Client = Message_Received + "";
        
        //System.out.println("\n********************************************************\n");
        New_Object_for_Class_RMI_Server_Main_Method.Log_Information_to_Text_file("\n********************************************************\n");
        
        Write_To_Infection_File file_update_write = new Write_To_Infection_File();
        file_update_write.Write_To_Infection_File();
        return Return_from_Server_to_Client;
        
    }


}


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