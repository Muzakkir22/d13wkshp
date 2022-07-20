package sg.edu.nus.iss.day13wkshp;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.ApplicationArguments;
import sg.edu.nus.iss.day13wkshp.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

@SpringBootApplication
public class Day13wkshpApplication implements ApplicationRunner {

	@Autowired
	DatabaseService dbSvc;

	public static void main(String[] args) {
		SpringApplication.run(Day13wkshpApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args)  {
		if (args.containsOption("dataDir")) {
			final String dataDir = args.getOptionValues("dataDir").get(0);
			dbSvc.setDataDir(new File(dataDir));

			if (!dbSvc.isDataDirValid()) {
				System.err.printf("%s does not exist, is not a directory or not writable");
				System.exit(-1);
			}
			
			System.out.printf("Using %s as data directory\n", dataDir);
		} else {
			dbSvc.setDataDir(new File("./data"));
		}
	}

}
