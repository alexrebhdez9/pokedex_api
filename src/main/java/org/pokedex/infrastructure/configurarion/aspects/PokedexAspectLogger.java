package org.pokedex.infrastructure.configurarion.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Modifier;
import java.util.Arrays;

@Aspect
@Configuration
public class PokedexAspectLogger {

    private static final Logger logger = LoggerFactory.getLogger(PokedexAspectLogger.class);


    /**
     *
     * @param joinPoint
     */
    @AfterThrowing("@annotation(org.pokedex.infrastructure.configurarion.aspects.PokedexLoggingAspect)")
    public void logAspectException(JoinPoint joinPoint) {

        String thread = String.valueOf(Thread.currentThread().getId());

        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();

        String methodName = joinPoint.getSignature().getName();

        JSONObject params = new JSONObject();

        // Method Information
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        params.put("declaring type: ", signature.getDeclaringType());

        // Method args
        Arrays.stream(signature.getParameterNames())
                .forEach(s -> params.put("arg name: ", s));

        Arrays.stream(signature.getParameterTypes())
                .forEach(s -> params.put("arg type: ", s));

        Arrays.stream(joinPoint.getArgs())
                .forEach(o -> params.put("arg value: ", o.toString()));

        // Additional Information
        params.put("returning type: ", signature.getReturnType());
        params.put("method modifier: ", Modifier.toString(signature.getModifiers()));
        Arrays.stream(signature.getExceptionTypes())
                .forEach(aClass -> params.put("exception type: ", aClass));

        logger.info(generateLoggingMessage(className, methodName, thread, params));


    }

    /**
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(org.pokedex.infrastructure.configurarion.aspects.PokedexLoggingAspect)")
    public Object pokedexLogsAround(ProceedingJoinPoint joinPoint) throws Throwable {

        String thread = String.valueOf(Thread.currentThread().getId());

        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();

        String methodName = joinPoint.getSignature().getName();

        JSONObject params = new JSONObject();

        // Method Information
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        params.put("declaring type: ", signature.getDeclaringType());

        // Method args
        Arrays.stream(signature.getParameterNames())
                .forEach(s -> params.put("arg name: ", s));

        Arrays.stream(signature.getParameterTypes())
                .forEach(s -> params.put("arg type: ", s));

        Arrays.stream(joinPoint.getArgs())
                .forEach(o -> params.put("arg value: ", o.toString()));

        // Additional Information
        params.put("returning type: ", signature.getReturnType());
        params.put("method modifier: ", Modifier.toString(signature.getModifiers()));
        Arrays.stream(signature.getExceptionTypes())
                .forEach(aClass -> params.put("exception type: ", aClass));

        logger.info(generateLoggingMessage(className, methodName, thread, params));

        return joinPoint.proceed();

    }

    /**
     *
     * @param className
     * @param method
     * @param thread
     * @param parameters
     * @return
     */
    private String generateLoggingMessage(String className, String method, String thread, JSONObject parameters) {

        return " | " +
                className +
                "[" + method + "]" +
                " | " +
                thread +
                " | " +
                " " + "parameters" + " = " +
                parameters +
                " | ";

    }
}