package View;

import Model.User;

public interface LoginListener {
    void onLogin(User user);
    void onRegister(boolean success);
}