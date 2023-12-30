package EcommerceSystem.ECommerce.Services;

import EcommerceSystem.ECommerce.Models.NotificationTemplateModel;
import EcommerceSystem.ECommerce.Models.Order;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Queue;
@Service
public class QueueServices {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ProductInMemoryServices ProductService = new ProductInMemoryServices();
    private CustomerInMemoryServices CustService = new CustomerInMemoryServices();
    public Queue<NotificationTemplateModel> displayNotification(){
        return DataBaseInMemory.queue;
    }
    public QueueServices(){
        scheduler.scheduleAtFixedRate(this::retrieveAndRemoveLastElement, 0, 3, TimeUnit.MINUTES);
    }
    private void retrieveAndRemoveLastElement() {
        // Check if the queue is not empty
        if (!DataBaseInMemory.queue.isEmpty()) {
            int initialOrderID = DataBaseInMemory.queue.peek().GetOrder().getOrderID();
            while (!DataBaseInMemory.queue.isEmpty() &&
                    DataBaseInMemory.queue.peek().GetOrder().getOrderID() == initialOrderID) {
                NotificationTemplateModel lastNotification = DataBaseInMemory.queue.poll();
                lastNotification.GetOrder().Ship();
                System.out.println(lastNotification.getContent());
            }
        }
    }

    public void DelMessages(Order orderToRemove) {
        Iterator<NotificationTemplateModel> iterator = DataBaseInMemory.queue.iterator();

        while (iterator.hasNext()) {
            NotificationTemplateModel notification = iterator.next();
            if (notification.GetOrder().getOrderID() == orderToRemove.getOrderID()) {
                iterator.remove();
            }
        }
    }
    public void addNotification(NotificationTemplateModel notification){
        DataBaseInMemory.queue.add(notification);
    }
}
