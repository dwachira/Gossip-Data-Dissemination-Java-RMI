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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

      /********************************************************
      *                                                       *
      *   Imports from JAVA Libraries                         *
      *                                                       *
      ********************************************************/

public class II_Looking_inside_a_File 
{
    public II_Looking_inside_a_File() 
    {
        
    }
    
    public int Find_Out_which_line_Search_Text_is (String Search_Text) 
    {       int line_number=0;

            try {
                           /************************************************************
                           *                                                           *
                           *   File reader to search within Text File                  *
                           *                                                           *
                           ************************************************************/
                            BufferedReader bf = new BufferedReader(new FileReader("Dynamic Multicast Table for Anti-Entropy Propagation.txt"));

                           /********************************************************
                           *                                                       *
                           *   Start a line count and declare a string             *
                           *   to hold our current line.                           *
                           *                                                       *
                           ********************************************************/
                            int linecount = 0;
                            String line;
                            String search_word_puzzle = Search_Text;

                           /********************************************************
                           *                                                       *
                           *   Loop through each line, stashing the line           *
                           *   into our line variable.                             *
                           *                                                       *
                           ********************************************************/

                            while (( line = bf.readLine()) != null)
                            {
                                    /********************************************************
                                    *                                                       *
                                    *   Increment the count and find the index of the word  *
                                    *                                                       *
                                    ********************************************************/
                                    linecount++;
                                    int indexfound = line.indexOf(search_word_puzzle);

                                    /********************************************************
                                    *                                                       *
                                    *   If greater than -1, means we found the word         *
                                    *                                                       *
                                    ********************************************************/
                                    if (indexfound > -1) {
                                        line_number=linecount; 
                                        System.out.println("Word was found at position " + indexfound + " on line " + linecount);
                                    }
                            }

                                    /********************************************************
                                    *                                                       *
                                    *   Close the file after done searching                 *
                                    *                                                       *
                                    ********************************************************/

                            bf.close();
                    }
                    catch (IOException e) {
                            System.out.println("IO Error Occurred: " + e.toString());
                    }
          /********************************************************
          *                                                       *
          *   Return to the Caller function the Line number       *
          *   where the Search Text has been found in the file    *
          *                                                       *
          ********************************************************/

            return line_number;
    }
    
    
public String[] Get_Info_from_Particular_lines_of_Text_File (int line_number_to_start, String [] Information_on_Instance) 
{
    String fileName = "Dynamic Multicast Table for Anti-Entropy Propagation.txt";
    int counter = 0;

      /********************************************************
      *                                                       *
      *   This will reference one line at a time              *
      *                                                       *
      ********************************************************/
    String line = null;
    FileReader fileReader = null;

    try {
      /********************************************************
      *                                                       *
      *   FileReader reads text files in the default encoding *
      *                                                       *
      ********************************************************/

        fileReader = new FileReader(fileName);

      /********************************************************
      *                                                       *
      *   FileReader is wrapped in BufferedReader.            *
      *                                                       *
      ********************************************************/
        
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int Read_Counter = 0;
        
        while((line = bufferedReader.readLine()) != null) {
            counter++;
            
      /********************************************************
      *                                                       *
      *   Read four lines from where the instance  was found  *
      *                                                       *
      ********************************************************/    
            if(counter == line_number_to_start || counter == (line_number_to_start+1) || counter == (line_number_to_start+2) || counter == (line_number_to_start+3))
            {
                Information_on_Instance[Read_Counter] = line.substring(line.lastIndexOf(":") + 1);
                System.out.println(""+line);
                Read_Counter++;
               
            }
        }   
        bufferedReader.close();
    }
    catch(FileNotFoundException ex) 
    {
        System.out.println("Unable to open file '" + fileName + "'");                
    }
    catch(IOException ex) 
    {
        System.out.println("Error reading file '" + fileName + "'");  
    }
    finally
    {
        if(fileReader != null){
      /********************************************************
      *                                                       *
      *   Always close files.                                 *
      *                                                       *
      ********************************************************/
           //bufferedReader.close();            
        }
    }
        return Information_on_Instance;
}
}
