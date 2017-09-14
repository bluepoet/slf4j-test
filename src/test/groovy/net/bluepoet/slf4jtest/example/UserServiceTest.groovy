package net.bluepoet.slf4jtest.example

import com.google.common.collect.SingletonImmutableList

import static uk.org.lidalia.slf4jtest.LoggingEvent.info;

import net.logstash.logback.marker.ObjectAppendingMarker
import org.slf4j.Marker
import spock.lang.Specification
import uk.org.lidalia.slf4jtest.LoggingEvent
import uk.org.lidalia.slf4jtest.TestLogger
import uk.org.lidalia.slf4jtest.TestLoggerFactory

/**
 * Created by poet.me on 2017. 9. 14..
 */
class UserServiceTest extends Specification {
    TestLogger logger = TestLoggerFactory.getTestLogger(UserService.class)
    UserService userService = new UserService()

    def "tester 유저정보를 가져올 때 로그메세지(문자열 데이터)를 확인한다."() {
        when:
        User user = userService.getUserInfo(1L)

        then:
        LoggingEvent loggingEvent = logger.getLoggingEvents().get(0)
        loggingEvent.message == 'user id: {}, name: {}, age: {}'
        loggingEvent.arguments == [1, 'tester', 10]
    }

    def "bluepoet 유저정보를 가져올 때 로그메세지(json 데이터)를 확인한다."() {
        when:
        User user = userService.getUserInfo(2L)

        then:
        LoggingEvent loggingEvent = logger.getLoggingEvents().get(0)
        logger.getLoggingEvents() == new SingletonImmutableList(info(createMarker(), "user info"))
    }

    Marker createMarker() {
        new ObjectAppendingMarker("userinfo", new HashMap<String, Object>() {
            {
                put('id', 2)
                put('name', 'bluepoet')
                put('age', 20)
            }
        })
    }

    void cleanup() {
        logger.clear()
    }
}