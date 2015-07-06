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
      *   Imports from JAVA RMI Libraries                     *
      *                                                       *
      ********************************************************/

import java.rmi.Remote;
import java.rmi.RemoteException;
 
public interface II_Message extends Remote 
{
      /********************************************************
      *                                                       *
      *   JAVA Remotely Invocable Method (RMI)                *
      *   Return the message of the remote object             *
      *                                                       *
     * 
      ********************************************************/
    String Pass_Message(String course_subject_message) throws RemoteException;
}