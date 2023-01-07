package bdbt_projekt.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;

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

        registry.addViewController("/languages").setViewName("languages");
        registry.addViewController("/data").setViewName("languages");
        registry.addViewController("/employees").setViewName("employee");
        registry.addViewController("/nasz-zespol").setViewName("our-team");
    }
    @Controller
    public class DashboardController
    {

        @RequestMapping("/languages")
        public String showLanguagesPage(Model model){
            List<Language> languageList = dao.list();
            model.addAttribute("languageList", languageList);

            return "languages";
        }
        @RequestMapping("/employees")
        public String showEmployeesPage(Model model){
            List<Employee> employeeList = daoE.list();
            model.addAttribute("employeeList", employeeList);

            employeeList = daoE.listAgents();
            Map<Integer, List> employeesLanguages = new HashMap<Integer, List>();
            for (Employee employee: employeeList) {
                List<Language> employeeLanguages = daoE.getEmployeesLanguages(employee.getNr_pracownika());
                employeesLanguages.put(employee.getNr_pracownika(), employeeLanguages);
                //System.out.println(Arrays.toString(employeesLanguages.get(employee.getNr_pracownika()).toArray()));
            }
            model.addAttribute("employeeAgentsList", employeeList);
            model.addAttribute("employeeLanguagesList", employeesLanguages);

            return "employee";
        }
        @RequestMapping("/estate-agent/{agentID}")
        public ModelAndView showAgentsPage (@PathVariable(value="agentID") int id) {
            ModelAndView agentsPage = new ModelAndView("agents_page");
            if (!daoE.checkIfRealEstateAgent(id)) throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
            Employee employee = daoE.get(id);

            agentsPage.addObject("employee", employee);
            return agentsPage;
        }
        @RequestMapping("/estate-agent/name/{agentName}")
        public ModelAndView showAgentsPage (@PathVariable(value="agentName") String agentName) {
            ModelAndView agentsPage = new ModelAndView("agents_page");
            if (!daoE.checkIfRealEstateAgent(agentName)) throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
            Employee employee = daoE.get(agentName);

            agentsPage.addObject("employee", employee);
            return agentsPage;
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
        @RequestMapping("/nasz-zespol")
        public String showOurTeamPage(Model model){
            List<Employee> employeeList = daoE.listAgents();
            Map<Integer, List> employeesLanguages = new HashMap<Integer, List>();
            for (Employee employee: employeeList) {
                List<Language> employeeLanguages = daoE.getEmployeesLanguages(employee.getNr_pracownika());
                employeesLanguages.put(employee.getNr_pracownika(), employeeLanguages);
            }
            model.addAttribute("employeeAgentsList", employeeList);
            model.addAttribute("employeeLanguagesList", employeesLanguages);

            return "our-team";
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