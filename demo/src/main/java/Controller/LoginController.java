package Controller;

import Model.Application;
import View.LoginPanel;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private Application model;
    private LoginPanel view;
    private Runnable loginSuccessListener;

    public LoginController(Application model, LoginPanel view) {
        this.model = model;
        this.view = view;

        // Add action listeners to the buttons in the view
        this.view.addLoginListener(new LoginListener());
        this.view.addRegisterListener(new RegisterListener());
        
    }

    public void setLoginSuccessListener(Runnable loginSuccessListener) {
        this.loginSuccessListener = loginSuccessListener;
    }
    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername();
            String password = view.getPassword();
            if (model.login(username, password) != null) {
                JOptionPane.showMessageDialog(view, "Login Successful");
                // Transition to the next page here
                if (loginSuccessListener != null) {
                    loginSuccessListener.run();
            } else {
                JOptionPane.showMessageDialog(view, "Invalid username or password");
            }
        }
    }
}
    class RegisterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername();
            String password = view.getPassword();
            boolean isAdmin = false; // Or based on additional UI input
            if (model.register(username, password, isAdmin)) {
                JOptionPane.showMessageDialog(view, "Registration Successful");
            } else {
                JOptionPane.showMessageDialog(view, "Username already exists. Please enter another username.");
            }
        }
    }


}