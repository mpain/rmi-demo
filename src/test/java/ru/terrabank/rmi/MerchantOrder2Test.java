package ru.terrabank.rmi;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


/**
 * User: sergey.samoylov@gmail.com
 * Date: 8/17/11
 * Time: 9:36 PM
 */
@Test
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class MerchantOrder2Test extends AbstractTestNGSpringContextTests {
    private static final Logger log = LoggerFactory.getLogger(MerchantOrder2Test.class);

    @Autowired
    @Qualifier("rmiClientQualifier")
    private IMerchantOrder2 rmiClient;

    @BeforeSuite
    public void setup() {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
    }

    @Test
    public void simpleTest() {
        log.info("This is a simple log message....");

        final int THREADS = 10;

        Thread[] threads = new Thread[THREADS];
        for (int j = 0; j < threads.length; j++) {
            threads[j] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        String result = rmiClient.doSomething(1234);
                        Assert.assertEquals(result, "response");
                        log.info("I've got a response!!!");
                    }
                }
            });

            threads[j].start();
        }

        log.debug("Joining...");
        for (int j = 0; j < threads.length; j++) {
            try {
                threads[j].join();
                log.info("Thread {} has been joined successfully", j);
            } catch (InterruptedException e) {
                log.warn("An error was handled during joining of thread {}", j);
            }
        }
    }
}
