package com.binoofactory.mph.web.auth.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@Entity(name="UM_AUTH_TKN")
@NoArgsConstructor 
public class OAuthVO implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TKN_ID", nullable=false, unique = false)
	public String tokenId;
    @Column(name = "CUST_NO", nullable=false, unique = true)
	public String username;
    @Column(name = "PASSWORD", nullable=false)
	public String password;
    @Column(name = "enabled", nullable=false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
	public boolean enabled;
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    authorities.add((GrantedAuthority) () -> "ROLE_USER");
	    return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
}
