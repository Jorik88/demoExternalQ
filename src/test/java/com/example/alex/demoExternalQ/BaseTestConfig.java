package com.example.alex.demoExternalQ;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class BaseTestConfig {

    public <T> void writeToFile(T someRequest, Class clazz, String filePath) {
        try {

            File file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(someRequest, file);
            jaxbMarshaller.marshal(someRequest, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
