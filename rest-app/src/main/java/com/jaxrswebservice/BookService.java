package com.jaxrswebservice;

import com.applicationdao.BookDAO; // class BookDAO
import com.applicationentities.Book; // class Book
import jakarta.ws.rs.GET; // @interface GET
import jakarta.ws.rs.POST; // @interface POST
import jakarta.ws.rs.PUT; // @interface PUT
import jakarta.ws.rs.DELETE; // @interface DELETE
import jakarta.ws.rs.Path; // @interface Path
import jakarta.ws.rs.PathParam; // @interface PathParam
import jakarta.ws.rs.Produces; // @interface Produces
import jakarta.ws.rs.Consumes; // @interface Consumes
import jakarta.ws.rs.core.MediaType; // class MediaType
import jakarta.ws.rs.core.Response; // abstract class Response

import java.util.List; // interface List

@Path("/bookservice")
public class BookService {

    @GET
    @Path("/getbooks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks() {

        List<Book> listOfBooks = BookDAO.getAllBooks();

        return Response.status(Response.Status.OK).entity(listOfBooks).build();

    }

   @GET
   @Path("/getbook/{param}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getBook(@PathParam("param") String bookId) {

        Book book = BookDAO.getBookById(bookId);

        if (book == null) {

            String jsonResponse = "{\"message\": \"A book with the given ID does not exist\"," +
                    "\"bookId\": \"" + bookId + "\"}";

            return Response.status(Response.Status.NOT_FOUND)
                    .entity(jsonResponse)
                    .build();
        }

        return Response.status(Response.Status.OK).entity(book).build();

    }

    @POST
    @Path("/addbook")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {

        String addMsg = BookDAO.addBook(book);

        if (addMsg.startsWith("Error")) {

            String jsonResponse = "{\"error\": \"The book could not be added.\"," +
                    "\"message\": \"" + addMsg + "\"}";

            return Response.status(Response.Status.CONFLICT)
                    .entity(jsonResponse)
                    .build();
        }

        return Response.status(Response.Status.CREATED).entity(book).build();
    }

    @PUT
    @Path("/updatebook")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book) {

        String updateMsg = BookDAO.updateBook(book);

        if (updateMsg.startsWith("Error")) {

            String jsonResponse = "{\"error\": \"The book could not be located.\"," +
                    "\"message\": \"" + updateMsg + "\"}";

            return Response.status(Response.Status.NOT_FOUND)
                    .entity(jsonResponse)
                    .build();
        }

        return Response.status(Response.Status.ACCEPTED).entity(book).build();
    }

    @DELETE
    @Path("/deletebook/{param}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response deleteBook(@PathParam("param") String bookId) {

        String deleteMsg = BookDAO.removeBook(bookId);

        if (deleteMsg.startsWith("Error")) {

            String jsonResponse = "{\"error\": \"The book could not be removed.\"," +
                    "\"message\": \"" + deleteMsg + "\"}";

            return Response.status(Response.Status.NOT_FOUND)
                    .entity(jsonResponse)
                    .build();
        }

        return Response.status(Response.Status.ACCEPTED)
                .entity("Book deleted. ID: " + bookId)
                .build();
    }
 }

