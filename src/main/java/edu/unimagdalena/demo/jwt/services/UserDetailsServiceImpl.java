package edu.unimagdalena.demo.jwt.services;



import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.unimagdalena.demo.jwt.model.MyUserDetails;
import edu.unimagdalena.demo.jwt.model.User;
import edu.unimagdalena.demo.jwt.repository.UserRepository;


import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/*@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User usuario = usuarioRepository.findByUsername(username);
		if (usuario == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(usuario.getUsername(), usuario.getPassword(), emptyList());
	}*/
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(userName);
		
		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
		
		/*if(user == null)
			throw new UsernameNotFoundException("Not found: " + userName));
		*/
		
		
		return user.map(MyUserDetails::new).get();
	}

	public UserDetails findUserById(Integer id) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findById(id);
		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + id));
		return user.map(MyUserDetails::new).get();
	}

	public UserDetails getLoggedInUser() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails;
	}
}
