package net.tcheltsou.springmvclearning.service;


import net.tcheltsou.springmvclearning.entity.entity_from_schema.GetUserRequest;
import net.tcheltsou.springmvclearning.entity.entity_from_schema.GetUserResponse;
import net.tcheltsou.springmvclearning.entity.entity_from_schema.User;
import net.tcheltsou.springmvclearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {
	private static final String NAMESPACE_URL = "http://epam.com/user-web-service";

	@Autowired
	private UserRepository userRepository;

	@PayloadRoot(namespace = NAMESPACE_URL, localPart = "getUserRequest")
	@ResponsePayload
	public GetUserResponse getUsers(@RequestPayload GetUserRequest request) {
		GetUserResponse response = new GetUserResponse();
		response.setUser(new User(userRepository.read((long) request.getId())));
		return response;
	}
}
