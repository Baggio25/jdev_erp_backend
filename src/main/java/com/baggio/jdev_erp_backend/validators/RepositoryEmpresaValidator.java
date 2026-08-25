package com.baggio.jdev_erp_backend.validators;

import com.baggio.jdev_erp_backend.anotations.IgnoreEmpresaId;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.core.support.RepositoryFactoryInformation;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
public class RepositoryEmpresaValidator implements SmartInitializingSingleton {

    private final ApplicationContext applicationContext;

    public RepositoryEmpresaValidator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    private List<Class<?>> getRepositories() {
        List<Class<?>> repositories = new ArrayList<>();
        applicationContext.getBeansOfType(RepositoryFactoryInformation.class).values().forEach(
                repo -> repositories.add(repo.getRepositoryInformation().getRepositoryInterface()));

        return repositories;
    }

    @Override
    public void afterSingletonsInstantiated() {
        for( Class<?> interfaceRepository : getRepositories() ){
            if(interfaceRepository.isAnnotationPresent(IgnoreEmpresaId.class)){
                continue;
            }

            for(Method method : interfaceRepository.getMethods()){
                if(method.isAnnotationPresent(IgnoreEmpresaId.class)){
                    continue;
                }

                if(!method.getDeclaringClass().equals(interfaceRepository)){
                    continue;
                }

                boolean queryPresent = method.isAnnotationPresent(Query.class);
                if(!queryPresent){
                    throw new IllegalArgumentException("O método: " + method
                        + " da interface: " + interfaceRepository
                        + " deve possuir query escrita.");
                }

                Query query = method.getAnnotation(Query.class);
                String sql = query.value().toLowerCase();
                if(sql.contains("empresa.id") || sql.contains("empresa_id")){
                    continue;
                }

                throw new IllegalStateException("""
						====================================================================
						ERRO DE SEGURANÇA

						Repository.....: %s
						Método.........: %s

						A consulta abaixo NÃO possui filtro por empresa.

						%s

						Toda consulta deve possuir:

						empresa.id

						ou

						empresa_id

						Caso esta consulta realmente não necessite
						do filtro, utilize:

						@IgnoreEmpresaId

						Essa anotação pode ser usada para o Repository completo ou para métodos unicos.
						====================================================================

						""".formatted(interfaceRepository.getSimpleName(), method.getName(), query.value()));

            }
        }
    }
}
