package by.isysoi.dao;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.List;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.11-b150120.1832
 * Generated source version: 2.2
 */
@WebService(name = "HorseDAO", targetNamespace = "http://dao.isysoi.by/")
@XmlSeeAlso({
        ObjectFactory.class
})
public interface HorseDAO {


    /**
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "insertHorse", targetNamespace = "http://dao.isysoi.by/", className = "by.isysoi.dao.InsertHorse")
    @ResponseWrapper(localName = "insertHorseResponse", targetNamespace = "http://dao.isysoi.by/", className = "by.isysoi.dao.InsertHorseResponse")
    @Action(input = "http://dao.isysoi.by/HorseDAO/insertHorseRequest", output = "http://dao.isysoi.by/HorseDAO/insertHorseResponse")
    void insertHorse(
            @WebParam(name = "arg0", targetNamespace = "")
                    Horse arg0);

    /**
     * @return returns java.util.List<by.isysoi.dao.Horse>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "readHorses", targetNamespace = "http://dao.isysoi.by/", className = "by.isysoi.dao.ReadHorses")
    @ResponseWrapper(localName = "readHorsesResponse", targetNamespace = "http://dao.isysoi.by/", className = "by.isysoi.dao.ReadHorsesResponse")
    @Action(input = "http://dao.isysoi.by/HorseDAO/readHorsesRequest", output = "http://dao.isysoi.by/HorseDAO/readHorsesResponse")
    List<Horse> readHorses();

    /**
     * @param arg0
     * @return returns java.util.List<by.isysoi.dao.Horse>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "readHorcesInRace", targetNamespace = "http://dao.isysoi.by/", className = "by.isysoi.dao.ReadHorcesInRace")
    @ResponseWrapper(localName = "readHorcesInRaceResponse", targetNamespace = "http://dao.isysoi.by/", className = "by.isysoi.dao.ReadHorcesInRaceResponse")
    @Action(input = "http://dao.isysoi.by/HorseDAO/readHorcesInRaceRequest", output = "http://dao.isysoi.by/HorseDAO/readHorcesInRaceResponse")
    List<Horse> readHorcesInRace(
            @WebParam(name = "arg0", targetNamespace = "")
                    int arg0);

    /**
     * @param arg0
     * @return returns by.isysoi.dao.Horse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "readHorseById", targetNamespace = "http://dao.isysoi.by/", className = "by.isysoi.dao.ReadHorseById")
    @ResponseWrapper(localName = "readHorseByIdResponse", targetNamespace = "http://dao.isysoi.by/", className = "by.isysoi.dao.ReadHorseByIdResponse")
    @Action(input = "http://dao.isysoi.by/HorseDAO/readHorseByIdRequest", output = "http://dao.isysoi.by/HorseDAO/readHorseByIdResponse")
    Horse readHorseById(
            @WebParam(name = "arg0", targetNamespace = "")
                    int arg0);

}
