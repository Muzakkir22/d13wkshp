package sg.edu.nus.iss.day13wkshp;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.*;

@SpringBootTest
public class FunctionTest {
    private Logger logger = Logger.getLogger(FunctionTest.class.getName());

    private Path workingDir;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void readContact() throws Exception {
        this.workingDir = Path.of("", "c:/data");
        Path file = this.workingDir.resolve("27dbd2c6");
        String content = Files.readString(file);
        assertThat(content, is(notNullValue()));
    }   

    @Test
    public void saveContact() throws Exception {

        MultiValueMap<String, String> payload = new LinkedMultiValueMap<String, String>();
        payload.add("name", "Muz");
        payload.add("email", "Muz123@gmail.com");
        payload.add("phone", "12345678");

        logger.log(Level.INFO, "" + objectMapper.writeValueAsString(payload));
    }
}
