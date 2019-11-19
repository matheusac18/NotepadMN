package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {
  public static void main(String[] args) {
    LoginDialog loginDlg = new LoginDialog(null);
    loginDlg.setVisible(true);
    // if logon successfully
    if (loginDlg.isSucceeded()) {
    }
  }
}
