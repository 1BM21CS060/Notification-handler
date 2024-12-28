package org.acme.Notification;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/notifications")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotificationResource {
    @Inject
    NotificationRepository repository;

    @POST
    public Response createNotification(Notification notification) {
        repository.addNotification(notification);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    public Notification getNotification(@PathParam("id") String id) {
        return repository.getNotification(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteNotification(@PathParam("id") String id) {
        repository.deleteNotification(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
