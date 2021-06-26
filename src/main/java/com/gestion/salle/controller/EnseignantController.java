package com.gestion.salle.controller;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.salle.model.ERole;
import com.gestion.salle.model.Enseignant;
import com.gestion.salle.model.Etudiant;
import com.gestion.salle.model.Role;
import com.gestion.salle.model.User;
import com.gestion.salle.repository.EnseigantRepository;
import com.gestion.salle.repository.EtudiantRepository;
import com.gestion.salle.repository.RoleRepository;
import com.gestion.salle.repository.UserRepository;
import com.gestion.salle.response.JwtResponse;
import com.gestion.salle.response.MessageResponse;
import com.gestion.salle.security.jwt.JwtUtils;
import com.gestion.salle.security.request.LoginRequest;
import com.gestion.salle.security.request.SignupRequest;
import com.gestion.salle.security.service.UserDetailsImpl;
import com.gestion.salle.security.service.UserDetailsServiceImpl;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/enseignant")
public class EnseignantController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EnseigantRepository enseignantRepository;
	
	//@Autowired
	//EtudiantRepository etudiantRepository;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
		
		// Sign in one User with his Email and password
	@PostMapping("/signin")
	public Object authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
		Optional<Enseignant> enseignant = enseignantRepository.findByEmail(loginRequest.getEmail());
		if (!enseignant.isPresent()) {
						
			return enseignant;
						
		} else {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				String jwt = jwtUtils.generateJwtToken(authentication);
				
				UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
				List<String> roles = userDetails.getAuthorities().stream()
						.map(item -> item.getAuthority()).collect(Collectors.toList());
				return Map.ofEntries(
						Map.entry("StatusCode", 200),
						Map.entry("Message", "Success"),
						Map.entry("Data", new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles))
						);
		}
		
			//return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
			//return ResponseEntity.ok(user);
	}
	
	// Add one User
			@SuppressWarnings("static-access")
			@PostMapping("/add")
			public ResponseEntity<?> registerEnseignant(@Valid @RequestBody SignupRequest signUpRequest) {

				if (enseignantRepository.existsByEmail(signUpRequest.getEmail())) {
					return ResponseEntity
							.badRequest()
							.body(new MessageResponse("Error: Email is already in use!"));
				}
				
				if (enseignantRepository.existsByEmail(signUpRequest.getEmail())) {
					return ResponseEntity
							.badRequest()
							.body(new MessageResponse("Error: Email is already taken!"));
				}

				Enseignant enseignant = new Enseignant(signUpRequest.getEmail(), signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()), signUpRequest.getContact());
				
				Set<String> strRoles = signUpRequest.getRole();
				Set<Role> roles = new HashSet<>();
				
				if (strRoles == null) {
					Role userRole = roleRepository.findByName(ERole.ROLE_USER);
					if(userRole == null) {
						logger.error("Error: Role is not found.");
					}
					roles.add(userRole);
				} else {
					strRoles.forEach(role -> {
						switch (role) {
						case "admin":
							Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
							if(adminRole == null) {
								logger.error("Error: Role is not found.");
							}
							roles.add(adminRole);

							break;
						default:
							Role userRole = roleRepository.findByName(ERole.ROLE_USER);
							if(userRole == null) {
								logger.error("Error: Role is not found.");
							}
							roles.add(userRole);
						}
					});
				}

				enseignant.setRoles(roles);
				enseignantRepository.save(enseignant);
				logger.info("User registered successfully!");
				
				return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

			}

		//Add Contact
		@PostMapping("/contact/{email}")
		public ResponseEntity<Enseignant> addUserContact(@PathVariable String email, @Valid @RequestBody User userDetails) throws ResourceNotFoundException{
			Enseignant enseignant = enseignantRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found for this email :: " + email));
			enseignant.setContact(userDetails.getContact());
			final Enseignant addContact = enseignantRepository.save(enseignant);
			return ResponseEntity.ok(addContact);
		}
		
		//Add Username
		@PostMapping("/username/{email}")
		public ResponseEntity<Enseignant> addUsername(@PathVariable String email, @Valid @RequestBody User userDetails) throws ResourceNotFoundException{
			Enseignant enseignant = enseignantRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found for this email :: " + email));
			enseignant.setUsername(userDetails.getUsername());
			final Enseignant addUsername = enseignantRepository.save(enseignant);
			return ResponseEntity.ok(addUsername);
		}
		
		//Change Password
		@PostMapping("/password/{email}")
		public ResponseEntity<Enseignant> addUserPassaword(@PathVariable String email, @Valid @RequestBody User userDetails) throws ResourceNotFoundException{
			Enseignant enseignant = enseignantRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found for this email :: " + email));
			enseignant.setPassword(encoder.encode(userDetails.getPassword()));
			final Enseignant addPassword = enseignantRepository.save(enseignant);
			return ResponseEntity.ok(addPassword);
		}
		
		// Get all Users
		@GetMapping("/getall")
		public List<Enseignant> getUsers(){
			return enseignantRepository.findAll();
		}
		
		// Get one user by his Email
		@GetMapping("/getbyemail/{email}")
		public ResponseEntity<Enseignant> getUserByEmail(
				@PathVariable(value = "email") String email) throws ResourceNotFoundException {
			Enseignant enseignant = enseignantRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found for this email :: " + email));
			if (userRepository.existsByEmail(email)) {
				logger.info("User found");
				return ResponseEntity.ok().body(enseignant);
			}else {
				logger.error("User not found");
				return ResponseEntity.ok().body(null);
			}
			
		}	
}
