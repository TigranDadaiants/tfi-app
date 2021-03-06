package ru.sstu.vec.core.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sstu.vec.core.dao.UserDao;
import ru.sstu.vec.core.domain.User;

/**
 * Class for database authentication.
 *
 * @author Denis A. Murashev
 * @since VEC 2.0
 */
@Transactional
@Service("userDetailsService")
class UserDetailsServiceImpl implements UserDetailsService {

	@Resource
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String login) {
		User user = userDao.find(login);
		if (user == null) {
			return null;
		}
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		if (user.isAdmin()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		if (user.isExpert()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_EXPERT"));
		}
		if (user.isTeacher()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
		}
		if (user.isObserver()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_OBSERVER"));
		}
		if (user.isStudent()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
		}
		return new UserDetailsImpl(user, authorities);
	}

	public final class UserDetailsImpl implements UserDetails {

		private static final long serialVersionUID = 8462253807654250797L;

		private final User user;
		private final Set<GrantedAuthority> authorities;

		private UserDetailsImpl(User user, Set<GrantedAuthority> authorities) {
			this.user = user;
			this.authorities = authorities;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public String getUsername() {
			return user.getLogin();
		}

		@Override
		public String getPassword() {
			return user.getPassword();
		}

		@Override
		public Collection<GrantedAuthority> getAuthorities() {
			return authorities;
		}
	}
}
