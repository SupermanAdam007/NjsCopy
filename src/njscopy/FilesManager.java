package njscopy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pavlat
 */
public class FilesManager {

    private File[] files;
    private File njsFile = null;
    private File easifierFile = null;

    public FilesManager(File dir) {
        this.files = null;
        if (dir.isDirectory()) {
            this.files = dir.listFiles();
        } else {
            System.err.println("Error: \"" + dir.getAbsolutePath() + "\" is not directory...");
        }
    }

    public File getNjsDef() throws IOException {
        if (this.njsFile != null) {
            return this.njsFile;
        }
        for (File file : files) {
            if (file.getName().equals("njs.rep")) {
                return file;
            }
        }
        return null;
    }

    public File geteasifier_outDat() throws IOException {
        if (this.easifierFile != null) {
            return this.easifierFile;
        }
        for (File file : files) {
            if (file.getName().equals("easifier_out.dat")) {
                return file;
            }
        }
        return null;
    }

    public void copyFile(Path from, Path to, String label) {
        to = new File(to.toString() + label).toPath();
        try {
            System.out.println("From: " + from.toString());
            System.out.println("To: " + to.toString());
            Files.move(from, to);
        } catch (IOException ex) {
            Logger.getLogger(FilesManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
