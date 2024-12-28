package org.acme.Notification;
import jakarta.enterprise.context.ApplicationScoped;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;

@ApplicationScoped
public class NotificationRepository {
    private final DynamoDbTable<Notification> table;

    public NotificationRepository(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        TableSchema<Notification> tableSchema = TableSchema.fromBean(Notification.class);
        this.table = dynamoDbEnhancedClient.table("Notifications", tableSchema);
    }

    public void addNotification(Notification notification) {
        table.putItem(notification);
    }

    public Notification getNotification(String notificationId) {
        return table.getItem(r -> r.key(k -> k.partitionValue(notificationId)));
    }

    public void deleteNotification(String notificationId) {
        table.deleteItem(r -> r.key(k -> k.partitionValue(notificationId)));
    }
}
