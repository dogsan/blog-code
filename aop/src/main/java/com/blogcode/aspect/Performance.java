package com.blogcode.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by jojoldu@gmail.com on 2016-12-05.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */
@Aspect
public class Performance {

    @Pointcut("execution(* com.blogcode.board.BoardService.getBoards(..))")
    public void getBoards(){}

    @Pointcut("execution(* com.blogcode.user.UserService.getUsers(..))")
    public void getUsers(){}

    @Around("getBoards() || getUsers()")
    public Object calculatePerformanceTime(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        try {
            long start = System.currentTimeMillis();
            result = proceedingJoinPoint.proceed();
            long end = System.currentTimeMillis();

            System.out.println("수행 시간 : "+ (end - start));
        } catch (Throwable throwable) {
            System.out.println("exception! ");
        }
        return result;
    }
}
