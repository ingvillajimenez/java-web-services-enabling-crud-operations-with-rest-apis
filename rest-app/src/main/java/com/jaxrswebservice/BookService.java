package com.jaxrswebservice;

import com.applicationdao.BookDAO; // class BookDAO
import com.applicationentities.Book; // class Book
import jakarta.ws.rs.GET; // @interface GET
import jakarta.ws.rs.Path; // @interface Path
import jakarta.ws.rs.Produces; // @interface Produces
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

 }

