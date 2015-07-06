package II_RMI_Main;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import II_RMI_Main.II_RMI_Server_Main_Method;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class File_Monitor_for_Changes {
    II_RMI_Server_Main_Method log_work_done = new II_RMI_Server_Main_Method();
    int File_Change_Counter = 0;
    // A hardcoded path to a folder you are monitoring .
    public static final String FOLDER =
            "./Infection_Data_File";

    public void File_Monitor_for_Changes() throws Exception
    {
        // The monitor will perform polling on the folder every 5 seconds
        final long pollingInterval = 1 * 1000;

        File folder = new File(FOLDER);

        if (!folder.exists()) {
            // Test to see if monitored folder exists
            throw new RuntimeException("Directory not found: " + FOLDER);
        }

        FileAlterationObserver observer = new FileAlterationObserver(folder);
        FileAlterationMonitor monitor =
                new FileAlterationMonitor(pollingInterval);
        FileAlterationListener listener = new FileAlterationListenerAdaptor() {
            // Is triggered when a file is created in the monitored folder
            @Override
            public void onFileCreate(File file) {
                try {
                    // "file" is the reference to the newly created file
                    System.out.println("File created: "
                            + file.getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }

            // Is triggered when a file is deleted from the monitored folder
            @Override
            public void onFileDelete(File file) {
                try {
                    // "file" is the reference to the removed file
                    System.out.println("File removed: "
                            + file.getCanonicalPath());
                    // "file" does not exists anymore in the location
                    System.out.println("File still exists in location: "
                            + file.exists());
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
            
            // Is triggered when a file is modified from the monitored folder
            @Override
            public void onFileChange(File file) 
            {
                try {
                    
                    System.out.println("File modified: "
                            + file.getCanonicalPath());
                    log_work_done.Log_Information_to_Text_file("File modified: "+ file.getCanonicalPath());
                    
//                    while (File_Change_Counter < 3)//array_size_number_of_connected_nodes)
//                    {
                        log_work_done.Test_Message_Implementation();
                        //File_Change_Counter++;
                    //} 
                    monitor.stop();
                    log_work_done.Log_Information_to_Text_file("File monitor stopped through monitor.stop();");
//                    
//                    if (log_work_done.Counter_for_Spreading_Infection == 2)
//                    {
//                        monitor.stop();
//                        log_work_done.Log_Information_to_Text_file("File monitored stopped through monitor.stop();");
//                    
//                    }
                    
                    
                    log_work_done.Counter_for_Spreading_Infection++;
                    
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                } catch (Exception ex) {
                    Logger.getLogger(File_Monitor_for_Changes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        observer.addListener(listener);
        monitor.addObserver(observer);
        monitor.start();
        log_work_done.Log_Information_to_Text_file("File monitor started through monitor.start();");

    }
}