package net.bluepoet.slf4jtest.example;

import net.logstash.logback.marker.Markers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * Created by bluepoet on 2017. 9. 14..
 */
public class UserService {
	Logger logger = LoggerFactory.getLogger(UserService.class);

	public User getUserInfo(long id) {
		if (id == 1) {
			User tester = new User(1, "tester", 10);
			logger.info("user id: {}, name: {}, age: {}", tester.getId(), tester.getName(), tester.getAge());
			return tester;
		}

		final User bluepoet = new User(2, "bluepoet", 20);
		logger.info(Markers.append("userinfo",
				new HashMap<String, Object>() {
					{
						put("id", bluepoet.getId());
						put("name", bluepoet.getName());
						put("age", bluepoet.getAge());

					}
				}), "user info");
		return bluepoet;
	}
}
