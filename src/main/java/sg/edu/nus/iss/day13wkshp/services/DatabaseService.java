package sg.edu.nus.iss.day13wkshp.services;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.*;
import java.util.*;

@Service
public class DatabaseService {

    private File dataDIr = new File("some directory");

    public File getDataDir() {
        return dataDIr;
    }

    public void setDataDir(File dataDir) {
        this.dataDir = dataDir;
    }

    // windows ---> /Users/<username/data
    // mac ---> /home/data
    public boolean isDataDirValid() {
        return dataDir.exists() && dataDir.isDirectory() && dataDir.canWrite();
    }
}
