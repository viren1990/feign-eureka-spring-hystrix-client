package demo;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

@Component
public class HelloClientFallBackFactory implements FallbackFactory<HelloClient> {


    @Override
    public HelloClient create(Throwable cause) {
        System.out.println(ExceptionUtils.getMessage(cause));

        String httpStatus = cause instanceof FeignException ? Integer.toString(((FeignException) cause).status()) : cause instanceof HystrixTimeoutException ? "time out" :
                cause.getMessage();
        System.out.println(httpStatus);
        return () -> "status received is " + httpStatus;
    }
}