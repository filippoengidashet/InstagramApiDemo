package org.dalol.instagramapidemo.model.feed_response;

import org.dalol.instagramapidemo.model.feed_response.Caption;
import org.dalol.instagramapidemo.model.pojo.User;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 2/20/2016
 */
public class Data {

    private String[] tags;

    private Location location;

    private String link;

    private String user_has_liked;

    private Caption caption;

    private String type;

    private String id;

    private Likes likes;

    private Images images;

    private String created_time;

    private String[] users_in_photo;

    private User user;

    private Attribution attribution;

    private Comments comments;

    private String filter;

    public String[] getTags ()
    {
        return tags;
    }

    public void setTags (String[] tags)
    {
        this.tags = tags;
    }

    public Location getLocation ()
    {
        return location;
    }

    public void setLocation (Location location)
    {
        this.location = location;
    }

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public String getUser_has_liked ()
    {
        return user_has_liked;
    }

    public void setUser_has_liked (String user_has_liked)
    {
        this.user_has_liked = user_has_liked;
    }

    public Caption getCaption ()
    {
        return caption;
    }

    public void setCaption (Caption caption)
    {
        this.caption = caption;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Likes getLikes ()
    {
        return likes;
    }

    public void setLikes (Likes likes)
    {
        this.likes = likes;
    }

    public Images getImages ()
    {
        return images;
    }

    public void setImages (Images images)
    {
        this.images = images;
    }

    public String getCreated_time ()
    {
        return created_time;
    }

    public void setCreated_time (String created_time)
    {
        this.created_time = created_time;
    }

    public String[] getUsers_in_photo ()
    {
        return users_in_photo;
    }

    public void setUsers_in_photo (String[] users_in_photo)
    {
        this.users_in_photo = users_in_photo;
    }

    public User getUser ()
    {
        return user;
    }

    public void setUser (User user)
    {
        this.user = user;
    }

    public Attribution getAttribution ()
    {
        return attribution;
    }

    public void setAttribution (Attribution attribution)
    {
        this.attribution = attribution;
    }

    public Comments getComments ()
    {
        return comments;
    }

    public void setComments (Comments comments)
    {
        this.comments = comments;
    }

    public String getFilter ()
    {
        return filter;
    }

    public void setFilter (String filter)
    {
        this.filter = filter;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [tags = "+tags+", location = "+location+", link = "+link+", user_has_liked = "+user_has_liked+", caption = "+caption+", type = "+type+", id = "+id+", likes = "+likes+", images = "+images+", created_time = "+created_time+", users_in_photo = "+users_in_photo+", user = "+user+", attribution = "+attribution+", comments = "+comments+", filter = "+filter+"]";
    }
}
