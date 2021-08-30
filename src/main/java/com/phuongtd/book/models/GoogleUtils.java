package com.phuongtd.book.models;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
@Component
public class GoogleUtils {
    public GooglePojo getUserInfo(final String accessToken) throws ClientProtocolException, IOException, IllegalAccessException, GeneralSecurityException {
//        String link = env.getProperty("google.link.get.user_info") + accessToken;
//        String response = Request.get(link).execute().returnContent().asString();
//        ObjectMapper mapper = new ObjectMapper();
//        GooglePojo googlePojo = mapper.readValue(response, GooglePojo.class);
//        System.out.println(googlePojo);
//        return googlePojo;
        NetHttpTransport transport = new NetHttpTransport();
        GsonFactory factory = new GsonFactory();
//        System.out.println(accessToken);
//        String link = env.getProperty("google.link.get.user_info") + accessToken;
//        String response = Request.Get(link).execute().returnContent().asString();
//        ObjectMapper mapper = new ObjectMapper();
//        GooglePojo googlePojo = mapper.readValue(response, GooglePojo.class);
//        System.out.println(googlePojo);
//        return googlePojo;
        String token = accessToken;

        // Create verifier
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, factory)
                .build();

        // Verify it
        GoogleIdToken idToken = verifier.verify(token);
        if (idToken == null) {
            throw new IllegalAccessException("Invalid id_token");
        }
        Payload payload = idToken.getPayload();

        // Print user identifier
        String userId = payload.getSubject();
        System.out.println("User ID: " + userId);

        // Get profile information from payload
        String email = payload.getEmail();
        boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
        String name = (String) payload.get("name");
        String pictureUrl = (String) payload.get("picture");
        String locale = (String) payload.get("locale");
        String familyName = (String) payload.get("family_name");
        String givenName = (String) payload.get("given_name");
        // Access payload
        System.out.println("Email: " + idToken.getPayload().getEmail());
        System.out.println("Name: "+ name);
        System.out.println("pictureUrl: "+pictureUrl);
        System.out.println("givenName"+givenName);
        System.out.println("familyName"+familyName);
        GooglePojo googlePojo = new GooglePojo();
        googlePojo.setEmail(idToken.getPayload().getEmail());
        googlePojo.setFamilyName(familyName);
        googlePojo.setGivenName(givenName);
        googlePojo.setPicture(pictureUrl);
        //googlePojo.setName(name);
        return googlePojo;
    }
    public UserDetails buildUser(GooglePojo googlePojo) {
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_STAFF"));
        UserDetails userDetail = new User(googlePojo.getEmail(),
                "", enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        return userDetail;
    }
}