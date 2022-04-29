package com.bootcamp.spring;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private static final String HELLO_MSG = "Hello ";
    private static final String HELLO_WORLD_MSG = "Hello World!";
    private static final String HELLO_MSG_FROM_POST = "Hello! You performed a post request successfully.";


    /**
     * This method will return the {@link #HELLO_WORLD_MSG} if the endpoint
     * <code>/hello</code> is requested.
     *
     * @return String response
     */
    @GetMapping("/hello")
    public String returnHelloWorld() {
        return HELLO_WORLD_MSG;
    }

    /**
     * This method will return the {@link #HELLO_MSG_FROM_POST} if the endpoint
     * <code>/hello</code> is hit with HTTP POST request.
     *
     * @return String response
     */
    @PostMapping("/hello")
    public String sayHelloFromPost() {
        return HELLO_MSG_FROM_POST;
    }

    /**
     * This method will be invoked only if the HTTP POST request
     * hitting the endpoint contains a request body.
     *
     * @param name Request body will be injected by the controller
     * @return String response
     */
    @PostMapping(value = "/hello", consumes = MediaType.TEXT_PLAIN_VALUE)
    public String sayHelloWithNameFromPost(@RequestBody String name) {
        return HELLO_MSG + name;
    }
}
