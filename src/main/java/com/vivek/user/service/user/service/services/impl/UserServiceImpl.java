package com.vivek.user.service.user.service.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vivek.user.service.user.service.entities.Hotel;
import com.vivek.user.service.user.service.entities.Rating;
import com.vivek.user.service.user.service.entities.User;
import com.vivek.user.service.user.service.exceptions.ResoueceNotFoundException;
import com.vivek.user.service.user.service.repositories.UserRepository;
import com.vivek.user.service.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		
		var randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResoueceNotFoundException("Resource not found on server"));		 
		
		//fetch rating from rating service
		
		Rating[] ratingsOfUser =  restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
		//logger.info("{}", ratingsOfUser);
		
		List<Rating> ratings = Arrays.stream(ratingsOfUser).collect(Collectors.toList());
		
		List<Rating> ratingList = ratings.stream()
				.map(rating -> {
					// http://localhost:8082/hotels/3522150a-156b-41da-a7fc-ab93bb1c24bd
					
					ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
					var hotel = forEntity.getBody();
					logger.info("response status code : {}" , forEntity.getStatusCode());
					rating.setHotel(hotel);
					return rating;
				})
				.collect(Collectors.toList());
				
		
		user.setRatings(ratingList);
		return user;
	}

}
