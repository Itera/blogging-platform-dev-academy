package no.itera.bloggingplatform;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static no.itera.bloggingplatform.constant.ProfileDefinition.IN_MEMORY_STORAGE;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(IN_MEMORY_STORAGE)
public class BloggingPlatformApplicationTests {

    @Test
    public void contextLoads() {
    }

}
