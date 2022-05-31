package vu.lt.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;


//  Interceptor classes may be designated with the optional
//        javax.interceptor.Interceptor annotation, but interceptor classes
//        aren’t required to be so annotated. An interceptor class must have a
//public, no-argument constructor.

//        The target class can have any number of interceptor classes associated with
//        it. The order in which the interceptor classes are invoked is determined
//        by the order in which the interceptor classes are defined in the
//        javax.interceptor.Interceptors annotation. However, this order can be overridden
//        in the deployment descriptor.

//        Interceptor classes may be targets of dependency injection.
//        Dependency injection occurs when the interceptor class instance is created,
//        using the naming context of the associated target class, and before any
//@PostConstruct callbacks are invoked.

@Interceptor
@LoggedInvocation
public class MethodLogger implements Serializable{
    @AroundInvoke
    public Object logMethodInvocation(InvocationContext context) throws Exception {
        System.out.println("Called method: " + context.getMethod().getName());
        return context.proceed();
    }
}