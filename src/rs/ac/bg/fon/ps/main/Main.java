/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.main;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmLogin;

/**
 *
 * @author Tamara
 */
public class Main {
    public static void main(String[] args) {
         MainCordinator.getInstance().openLoginForm();
    }
        
}
