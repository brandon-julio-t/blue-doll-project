package edu.binus.bluedollproject.facades;

import android.content.Context;

import edu.binus.bluedollproject.models.User;
import edu.binus.bluedollproject.repositories.UserRepository;

public class Auth {
    private static User user = null;
    private final Context context;

    public Auth(Context context) {
        this.context = context;
    }

    public User getUser() {
        return user;
    }

    public boolean login(String email, String password) {
        User admin = adminLogin(email, password);
        if (admin != null) {
            Auth.user = admin;
            return true;
        }

        User user = new UserRepository(context).getOneByEmail(email);
        boolean isAuth = user.getPassword().equals(password);
        if (isAuth) Auth.user = user;
        return isAuth;
    }

    private User adminLogin(String email, String password) {
        if (email.equals("admin@email.com") && password.equals("admin"))
            return new User(
                    0,
                    "Administrator",
                    "admin@email.com",
                    "admin",
                    "-",
                    "admin"
            );
        return null;
    }

    public void logout() {
        user = null;
    }
}
