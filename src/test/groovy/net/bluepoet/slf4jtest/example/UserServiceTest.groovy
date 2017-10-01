package net.bluepoet.slf4jtest.example

import net.logstash.logback.argument.StructuredArguments
import spock.lang.Specification
import uk.org.lidalia.slf4jtest.LoggingEvent
import uk.org.lidalia.slf4jtest.TestLogger
import uk.org.lidalia.slf4jtest.TestLoggerFactory

/**
 * Created by bluepoet on 2017. 10. 02..
 */
class UserServiceTest extends Specification {
    TestLogger logger = TestLoggerFactory.getTestLogger(UserService.class)
    UserService userService = new UserService()

    def "tester 유저정보를 가져올 때 로그메세지(문자열 데이터)를 확인한다."() {
        when:
        userService.getUserInfo(1L)

        then:
        LoggingEvent loggingEvent = logger.getLoggingEvents().get(0)
        with(loggingEvent) {
            message == 'user id: {}, name: {}, age: {}'
            arguments == [1, 'tester', 10]
        }
    }

    def "bluepoet 유저정보를 가져올 때 로그메세지(Marker object)를 확인한다."() {
        when:
        userService.getUserInfo(2L)

        then:
        LoggingEvent loggingEvent = logger.getLoggingEvents().get(0)
        with(loggingEvent) {
            message == 'user info'

            with(marker.get()) {
                fieldName == 'userinfo'
                fieldValue == StructuredArguments.toString([name: 'bluepoet', id: 2, age: 20])
            }
        }
    }

    void cleanup() {
        logger.clear()
    }
}