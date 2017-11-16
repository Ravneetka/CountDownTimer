package exousiatech.newpracticeapp.Modals;

import java.io.Serializable;

/**
 * Created by admin1 on 31-01-2017.
 */

public class BlogResponseData implements Serializable {
    int id,type;
    String title,image,created_at,new_created,parmalink,question,views,ico_name,protocol_name,ico_description,category_name,ico_end;


    public String getIco_end() {
        return ico_end;
    }

    public void setIco_end(String ico_end) {
        this.ico_end = ico_end;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getNew_created() {
        return new_created;
    }

    public void setNew_created(String new_created) {
        this.new_created = new_created;
    }

    public String getParmalink() {
        return parmalink;
    }

    public void setParmalink(String parmalink) {
        this.parmalink = parmalink;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getIco_name() {
        return ico_name;
    }

    public void setIco_name(String ico_name) {
        this.ico_name = ico_name;
    }

    public String getProtocol_name() {
        return protocol_name;
    }

    public void setProtocol_name(String protocol_name) {
        this.protocol_name = protocol_name;
    }

    public String getIco_description() {
        return ico_description;
    }

    public void setIco_description(String ico_description) {
        this.ico_description = ico_description;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }
}
