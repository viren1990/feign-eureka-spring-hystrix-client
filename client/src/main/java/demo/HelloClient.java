package demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(value = "HelloServer" , fallbackFactory = HelloClientFallBackFactory.class )
public interface HelloClient {
    @RequestMapping(value = "/service-instance/HelloServer", method = GET)
    String hello();
}
