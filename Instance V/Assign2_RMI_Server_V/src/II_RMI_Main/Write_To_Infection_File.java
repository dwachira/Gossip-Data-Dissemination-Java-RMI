/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package II_RMI_Main;

 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class Write_To_Infection_File {
	public void Write_To_Infection_File() {
        II_RMI_Server_Main_Method logging_method = new II_RMI_Server_Main_Method();    
            
		try {
                        
                        java.util.Date date= new java.util.Date();
			String content = "\nFile Updated - [" + new Timestamp(date.getTime()) + "] ";
                        
                        //System.out.println(new Timestamp(date.getTime()));

			File file = new File("./Infection_Data_File/Infection_Status.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
                        logging_method.Log_Information_to_Text_file (""+ content);
			bw.close();
 
			System.out.println("Done -->  File Updated - [" + new Timestamp(date.getTime()) + "]" );
                        
                       
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}