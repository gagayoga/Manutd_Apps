/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Session;

/**
 *
 * @author Asus
 */
public class SessionLogin {
    private static String NameUser;
    private static String EmailUser;
    private static String PasswordUser;
    private static String TeleponUser;
    private static String RoleUser;

    public static String getNameUser() {
        return NameUser;
    }

    public static void setNameUser(String NameUser) {
        SessionLogin.NameUser = NameUser;
    }

    public static String getEmailUser() {
        return EmailUser;
    }

    public static void setEmailUser(String EmailUser) {
        SessionLogin.EmailUser = EmailUser;
    }

    public static String getPasswordUser() {
        return PasswordUser;
    }

    public static void setPasswordUser(String PasswordUser) {
        SessionLogin.PasswordUser = PasswordUser;
    }

    public static String getTeleponUser() {
        return TeleponUser;
    }

    public static void setTeleponUser(String TeleponUser) {
        SessionLogin.TeleponUser = TeleponUser;
    }

    public static String getRoleUser() {
        return RoleUser;
    }

    public static void setRoleUser(String RoleUser) {
        SessionLogin.RoleUser = RoleUser;
    }
}
