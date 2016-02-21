package org.dalol.instagramapidemo.model.callback;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 2/20/2016
 */
public interface AuthenticationListener {

    void onCodeReceived(String code);
}
