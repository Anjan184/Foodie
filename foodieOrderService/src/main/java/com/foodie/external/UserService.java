package com.foodie.external;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@Service
@FeignClient(name = "FOODIE-USER-SERVICE")
public interface UserService {

//    @PostMapping("/auth/validate")
//    boolean validateToken(String token);

}
