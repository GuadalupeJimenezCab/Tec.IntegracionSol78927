package com.example.demo;

import org.example.calculadora.SumarRequest;
import org.example.calculadora.SumarResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CalculadoraEndPoint {
    
    @PayloadRoot(namespace = "http://www.example.org/calculadora", localPart = "SumarRequest")

    @ResponsePayload
    public SumarResponse dameSuma(@RequestPayload SumarRequest peticion){

        SumarResponse respuesta= new SumarResponse();
        respuesta.setResultado( String.valueOf(
            Integer.parseInt(peticion.getA()) +
            Integer.parseInt(peticion.getB())
            )   
        );
        return respuesta;
    }
}
