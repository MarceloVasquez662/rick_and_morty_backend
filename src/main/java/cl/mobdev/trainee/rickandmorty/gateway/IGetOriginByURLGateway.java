package cl.mobdev.trainee.rickandmorty.gateway;

import cl.mobdev.trainee.rickandmorty.model.Origin;

public interface IGetOriginByURLGateway {
    Origin execute(String uri);
}
