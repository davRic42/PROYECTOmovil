package co.edu.gestion_inventarios.api;

import java.util.ArrayList;
import java.util.List;

import co.edu.gestion_inventarios.model.Categoria;
import co.edu.gestion_inventarios.model.ResultsC;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceCategory {
    @GET("categorys")
    Call<ArrayList<Categoria>> getCategoria();

    @POST("categorys")
    Call <Categoria> createCategory(@Body Categoria createCategory);

}
