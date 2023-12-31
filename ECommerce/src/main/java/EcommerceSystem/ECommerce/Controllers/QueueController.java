package EcommerceSystem.ECommerce.Controllers;

import EcommerceSystem.ECommerce.Models.NotificationTemplateModel;
import EcommerceSystem.ECommerce.Services.QueueServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Queue;
@RestController
@RequestMapping("/api/queue")
public class QueueController {
    @Autowired
    private QueueServices queueServices = new QueueServices();
    @GetMapping("/GetNotifications/")
    public Queue<NotificationTemplateModel> displayNotifications(){
        return queueServices.displayNotification();
    }
}
