package com.bootcamp.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Any class annotated with <code>@RestController</code> is called
 * class. When a request is sent to the server the dispatcher
 * servlet (responsible for matching the requested endpoint with
 * the controller). A spring web application can have multiple
 * controllers.
 */
@RestController
public class HelloWorld {
    private static final String HELLO_WORLD_MSG = "Hello World!";

    /**
     * This method will return the {@link #HELLO_WORLD_MSG} if the endpoint
     * <code>/hello</code> is requested.
     *
     * @return
     */
    @GetMapping("/hello")
    public String returnHelloWorld() {
        return HELLO_WORLD_MSG;
    }
}
