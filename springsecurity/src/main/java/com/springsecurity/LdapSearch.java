package com.springsecurity;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.DefaultDirObjectFactory;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;

import com.springsecurity.user.User;


public class LdapSearch {
	public static final String BASE_DN = "cn=Administrators,ou=groups,ou=system";
	public static final String LDAP_URL = "ldap://localhost:10389";
	public static final String SEARCH_USER_DN = "uid=admin,ou=system";
	public static final String SEARCH_USER_PW = "secret";
	private LdapContextSource lcs;
	
	LdapSearch(){
		lcs = new LdapContextSource();
    	lcs.setUrl(LDAP_URL);
    	lcs.setUserDn(SEARCH_USER_DN);
    	lcs.setPassword(SEARCH_USER_PW);
    	lcs.setDirObjectFactory(DefaultDirObjectFactory.class);
    	lcs.afterPropertiesSet();
	}
	
	List<User> listPeople(){
		LdapTemplate ldapTemplate = new LdapTemplate(lcs);
    	AndFilter filter = new AndFilter();
    	filter.and(new EqualsFilter("objectclass","person"));
    	return (List<User>)ldapTemplate.search(BASE_DN,filter.encode(),1,new String[]{"entryUUID","uid","givenName","sn"},
    			new AttributesMapper(){

					public Object mapFromAttributes(Attributes attr) throws NamingException {
						
						return new User(attr.get("entryUUID").get().toString(),attr.get("uid").get().toString(),attr.get("givenName").get().toString(),attr.get("sn").get().toString());
					}
    		
    	});
	}
}
