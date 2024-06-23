package com.SwordboundSouls.controller;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.SwordboundSouls.entity.CharacterFighting;
import com.SwordboundSouls.entity.Characters;
import com.SwordboundSouls.entity.HollowFighting;
import com.SwordboundSouls.entity.Hollows;
import com.SwordboundSouls.entity.Users;
import com.SwordboundSouls.service.CharacterFightingService;
import com.SwordboundSouls.service.CharacterService;
import com.SwordboundSouls.service.HollowFightingService;
import com.SwordboundSouls.service.HollowService;
import com.SwordboundSouls.service.UserService;
@Controller
public class SwordboundSoulsController {
	//Dependency injection
	@Autowired
	private UserService uService;
	@Autowired
	private CharacterService cService;
	@Autowired
	private HollowService hService;
	@Autowired
	private CharacterFightingService cfService;
	@Autowired
	private HollowFightingService hfService;
	//Managing web pages
	@GetMapping("/")
	public String indexPage() {
		return "index";
	}
	@GetMapping("/signUpPage")
	public String signUpPage() {
		return "signUp";
	}
	@GetMapping("/manageWebPages")
	public ModelAndView manageWebPages(@RequestParam("webPage") String webPage,@RequestParam("username") String username,@RequestParam("characterName") String characterName) {
		ModelAndView modelAndView = new ModelAndView();
		if(webPage.equals("huntHollows")) {
			modelAndView = passEntitiesToViewAndSetView(getUser(username),getCharacterByName(characterName),getAllHollows(),webPage);
		}else {
			modelAndView = passEntitiesToViewAndSetView(getUser(username),getCharacterByName(characterName),webPage);
		}
		return modelAndView;
	}
	@PostMapping("/manageAdminWebPages")
	public ModelAndView manageAdminWebPages(@RequestParam("username") String username,@RequestParam("characterName") String characterName,@RequestParam("webPage") String webPage) {
		ModelAndView modelAndView = new ModelAndView();
		switch(webPage) {
		case "usersCrud":
			modelAndView = passEntitiesToViewAndSetView(getUser(username),getCharacterByName(characterName),"usersCrud",getAllUsers());
			break;
		case "charactersCrud":
			modelAndView = passEntitiesToViewAndSetView(getUser(username),getAllCharacters(),getCharacterByName(characterName),"charactersCrud");
			break;
		case "hollowsCrud":
			break;
		}
		return modelAndView;
	}
	//Special Methods
	@PostMapping("/logIn")
	public ModelAndView logIn(@ModelAttribute Users user) {
		ModelAndView modelAndView = new ModelAndView();
		if(userIsRegistered(user.getUsername(),user.getPass())) {
			if(userHasACharacter(user.getUsername())) {
				modelAndView = manageWebPages("home",user.getUsername(),getCharacterByUsername(user.getUsername()).getCharacterName());
			}else {
				modelAndView = passEntitiesToViewAndSetView(user.getUsername(),"createCharacter");
			}
		}else{
			modelAndView.setViewName("index");
		}
		return modelAndView;
	}
	public ModelAndView passEntitiesToViewAndSetView(Users u,Characters c,String view) {
		ModelAndView passDataToView = new ModelAndView();
		passDataToView.addObject("user", u);
		passDataToView.addObject("character", c);
		passDataToView.setViewName(view);
		return passDataToView;
	}
	public ModelAndView passEntitiesToViewAndSetView(Users u,List<Characters> charactersList,Characters c,String view) {
		ModelAndView passDataToView = new ModelAndView();
		passDataToView.addObject("user", u);
		passDataToView.addObject("character", c);
		passDataToView.addObject("allCharacters",charactersList);
		passDataToView.setViewName(view);
		return passDataToView;
	}
	public ModelAndView passEntitiesToViewAndSetView(String username,String view) {
		ModelAndView passDataToView = new ModelAndView();
		passDataToView.addObject("username", username);
		passDataToView.setViewName(view);
		return passDataToView;
	}
	public ModelAndView passEntitiesToViewAndSetView(Users u,Characters c,List<Hollows> hollows,String view) {
		ModelAndView passDataToView = passEntitiesToViewAndSetView(u,c,"");
		passDataToView.addObject("hollows", getAllHollows());
		passDataToView.setViewName(view);
		return passDataToView;
	}
	public ModelAndView passEntitiesToViewAndSetView(Users u,CharacterFighting cf,HollowFighting hf,String view) {
		ModelAndView passDataToView = new ModelAndView();
		passDataToView.addObject("user", u);
		passDataToView.addObject("character", cf);
		passDataToView.addObject("hollow", hf);
		passDataToView.setViewName(view);
		return passDataToView;
	}
	public ModelAndView passEntitiesToViewAndSetView(Users u,Characters c,String view,List<Users> usersList) {
		ModelAndView passDataToView = new ModelAndView();
		passDataToView.addObject("user", u);
		passDataToView.addObject("character", c);
		passDataToView.addObject("allUsers", usersList);
		passDataToView.setViewName(view);
		return passDataToView;
	}
	@PostMapping("/register")
	public ModelAndView registerUser(@RequestParam("username") String username,@RequestParam("pass") String pass,@RequestParam("buttonPressed") String buttonPressed) {
		ModelAndView modelAndView = new ModelAndView();
		if(usernameIsValid(username) && buttonPressed.equals("confirm")) {
			registerNewUser(new Users(username,pass,false));
			modelAndView.setViewName("index");
		}else {
			modelAndView.setViewName("signUp");
		}
		if(buttonPressed.equals("cancel")) {
			modelAndView.setViewName("index");
		}
		return modelAndView;
	}
	public boolean usernameIsValid(String username) {
		boolean isValid = true;
		if(username.equals("") || username.equals("")) {
			isValid = false;
		}
		return isValid;
	}
	@GetMapping("/createCharacter")
	public ModelAndView createCharacter(@RequestParam("characterName") String characterName,@RequestParam("classType") String classType,@RequestParam("username") String username) {
		ModelAndView modelAndView = new ModelAndView();
		if(characterAlreadyExists(characterName)) {
			//Name already taken. User has to choose another
			modelAndView = passEntitiesToViewAndSetView(username,"createCharacter");
		}else {
			createNewCharacter(setCharacterAttributes(characterName,classType,username));
			modelAndView = manageWebPages("home",username,characterName);
		}
		return modelAndView;
	}
	public Characters setCharacterAttributes(String characterName,String classType,String username) {
		HashMap<String,Integer> characterStats = new HashMap<>();
		String characterAspect = "ImageHere";
		switch(classType) {
		case "berserker":
			characterStats = setCharacterStats(20,15,15,5);
			characterAspect = "https://i.imgur.com/pbwUTSW.png";
			break;
		case "balanced":
			characterStats = setCharacterStats(15,10,10,10);
			characterAspect = "https://i.imgur.com/8noIjw3.jpg";
			break;
		case "spiritual":
			characterStats = setCharacterStats(10,5,5,20);
			characterAspect = "https://i.imgur.com/S83m0qD.png";
			break;
		}
		return new Characters(characterName,classType,0,username,characterAspect,characterStats.get("hp"),characterStats.get("atk"),characterStats.get("def"),characterStats.get("reiatsu"),1);
	}
	public HashMap<String,Integer> setCharacterStats(int hp, int atk,int def,int reiatsu) {
		HashMap<String,Integer> characterStats = new HashMap<>();
		characterStats.put("hp", hp);
		characterStats.put("atk", atk);
		characterStats.put("def", def);
		characterStats.put("reiatsu", reiatsu);
		return characterStats;
	}
	
	@GetMapping("/preparingHollowHunt")
	public ModelAndView preparingHollowHunt(@RequestParam("hollowName") String hollowName,@RequestParam("username") String username,@RequestParam("characterName") String characterName) {
		ModelAndView modelAndView = manageWebPages("preparingHollowHunt",username,characterName);
		modelAndView.addObject("hollow", getHollow(hollowName));
		return modelAndView;
	}
	
	@GetMapping("/hunt")
	public ModelAndView hunt(@RequestParam("buttonPressed") String buttonPressed,@RequestParam("username") String username,@RequestParam("characterName") String characterName,@RequestParam("hollowName") String hollowName) {
		ModelAndView modelAndView = new ModelAndView();
		if(buttonPressed.equals("confirm")) {
			//Set things up for the combat
			CharacterFighting cf = castCharacterToCharacterFighting(getCharacterByName(characterName));
			HollowFighting hf = castHollowToHollowFighting(getHollow(hollowName));
			createNewCharacterFighting(cf);
			createNewHollowFighting(hf);
			modelAndView = passEntitiesToViewAndSetView(getUser(username),cf,hf,"huntingHollow");
		}else {
			modelAndView = manageWebPages("huntHollows",username,characterName);
		}
		return modelAndView;
	}
	public CharacterFighting castCharacterToCharacterFighting(Characters c) {
		CharacterFighting cf = new CharacterFighting();
		cf.setCharacterName(c.getCharacterName());
		cf.setClassType(c.getClassType());
		cf.setAspect(c.getAspect());
		cf.setAtk(c.getAtk());
		cf.setDef(c.getDef());
		cf.setHp(c.getHp());
		cf.setReiatsu(c.getReiatsu());
		cf.setLvl(c.getLvl());
		cf.setXp(c.getXp());
		cf.setUsername(c.getUsername());
		cf.setPhysicalSkills(c.getPhysicalSkills());
		cf.setKidoSkills(c.getKidoSkills());
		cf.setBuffs(c.getBuffs());
		return cf;
	}
	public HollowFighting castHollowToHollowFighting(Hollows h) {
		HollowFighting hf = new HollowFighting();
		hf.setHollowName(h.getHollowName());
		hf.setAspect(h.getAspect());
		hf.setAtk(h.getAtk());
		hf.setDef(h.getDef());
		hf.setHp(h.getHp());
		hf.setLvl(h.getLvl());
		hf.setReiatsu(h.getReiatsu());
		hf.setHollowDescription(h.getHollowDescription());
		return hf;
	}
	@GetMapping("/manageCombatAgainstHollow")
	public ModelAndView manageCombatAgainstHollow(@RequestParam("username") String username,@RequestParam("characterName") String characterName,@RequestParam("hollowName") String hollowName,@RequestParam("attackAction") String attackAction,@RequestParam(value="buffAction",required=false) String buffAction) {
		Users user = getUser(username);
		CharacterFighting characterFighting = getCharacterFighting(characterName);
		HollowFighting hollowFighting = getHollowFighting(hollowName);
		manageCharacterBuffs(buffAction,characterFighting);
		manageCharacterAndHollowDamage(hollowFighting,characterFighting,attackAction);
		return checkIfCharacterOrHollowAreDead(hollowFighting,characterFighting,user);
	}
	public void manageCharacterBuffs(String buffAction,CharacterFighting character) {
		if(buffAction != null) {
			character.determineCharacterStatsAfterBuffing(buffAction);			
		}
	}
	public void manageCharacterAndHollowDamage(HollowFighting hollow,CharacterFighting character,String attackAction) {
		hollow.reduceHp(character.determineCharacterDamage(attackAction));
		character.reduceHp(hollow.getAtk());
	}
	public ModelAndView checkIfCharacterOrHollowAreDead(HollowFighting hollow,CharacterFighting character,Users user) {
		ModelAndView modelAndView = new ModelAndView();
		if(hollow.isDead()) {
			//TODO: Increment character's xp and check if levels up. If he levels up then increment overall stats
			//TODO: Also, balance Hollow's and character's dmg formula :]
			deleteFightingEntities(hollow.getHollowName(),character.getCharacterName());
			modelAndView = passEntitiesToViewAndSetView(user,getCharacterByName(character.getCharacterName()),getAllHollows(),"huntHollows");
		}else if(character.isDead()) {
			deleteFightingEntities(hollow.getHollowName(),character.getCharacterName());
			modelAndView = passEntitiesToViewAndSetView(user,getCharacterByName(character.getCharacterName()),"home");
		}else {
			updateHollowFighting(hollow);
			updateCharacterFighting(character);
			modelAndView = passEntitiesToViewAndSetView(user,character,hollow,"huntingHollow");
		}
		return modelAndView;
	}
	public void deleteFightingEntities(String hollowName,String characterName) {
		deleteHollowFighting(hollowName);
		deleteCharacterFighting(characterName);		
	}
	//Service Methods - UserEntity
	public Users getUser(String username) {
		return uService.getUser(username);
	}
	public boolean userIsRegistered(String username,String pass) {
		return uService.verifyIfUserExists(username, pass);
	}
	public void registerNewUser(Users user) {
		uService.registerNewUser(user);
	}
	public List<Users> getAllUsers(){
		return uService.getAllUsers();
	}
	//Service Methods - CharacterEntity
	public Characters getCharacterByUsername(String username) {
		return cService.getCharacterByUsername(username);
	}
	public Characters getCharacterByName(String name) {
		return cService.getCharacterByName(name);
	}
	public List<Characters> getAllCharacters(){
		return cService.getAllCharacters();
	}
	public boolean userHasACharacter(String username) {
		return cService.verifyIfUserHasCharacter(username);
	}
	public boolean characterAlreadyExists(String characterName) {
		return cService.verifyIfCharacterExists(characterName);
	}
	public void createNewCharacter(Characters cE) {
		cService.createNewCharacter(cE);
	}
	//Servicie Methods - CharacterFighting
	public void createNewCharacterFighting(CharacterFighting c) {
		cfService.createNewCharacterFighting(c);
	}
	public void updateCharacterFighting(CharacterFighting c) {
		createNewCharacterFighting(c);
	}
	public CharacterFighting getCharacterFighting(String characterName) {
		return cfService.getCharacterFighting(characterName);
	}
	public void deleteCharacterFighting(String characterName) {
		cfService.deleteCharacterFighting(characterName);
	}
	//Service Methods - Hollow
	public Hollows getHollow(String hollowName) {
		return hService.getHollow(hollowName);
	}
	public List<Hollows> getAllHollows(){
		return hService.getAllHollows();
	}
	//Service Methods - HollowFighting
	public void createNewHollowFighting(HollowFighting hollow) {
		hfService.createNewHollowFighting(hollow);
	}
	public void updateHollowFighting(HollowFighting hollow) {
		createNewHollowFighting(hollow);
	}
	public void deleteHollowFighting(String hollowName) {
		hfService.deleteHollowFighting(hollowName);
	}
	public HollowFighting getHollowFighting(String hollowName) {
		return hfService.getHollowFighting(hollowName);
	}
}
