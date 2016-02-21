package org.dalol.instagramapidemo.model.pojo;

import com.google.gson.annotations.Expose;

import org.dalol.instagramapidemo.model.feed_response.Data;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 2/20/2016
 */
public class FeedResponse {

    @Expose
    private Meta meta;

    @Expose
    private Pagination pagination;

    @Expose
    private Data data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
