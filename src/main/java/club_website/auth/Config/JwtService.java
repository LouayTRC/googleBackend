package club_website.auth.Config;

import java.security.Key;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Service
public class JwtService {
	
	private static final String SECRET_KEY="ZCHJgoqcOS3gih6iT0Mucq9uuxiH/zyvnsJ4hNOpW68MRQDbwuuI7vavh67OAUybYygbt8kl3/4MOnql69qI+SgFrlawljFj/ClP++soMVse7Vl/rptb9F1wBiq9C/OaDxdR6s1nt2GYw8tZz0eIJmf8AGIiBcg0o2nTcMQX2/F7gEb/zgvZrospPE0/bFhRVWe2UG1bvgj7lt3UHPYZJHjFCiqKzr626shhZwjg1z1kEALwAR7SejGUKLpTyGpx/iQg6ZBXZqEY38QNTPJSGnIgm6stBlzXrjnHh33HtzQGYz2AcbJY1GoVKEzPzhXweG304N5v6n8JY7z9LiqR9oxMaO7Frpwy39yeUbpw4L8=";
	
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(),userDetails);
	}
	
	public String generateToken(
			Map<String, Object> extraClaims,
			UserDetails userDetails){
		return Jwts
				.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ 1000 *60 *24))
				.signWith(getSigningKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String extractUsername(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token, Claims::getSubject);
	}
	
	public <T> T extractClaim(String token,Function<Claims,T> claimsResolver) {
		final Claims claims=extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	private Key getSigningKey() {
		// TODO Auto-generated method stub
		byte[] keybytes=Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keybytes);
	}
	
	public boolean isTokenValid(String token,UserDetails userDetails) {
		final String username=extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
	}
}
