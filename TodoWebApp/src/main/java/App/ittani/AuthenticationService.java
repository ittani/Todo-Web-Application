package App.ittani;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {


    public boolean autheticate(String username, String pass)
    {
        boolean isValidUsername= username.equalsIgnoreCase("ittani");
        boolean isValidPass = pass.equalsIgnoreCase("9711");

        return isValidUsername && isValidPass;
    }

}
