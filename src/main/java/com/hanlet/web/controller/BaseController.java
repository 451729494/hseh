package com.hanlet.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanlet.biz.entity.SysUser;
import com.hanlet.biz.service.RedisService;
import com.hanlet.biz.service.SysUserService;
import com.hanlet.config.properties.OAuth2Properties;
import com.hanlet.util.JsonUtil;
import com.hanlet.util.Result;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Controller
public class BaseController {

	@Autowired
	private RedisService redisServce;

	@Autowired
	private SysUserService userService;

	@Autowired
	private OAuth2Properties oAuth2Properties;

	@Autowired
	private TokenStore tokenStore;

	// -------------------Retrieve All
	// Users--------------------------------------------------------

	// @Autowired
	// private ConsumerTokenServices consumerTokenServices;

//	@RequestMapping(value = "/oauth/logout", method = RequestMethod.GET)
//	public Result<String> logout(HttpServletRequest request) {
//		Result<String> result = new Result<String>();
//		String authHeader = request.getHeader("Authorization");
//		if (authHeader != null) {
//			String tokenValue = authHeader.replace("Bearer", "").trim();
//			OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
//			tokenStore.removeAccessToken(accessToken);
//			result.setCode(200);
//			result.setMsg("注销成功");
//			return result;
//		} else {
//			result.setCode(500);
//			result.setMsg("注销失败");
//			return result;
//		}
//
//	}

	@ResponseBody
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<SysUser> listAllUsers() {
		List<SysUser> users = userService.findAll();
		if (users.isEmpty()) {
			return null;
		}
		return users;
	}

	@ResponseBody
	@GetMapping("/user/test")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Object getCurrentUser1(Authentication authentication, HttpServletRequest request)
			throws UnsupportedEncodingException {
		System.out.println(
				"【SecurityOauth2Application】 getCurrentUser1 authenticaiton={}" + JsonUtil.toJson(authentication));

		String header = request.getHeader("Authorization");
		String token = StringUtils.substringAfter(header, "bearer ");

		Claims claims = Jwts.parser().setSigningKey(oAuth2Properties.getJwtSigningKey().getBytes("UTF-8"))
				.parseClaimsJws(token).getBody();
		String blog = (String) claims.get("blog");
		System.out.println("【SecurityOauth2Application】 getCurrentUser1 blog={}" + blog);

		return authentication;
	}

	// -------------------Retrieve Single
	// User--------------------------------------------------------

	/*
	 * @RequestMapping(value = "/user/{id}", method = RequestMethod.GET,
	 * produces =
	 * {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	 * public ResponseEntity<User> getUser(@PathVariable("id") long id) {
	 * System.out.println("Fetching User with id " + id); User user =
	 * userService.findById(id); if (user == null) {
	 * System.out.println("User with id " + id + " not found"); return new
	 * ResponseEntity<User>(HttpStatus.NOT_FOUND); } return new
	 * ResponseEntity<User>(user, HttpStatus.OK); }
	 */

	// -------------------Create a
	// User--------------------------------------------------------

	/*
	 * @RequestMapping(value = "/user/", method = RequestMethod.POST) public
	 * ResponseEntity<Void> createUser(@RequestBody User user,
	 * UriComponentsBuilder ucBuilder) { System.out.println("Creating User " +
	 * user.getName());
	 * 
	 * if (userService.isUserExist(user)) {
	 * System.out.println("A User with name " + user.getName() +
	 * " already exist"); return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	 * }
	 * 
	 * userService.saveUser(user);
	 * 
	 * HttpHeaders headers = new HttpHeaders();
	 * headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.
	 * getId()).toUri()); return new ResponseEntity<Void>(headers,
	 * HttpStatus.CREATED); }
	 */

	// ------------------- Update a User
	// --------------------------------------------------------

	/*
	 * @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT) public
	 * ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody
	 * User user) { System.out.println("Updating User " + id);
	 * 
	 * User currentUser = userService.findById(id);
	 * 
	 * if (currentUser==null) { System.out.println("User with id " + id +
	 * " not found"); return new ResponseEntity<User>(HttpStatus.NOT_FOUND); }
	 * 
	 * currentUser.setName(user.getName()); currentUser.setAge(user.getAge());
	 * currentUser.setSalary(user.getSalary());
	 * 
	 * userService.updateUser(currentUser); return new
	 * ResponseEntity<User>(currentUser, HttpStatus.OK); }
	 */

	// ------------------- Delete a User
	// --------------------------------------------------------

	/*
	 * @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	 * public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
	 * System.out.println("Fetching & Deleting User with id " + id);
	 * 
	 * User user = userService.findById(id); if (user == null) {
	 * System.out.println("Unable to delete. User with id " + id +
	 * " not found"); return new ResponseEntity<User>(HttpStatus.NOT_FOUND); }
	 * 
	 * userService.deleteUserById(id); return new
	 * ResponseEntity<User>(HttpStatus.NO_CONTENT); }
	 */

	// ------------------- Delete All Users
	// --------------------------------------------------------

	/*
	 * @RequestMapping(value = "/user/", method = RequestMethod.DELETE) public
	 * ResponseEntity<User> deleteAllUsers() {
	 * System.out.println("Deleting All Users");
	 * 
	 * userService.deleteAllUsers(); return new
	 * ResponseEntity<User>(HttpStatus.NO_CONTENT); }
	 */
}
