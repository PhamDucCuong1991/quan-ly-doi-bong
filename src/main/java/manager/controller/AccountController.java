package manager.controller;

import manager.model.account.Account;
import manager.model.account.AccountToken;
import manager.model.account.Role;
import manager.sevice.account_service.AccountService;
import manager.sevice.account_service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@PropertySource("classpath:application.properties")
@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class AccountController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AccountService accountService;

    @Value("${upload.path}")
    private String upload;

    @PostMapping("/login")
    public AccountToken login(@RequestBody Account account) {
        if (!accountService.findAllByStatus().contains(accountService.findAccountByUserName(account.getUsername()))) {
            return null;
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtService.createToken(authentication);
        Account account1 = accountService.findAccountByUserName(account.getUsername());
        AccountToken accountToken = new AccountToken(account1.getId(), account1.getUsername(), account1.getAvatar(), token, account1.getRoles());
        return accountToken;
    }

    @PostMapping("/upAvatar")
    public String upAvatar(@RequestParam(required = false) MultipartFile fileImg) {

        if (fileImg == null) {
            return "../images/users/avatar.png";
        }
        String nameImg = fileImg.getOriginalFilename();

        try {
            FileCopyUtils.copy(fileImg.getBytes(), new File(upload + nameImg));
            return "../images/users/" + nameImg;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/upDateAvatar/{id}")
    public String upDateAvatar(@RequestParam(required = false) MultipartFile fileImg, @PathVariable Long id) {

        if (fileImg == null) {
            return accountService.findAccountById(id).getAvatar();
        }
        String nameImg = fileImg.getOriginalFilename();

        try {
            FileCopyUtils.copy(fileImg.getBytes(), new File(upload + nameImg));
            return "../images/users/" + nameImg;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/register")
    public void register(@RequestBody Account account) {

        Role role = new Role();
        role.setId(4L);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        account.setRoles(roles);
        account.setStatus(true);
        accountService.save(account);
    }

    @GetMapping("/{id}")
    public Account findOne(@PathVariable Long id) {
        return accountService.findAccountById(id);
    }

    @GetMapping("/listUser")
    public List<Account> findAll() {
        return accountService.findAllByStatus();
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody Account account, @PathVariable Long id) {
        account.setStatus(true);
        accountService.save(account);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Account account = accountService.findAccountById(id);
        account.setStatus(false);
        accountService.save(account);
    }
}
