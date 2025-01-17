package paquete.conversor.conversion;


import com.google.gson.annotations.SerializedName;

//consderar usar el campo success si sera necesario que se asigfnen a un a lista solo si fue exitoso
public record MonedaRecord(
        @SerializedName("base_code") String base_code,
        @SerializedName("target_code") String target_code,
        @SerializedName("conversion_rate") Double conversion_rate

) {

}
