package ibf2022.batch2.ssf.frontcontroller.respositories;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.ssf.frontcontroller.model.User;

@Repository
public class AuthenticationRepository {

	// TODO Task 5
	// Use this class to implement CRUD operations on Redis

	@Autowired
	private RedisTemplate<String, String> template;

	public void save(String user){
        
		//save to redis with 30mins timeout
        this.template.opsForValue()
            .set(user, user, 1800, TimeUnit.SECONDS);
    }



	
	
}
