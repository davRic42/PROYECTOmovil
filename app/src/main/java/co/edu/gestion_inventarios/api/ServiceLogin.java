package co.edu.gestion_inventarios.api;


import java.util.List;

import co.edu.gestion_inventarios.model.Loger;
import co.edu.gestion_inventarios.model.ResponseCredentials;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.Call;

public interface ServiceLogin {
    /*@POST("registro")
    Call<String> registrarUsuario(@Body Loger loger);*/
    @POST("login")
    Call<ResponseCredentials> accessLogin(@Body Loger login);
    @POST("users")
    Call<ResponseCredentials> createUser(@Body Loger createUser);
    @GET("users")
    Call<List<ResponseCredentials>> getUsers();
    @PATCH("users")
    Call<ResponseCredentials> updateUsers(@Body Loger updateUsers);
}