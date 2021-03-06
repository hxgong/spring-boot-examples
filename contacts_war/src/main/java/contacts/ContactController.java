package contacts;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Controller
@RequestMapping("/")
@ConfigurationProperties(prefix="app")
public class ContactController {
  private ContactRepository contactRepo;

  private String title;
  
  @Autowired
  public ContactController(ContactRepository contactRepo) {
  	this.contactRepo = contactRepo;
  }

  @RequestMapping(method=RequestMethod.GET)
  public String home(Map<String,Object> model) {
    List<Contact> contacts = contactRepo.findAll();
    model.put("contacts", contacts);
    model.put("title", title);
    return "home";
  }

  @RequestMapping(method=RequestMethod.POST)
  public String submit(Contact contact) {
    contactRepo.save(contact);
    return "redirect:/";
  }	
 
  public void setTitle(String title) {
	  this.title = title;
  }
}