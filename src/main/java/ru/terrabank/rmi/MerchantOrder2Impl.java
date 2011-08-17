package ru.terrabank.rmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: sergey.samoylov@gmail.com
 * Date: 8/17/11
 * Time: 9:33 PM
 */
public class MerchantOrder2Impl implements IMerchantOrder2 {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String doSomething(int anArgument) {

        log.debug("The argument is: {}", anArgument);
        return "response";
    }
}
