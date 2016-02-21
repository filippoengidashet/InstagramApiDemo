package org.dalol.instagramapidemo.model.feed_response;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 2/21/2016
 */
public class Caption {

    private String id;

    private String text;

    private From from;

    private String created_time;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    public From getFrom ()
    {
        return from;
    }

    public void setFrom (From from)
    {
        this.from = from;
    }

    public String getCreated_time ()
    {
        return created_time;
    }

    public void setCreated_time (String created_time)
    {
        this.created_time = created_time;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", text = "+text+", from = "+from+", created_time = "+created_time+"]";
    }
}
