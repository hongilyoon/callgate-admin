package com.hellowd.callgate.core.annotation.modelMapper;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(value = ModelMapperRegister.class)
public @interface EnableModelMapper {
}
