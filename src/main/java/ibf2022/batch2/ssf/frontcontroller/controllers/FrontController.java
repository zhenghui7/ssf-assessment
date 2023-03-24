package ibf2022.batch2.ssf.frontcontroller.controllers;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ibf2022.batch2.ssf.frontcontroller.model.User;
import ibf2022.batch2.ssf.frontcontroller.services.AuthenticationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class FrontController {

	// TODO: Task 2, Task 3, Task 4, Task 6

	@Autowired 
	private AuthenticationService authSvc;
	
	@GetMapping(path={"/", "/index.html"})
    public String showCart(Model model, HttpSession sess){
		User u = (User) sess.getAttribute("user");
		if(null == u){
            u = new User();
            sess.setAttribute("user", u);
        }

		model.addAttribute("user", u);
		return "view0";
	}

	@PostMapping(path="/login")
	public String postLogin(Model model , HttpSession sess, @Valid User user, BindingResult bindings) {

		User u = (User) sess.getAttribute("user");

			if(bindings.hasErrors())
				model.addAttribute("user", user);
            return "view0";

		// send requestentity to validate
		authSvc.authenticate(user.getUsername(), user.getPassword());

		//if authentication suceed
		return "view1";
		
	}

	@PostMapping(path="/login")
	public String postLogin(Model model , HttpSession sess, @Valid User user, BindingResult bindings) {	

		User u = (User) sess.getAttribute("user");

		//depending on the count, to display the captcha 
		String[] ops = {"+", "-", "*", "/"};
		Random random = new Random();

		int number1 = random.nextInt(50) +1;
		int number2 = random.nextInt(50) +1;
		String calculate = ops[random.nextInt(4)];
		float result = 0;

		switch (calculate) {
			case "+":
			  result = number1 + number2;
			  break;
			case "-":
			  result = number1 - number2;
			  break;
			case "*":
			  result = number1 * number2;
			  break;
			case "/":
			  result = (float) number1 / number2;
			  break;
		}
		
	}
	

	@GetMapping(path="/protected/view1")
	public String getLoginPage(Model model, HttpSession sess) {

		if (!hasOrder(sess)) {
			return "redirect:/";
		}

		sess.invalidate();

		return "view0";
	}

	//in view1.html to logout, invalidate session
	@PostMapping(path="/protected/view1")
	public String post(Model model, HttpSession sess) {

		if (!hasOrder(sess)) {
			return "redirect:/";
		}

		sess.invalidate();

		return "view0";
	}



	private boolean hasOrder(HttpSession sess) {
		return null != sess.getAttribute("user");
	}



}