package org.dalol.instagramapidemo.model.pojo;

import java.io.Serializable;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 2/20/2016
 */
public class TokenResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String access_token;
    private User user;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return this.access_token;
    }
}
