package bdbt_projekt.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Configuration
public class AppController implements WebMvcConfigurer {
    @Autowired
    private LanguageDAO dao;
    @Autowired
    private EmployeeDAO daoE;

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/main").setViewName("main");
        registry.addViewController("/login").setViewName("login");

        registry.addViewController("/main_admin").setViewName("admin/main_admin");
        registry.addViewController("/main_user").setViewName("user/main_user");

        registry.addViewController("/languages").setViewName("data");
        registry.addViewController("/data").setViewName("data");
        registry.addViewController("/employees").setViewName("employee");
    }
    @Controller
    public class DashboardController
    {
        @RequestMapping("/data")
        public String ViewHomePage(Model model){
            List<Language> languageList = dao.list();
            model.addAttribute("languageList", languageList);
            System.out.println(Arrays.toString(languageList.toArray()));
            return "data";
        }
        @RequestMapping("/languages")
        public String showLanguagesPage(Model model){
            List<Language> languageList = dao.list();
            model.addAttribute("languageList", languageList);

            return "data";
        }
        @RequestMapping("/employees")
        public String showEmployeesPage(Model model){
            List<Employee> employeeList = daoE.list();
            model.addAttribute("employeeList", employeeList);

            return "employee";
        }
        @RequestMapping
                ("/main"
                )
        public String defaultAfterLogin
                (HttpServletRequest request) {
            if
            (request.isUserInRole
                    ("ADMIN")) {
                return "redirect:/main_admin";
            }
            else if
            (request.isUserInRole
                            ("USER")) {
                return "redirect:/main_user";
            }
            else
            {
                return "redirect:/index";
            }
        }
    }
    @RequestMapping(value={"/main_admin"})
    public String showAdminPage(Model model) {
        return "admin/main_admin";
    }
    @RequestMapping(value={"/main_user"})
    public String showUserPage(Model model) {
        return "user/main_user";
    }

}