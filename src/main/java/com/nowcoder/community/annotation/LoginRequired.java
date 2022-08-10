package com.nowcoder.community.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //声明注解写在哪里
@Retention(RetentionPolicy.RUNTIME) //声明注解有效时长
public @interface LoginRequired {

}


