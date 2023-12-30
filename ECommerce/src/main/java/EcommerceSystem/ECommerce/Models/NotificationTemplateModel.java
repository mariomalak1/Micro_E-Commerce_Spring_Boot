package EcommerceSystem.ECommerce.Models;

import javax.security.auth.Subject;
import java.util.ArrayList;

public abstract class NotificationTemplateModel {
    protected String subject;
    protected String content;

    protected ArrayList<String> langauges = new ArrayList<>();

    protected Order order;
    public NotificationTemplateModel(Order order , String content) {
        this.order = order;
        this.content = content;
        setPlaceHolders(order);
        setLangauges();
        setSubject();
    }

    public String getSubject() {
        return subject;
    }

    public abstract void setSubject() ;

    public String getContent() {
        return content;
    }

    public  void AddToContent(String Signutre){
        content+=" "+Signutre;
    }
    public ArrayList<String> getLangauges() {
        return langauges;
    }

    public abstract void setLangauges();
    public abstract void setPlaceHolders(Order order);
    public Order GetOrder(){
        return order;
    }
    public void SetOrder(Order order){
        this.order = order;
    }
    public abstract NotificationTemplateModel clone();
}
