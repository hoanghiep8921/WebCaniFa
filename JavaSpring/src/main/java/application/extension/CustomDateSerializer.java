package application.extension;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class CustomDateSerializer extends JsonSerializer<Date> {
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializerProvider)
        throws IOException,JsonProcessingException{
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            if(value==null){
                gen.writeNull();
            }else {
                gen.writeString(format.format(value));
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
