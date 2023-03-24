package ibf2022.batch2.ssf.frontcontroller.services;

import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ibf2022.batch2.ssf.frontcontroller.respositories.AuthenticationRepository;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

@Service
public class AuthenticationService {

	public static final String URL = "https://auth.chuklee.com/api/authenticate";

	@Autowired
    private AuthenticationRepository AuthRepo;

	// TODO: Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	public void authenticate(String username, String password) throws Exception {

		//to convert params to JSON
		JsonObjectBuilder jsonParam = Json.createObjectBuilder()
								.add("username", username)
								.add("password", password);

		RequestEntity<String> req = RequestEntity.post(URL)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.body(jsonParam.build().toString());

		ResponseEntity<String> resp;
		RestTemplate template = new RestTemplate();
		try {
			resp = template.exchange(req, String.class);
		} catch (Exception ex) {
			throw ex;
		}

		String payload = resp.getBody();
		JsonReader reader = Json.createReader(new StringReader(payload));
		JsonObject json = reader.readObject();

		String valueReturn = json.getString("message");
		if (valueReturn.contains("Authenticated")) {
			// return view1 thereafter
		} else {
			//to add the count 

			//call to repo to store user details in redis for 30mins
			// disableUser(username);
		}

	}

	// TODO: Task 3
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public void disableUser(String username) {
		AuthRepo.save(username);
	}

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		return false;
	}
}
