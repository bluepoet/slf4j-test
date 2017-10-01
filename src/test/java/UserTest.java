import net.bluepoet.slf4jtest.example.User;
import net.bluepoet.slf4jtest.example.UserService;
import net.logstash.logback.marker.Markers;
import org.junit.Test;
import org.slf4j.Marker;
import uk.org.lidalia.slf4jtest.TestLogger;
import uk.org.lidalia.slf4jtest.TestLoggerFactory;

import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;
import static uk.org.lidalia.slf4jtest.LoggingEvent.info;

/**
 * Created by bluepoet on 2017. 9. 15..
 */
public class UserTest {
    TestLogger logger = TestLoggerFactory.getTestLogger(UserService.class);

    @Test
    public void testName() throws Exception {
        UserService service = new UserService();

        final User user = service.getUserInfo(2L);

        Marker marker = Markers.append("userinfo", new HashMap<String, Object>() {
            {
                put("id", user.getId());
                put("name", user.getName());
                put("age", user.getAge());

            }
        });
        assertThat(logger.getAllLoggingEvents()).isEqualTo(Arrays.asList(info(marker, "user info")));

    }
}
