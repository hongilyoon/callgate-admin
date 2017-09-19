package com.hellowd.callgate.core.annotation.bind;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@RequestMapping
public @interface RestApiController {

    @AliasFor(value = "path", annotation = RequestMapping.class)
    String[] path() default {};

    @AliasFor(value = "value", annotation = RestController.class)
    String value() default "";

}
