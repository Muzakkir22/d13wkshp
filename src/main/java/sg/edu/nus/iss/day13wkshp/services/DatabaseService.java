package sg.edu.nus.iss.day13wkshp.services;

import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day13wkshp.models.Contact;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;

@Service
public class DatabaseService {

    private File dataDir = new File("some directory");

    public File getDataDir() {
        return dataDir;
    }

    public void setDataDir(File dataDir) {
        this.dataDir = dataDir;
    }

    // windows ---> /Users/<username/data
    // mac ---> /home/data
    public boolean isDataDirValid() {
        return dataDir.exists() && dataDir.isDirectory() && dataDir.canWrite();
    }

    public boolean save(final Contact contact) {
        File f = new File(this.dataDir, contact.getId());
        try (OutputStream out = new FileOutputStream(f)) {
            PrintWriter pw = new PrintWriter(out);
            pw.println(contact.getId());
            pw.println(contact.getName());
            pw.println(contact.getEmail());
            pw.println(contact.getPhone());
            pw.flush();

            return true;
        } catch (IOException ex) {
            System.err.printf("Error: %s", ex.getMessage());
            // ex.printStackTrace();
            return false;
        }
	}

	public Contact read(String fileId) {

        try {
            
            // File f = new File(this.dataDir, fileId);
            // Scanner myReader = new Scanner(f);
            // while (myReader.hasNextLine()) {
            //    System.out.println(myReader.nextLine());
            // }
            // myReader.close();

            Contact contact = new Contact();

            Path filePath = new File(this.dataDir, fileId).toPath();
            Charset charset = Charset.forName("utf-8");
            List<String> stringVal = Files.readAllLines(filePath, charset);

            contact.setName(stringVal.get(1));
            contact.setEmail(stringVal.get(2));
            contact.setPhone(stringVal.get(3));

            return contact;
        } catch (IOException ex) {
            System.err.printf("Error: %s", ex.getMessage());
            ex.printStackTrace();
            return null;
        }
	}
}
