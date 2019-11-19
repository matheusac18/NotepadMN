package operacao;

import modelo.Usuario;


public class Login {
  
  private static Usuario logged;

  public static boolean authenticate(String user, String password) {
	 
    for(Usuario u : Cadastro.getCadastrados()) {
      if(u.getEmail().equals(user)) {
    	  
        if(PasswordUtil.verifyPassword(password, u.getHash(), u.getSalt())) {
        	
          setLogged(u);
          return true;
        } else {
          return false;
        }
      }
    }
    return false;
  }

public static Usuario getLogged() {
	return logged;
}

public static void setLogged(Usuario logged) {
	Login.logged = logged;
}

}
