package bdbt_projekt.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private PositionDAO positionDAO;
    @Autowired
    private OfficeDAO officeDAO;


    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/main").setViewName("main");
        registry.addViewController("/login").setViewName("login");

        registry.addViewController("/main_admin").setViewName("admin/main_admin");
        registry.addViewController("/main_user").setViewName("user/main_user");

        registry.addViewController("/languages").setViewName("languages");
        registry.addViewController("/data").setViewName("languages");
        registry.addViewController("/employees").setViewName("employees/employees");
        registry.addViewController("/nasz-zespol").setViewName("our-team");
        registry.addViewController("/errors/record-connected").setViewName("/errors/record-connected");
    }

    @Controller
    public class DashboardController {

        @RequestMapping("/languages")
        public String showLanguagesPage(Model model) {
            List<Language> languageList = dao.list();
            model.addAttribute("languageList", languageList);

            return "languages";
        }

        // Handling employees
        @RequestMapping("/employees")
        public String showEmployeesPage(HttpServletRequest request, @AuthenticationPrincipal User user, Model model) {
            if (request.isUserInRole("USER")) {
                return "redirect:/employee/edit/" + user.getUsername();
            }
            else if (request.isUserInRole("ADMIN")) {
                List<Employee> employeeList = daoE.list();
                model.addAttribute("employeeList", employeeList);
                return "employees/employees";
            }
            else{
                return "index";
            }

        }

        @RequestMapping("/employee/new")
        public String showNewEmployeeForm(Model model) {
            Employee newEmployee = new Employee();
            List<Position> positionList = positionDAO.list();
            List<Office> officeList = officeDAO.list();

            model.addAttribute("newEmployee", newEmployee);
            model.addAttribute("positionList", positionList);
            model.addAttribute("officeList", officeList);

            return "employees/new-employee-form";
        }

        @RequestMapping(value = "/employee/save", method = RequestMethod.POST)
        public String saveEmployee(@ModelAttribute("newEmployee") Employee newEmployee) {
            daoE.save(newEmployee);

            return "redirect:/employees";
        }

        @RequestMapping(value = "/employee/edit/{id}")
        public ModelAndView showEditEmployeeForm(@PathVariable(name = "id") int id) {
            ModelAndView mav = new ModelAndView("employees/edit-employee-form");
            List<Position> positionList = positionDAO.list();
            List<Office> officeList = officeDAO.list();
            Employee newEmployee = daoE.get(id);

            mav.addObject("positionList", positionList);
            mav.addObject("officeList", officeList);
            mav.addObject("employee", newEmployee);

            return mav;
        }

        @RequestMapping(value = "/employee/update", method = RequestMethod.POST)
        public String updateEmployee(@ModelAttribute("newEmployee") Employee newEmployee) {
            daoE.update(newEmployee);

            return "redirect:/employees";
        }

        @RequestMapping(value = "/employee/delete/{id}")
        public String deleteEmployee(@PathVariable(name = "id") int id) {
            daoE.delete(id);

            return "redirect:/employees";
        }

        // Handling positions
        @RequestMapping("/positions")
        public String showPositionsPage(Model model) {
            List<Position> positionList = positionDAO.list();
            model.addAttribute("positionList", positionList);

            return "positions/positions";
        }

        @RequestMapping("/position/new")
        public String showNewPositionForm(Model model) {
            Position newPosition = new Position();

            model.addAttribute("newPosition", newPosition);

            return "positions/new-position-form";
        }

        @RequestMapping(value = "/position/save", method = RequestMethod.POST)
        public String savePosition(@ModelAttribute("newPosition") Position newPosition) {
            positionDAO.save(newPosition);

            return "redirect:/positions";
        }

        @RequestMapping(value = "/position/edit/{id}")
        public ModelAndView showEditPositionForm(@PathVariable(name = "id") int id) {
            ModelAndView mav = new ModelAndView("positions/edit-position-form");
            Position newPosition = positionDAO.get(id);

            mav.addObject("position", newPosition);

            return mav;
        }

        @RequestMapping(value = "/position/update", method = RequestMethod.POST)
        public String updatePosition(@ModelAttribute("newPosition") Position newPosition) {
            positionDAO.update(newPosition);

            return "redirect:/positions";
        }

        @RequestMapping(value = "/position/delete/{id}")
        public String deletePosition(@PathVariable(name = "id") int id) {
            positionDAO.delete(id);
            return "redirect:/positions";
        }

        @RequestMapping("/estate-agent/{agentID}")
        public ModelAndView showAgentsPage(@PathVariable(value = "agentID") int id) {
            ModelAndView agentsPage = new ModelAndView("agents_page");
            if (!daoE.checkIfRealEstateAgent(id))
                throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
            Employee employee = daoE.get(id);

            agentsPage.addObject("employee", employee);
            return agentsPage;
        }

        @RequestMapping("/estate-agent/name/{agentName}")
        public ModelAndView showAgentsPage(@PathVariable(value = "agentName") String agentName) {
            ModelAndView agentsPage = new ModelAndView("agents_page");
            if (!daoE.checkIfRealEstateAgent(agentName))
                throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
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
            } else if
            (request.isUserInRole
                            ("USER")) {
                return "redirect:/main_user";
            } else {
                return "redirect:/index";
            }
        }

        @RequestMapping("/nasz-zespol")
        public String showOurTeamPage(Model model) {
            List<Employee> employeeList = daoE.listAgents();
            Map<Integer, List> employeesLanguages = new HashMap<Integer, List>();
            for (Employee employee : employeeList) {
                List<Language> employeeLanguages = daoE.getEmployeesLanguages(employee.getNr_pracownika());
                employeesLanguages.put(employee.getNr_pracownika(), employeeLanguages);
            }
            model.addAttribute("employeeAgentsList", employeeList);
            model.addAttribute("employeeLanguagesList", employeesLanguages);

            return "our-team";
        }
    }

    @RequestMapping(value = {"/main_admin"})
    public String showAdminPage(Model model) {
        return "admin/main_admin";
    }

    @RequestMapping(value = {"/main_user"})
    public String showUserPage(Model model) {
        return "user/main_user";
    }

}