package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
public class UserController {
     private final ShoppingCartService shoppingCartService;
     private final UserService userService;

    public UserController(ShoppingCartService shoppingCartService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }
    @GetMapping("/user")
    public String getUserPage(@RequestParam(required = false) String error, Model model) {

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        return "login";
    }
    @PostMapping("/user")
    public String postUserOage(HttpServletRequest request, Model model){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = this.userService.findByUsername(username).get();
        request.getSession().setAttribute("user", user);
        return "redirect:/balloon";
    }

    @PostMapping("user/active")
    public String creteShoppingcart(HttpServletRequest req){



        //req.getSession().setAttribute("cartId", cartId);
        //req.getSession().setAttribute("username", username);

        return "login";
    }
    @PostMapping("/logout")
    public String logoutUser(HttpServletRequest req){
        req.getSession().invalidate();
        return "login";
    }
    @GetMapping("/access_denied")
    public String getAccessDeniedPage() {

        return "access_denied";
    }
}
