package com.virtusa.nyl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;
import org.apache.kafka.common.serialization.Serializer;

public class RouteObjectSerializer implements Serializer{

    @Override
    public void close() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void configure(Map arg0, boolean arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public byte[] serialize(String arg0, Object obj) {
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();
            byte[] b = baos.toByteArray();
            return b;
        }catch(IOException e){
            return new byte[0];
        }
    }

}
