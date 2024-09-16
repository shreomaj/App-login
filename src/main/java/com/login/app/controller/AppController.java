package com.login.app.controller;

import org.springframework.http.HttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.login.app.controller.dto.UserRegistrationDto;
import com.login.app.entity.Student;
import com.login.app.entity.User;
import com.login.app.service.StudentService;
import com.login.app.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

// Indicates that this class is a Spring MVC controller that will handle HTTP requests.
@Controller
//@RequestMapping("/registration")//Specifies that the controller will handle requests starting with /registration.
public class AppController {

	private UserService userService;
	private StudentService studentService;

    

	public AppController(UserService userService, StudentService studentService) {
		super();
		this.userService = userService;
		this.studentService = studentService;
	}

	@GetMapping("/registration")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new UserRegistrationDto());
		// Add a new UserRegistrationDto object to the model
		return "registration"; // Return the name of the Thymeleaf template
	}

	// registration view page , don't need to write .html, Spring MVC automatically
	// understand

	@RequestMapping(value="/registration",method=RequestMethod.POST)
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		userService.save(registrationDto);
		System.out.println("Save data");
		return "redirect:/registration?success";
	}

	@GetMapping("/login")
	public String showLoginPage(Model model) {
		return "login";
	}

	@RequestMapping(value="/login",method=RequestMethod.POST)
	    public String handleLogin(HttpServletRequest request) {
	    	System.out.println("login");
	    	String usrname=request.getParameter("email");
	    	String password=request.getParameter("password");
	        System.out.println("username : "+usrname);
	        User user = userService.findByEmail(usrname);
	        if (user != null && user.getPassword().equals(password)) {
	            return "redirect:/welcome";
	        } else {
	          //  model.addAttribute("error", "Invalid email or password.");
	            return "login";
	        }
	    }
//

	// Fetch list of students for the welcome page
    @GetMapping("/welcome")
    public String welcomePage(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "welcome";  // Return Thymeleaf template
    }
	
	@GetMapping("/students/new")
    public String showCreateStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "create-student";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.save(student);
        return "redirect:/welcome";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/students/edit/{id}")
    public String showEditStudentForm(@PathVariable Long id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "edit-student";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student) {
        Student existingStudent = studentService.findById(id);
        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        studentService.save(existingStudent);
        return "redirect:/welcome";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteById(id);
        return "redirect:/welcome";
    }
}



