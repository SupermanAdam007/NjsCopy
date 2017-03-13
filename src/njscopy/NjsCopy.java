package njscopy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pavlat
 */
public class NjsCopy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        Path resNjsDir = new File("C:\\ads_storage\\str_mult\\njs.def").toPath();
        Path malwareDir = new File("C:\\ads_storage\\class_malware").toPath();
        
        String mainPath = System.getProperty("user.dir");
        FilesManager fileMan = new FilesManager(new File(mainPath));
        
        System.out.println("Path: " + mainPath);
        
        System.out.println("Try to load njs.rep...");
        File njs = fileMan.getNjsDef();
        
        BufferedReader buffr = null;
        if (njs == null) {
            System.out.println("njs.rep not found...");
            System.exit(0);
        } else {
            System.out.println("njs.rep discovered");
        }
        buffr = new BufferedReader(new FileReader(njs));
        String line;
        
        while ((line = buffr.readLine()) != null) {
            if (!line.contains("<- OK") && line.length() > 50) {
                System.out.println(line.split(" ")[0]);
                String label = "\\" + line.split(" ")[0];
                fileMan.copyFile(new File(mainPath + label).toPath(), malwareDir, label);
            }
        }
        
        File easiout = fileMan.geteasifier_outDat();
        Files.write(resNjsDir, Files.readAllBytes(easiout.toPath()), StandardOpenOption.APPEND);

//        try {
//            if (fileMan.isDir()) {
//                for (File f : fileMan.getFiles()) {
//                    if (f.isFile()) {
//                        System.out.println("file: " + f);
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    
}
