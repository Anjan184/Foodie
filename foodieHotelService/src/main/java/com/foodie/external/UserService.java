package com.foodie.external;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@FeignClient(name = "FOODIE-USER-SERVICE")
public interface UserService {

//    @PostMapping("/auth/validate")
//    boolean validateToken(String token);

}
