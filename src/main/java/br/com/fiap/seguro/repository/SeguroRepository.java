package br.com.fiap.seguro.repository;

import br.com.fiap.abstracoes.repository.Repository;
import br.com.fiap.seguro.model.Seguro;

import java.util.List;

public abstract class SeguroRepository extends Repository {

    public static Seguro findById(Long id) {
        return manager.find(Seguro.class, id);
    }

    public static List<?> findAll(){
        String jpql = "From Seguro";
        return  manager.createQuery(jpql).getResultList();
    }

    public SeguroRepository(){}

}
