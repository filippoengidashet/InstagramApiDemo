package org.dalol.instagramapidemo.model.feed_response;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 2/21/2016
 */
public class Attribution {

    private String itunes_url;

    private String website;

    private String name;

    public String getItunes_url ()
    {
        return itunes_url;
    }

    public void setItunes_url (String itunes_url)
    {
        this.itunes_url = itunes_url;
    }

    public String getWebsite ()
    {
        return website;
    }

    public void setWebsite (String website)
    {
        this.website = website;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [itunes_url = "+itunes_url+", website = "+website+", name = "+name+"]";
    }
}
