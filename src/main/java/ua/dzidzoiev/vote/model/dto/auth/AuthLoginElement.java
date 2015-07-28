package ua.dzidzoiev.vote.model.dto.auth;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement
@XmlType(propOrder = {"username", "password"})
public class AuthLoginElement implements Serializable {

    @NotNull
    @Size(min = 3, max = 16)
    private String username;

    @Size(max = 16)
    private String password;

    public AuthLoginElement(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthLoginElement() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}