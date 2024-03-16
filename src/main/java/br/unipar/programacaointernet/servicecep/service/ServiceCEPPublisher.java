package br.unipar.programacaointernet.servicecep.service;

import jakarta.xml.ws.Endpoint;

public class ServiceCEPPublisher {

    public static void main(String[] args) {

        Endpoint.publish("http://localhost:8080/usuario",
                new UsuarioSIB());

        Endpoint.publish("http://localhost:8080/endereco",
                new EnderecoSIB());

        System.out.println("Endpoints publicado com sucesso!");
    }
}
