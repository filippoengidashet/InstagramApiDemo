package org.dalol.instagramapidemo.model;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 2/21/2016
 */
public class Authenticate {

    private String client_id, redirect_uri, response_type, display, scope;

    private Authenticate(Builder builder) {
        this.client_id = builder.client_id;
        this.redirect_uri = builder.redirect_uri;
        this.response_type = builder.response_type;
        this.display = builder.display;
        this.scope = builder.scope;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public String getResponse_type() {
        return response_type;
    }

    public String getDisplay() {
        return display;
    }

    public String getScope() {
        return scope;
    }

    public String urlLify() {
        StringBuffer buffer = new StringBuffer(Constants.API_URL);
        buffer.append("?client_id=" + getClient_id())
                .append("&redirect_uri=" + getRedirect_uri())
                .append("&response_type=" + getResponse_type())
                .append("&display=" + getDisplay())
                .append("&scope=" + getScope());

        return buffer.toString();
    }

    public static class Builder {
        private String client_id, redirect_uri, response_type, display, scope;

        public Builder setClient_id(String client_id) {
            this.client_id = client_id;
            return Builder.this;
        }

        public Builder setRedirect_uri(String redirect_uri) {
            this.redirect_uri = redirect_uri;
            return Builder.this;
        }

        public Builder setResponse_type(String response_type) {
            this.response_type = response_type;
            return Builder.this;
        }

        public Builder setDisplay(String display) {
            this.display = display;
            return Builder.this;
        }

        public Builder setScope(String scope) {
            this.scope = scope;
            return Builder.this;
        }

        public Authenticate build() {
            return new Authenticate(Builder.this);
        }
    }
}
