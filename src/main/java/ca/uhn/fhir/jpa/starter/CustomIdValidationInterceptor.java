package ca.uhn.fhir.jpa.starter;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.api.server.storage.ResourcePersistentId;
import ca.uhn.fhir.rest.server.exceptions.InvalidRequestException;
import ca.uhn.fhir.rest.server.interceptor.ServerOperationInterceptorAdapter;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.springframework.stereotype.Component;

@Component
public class CustomIdValidationInterceptor extends ServerOperationInterceptorAdapter {

    @Hook(Pointcut.STORAGE_PRESTORAGE_CREATE_RESOURCE)
    public void validateIdBeforeCreate(RequestDetails theRequestDetails, IBaseResource theResource, ResourcePersistentId theResourcePersistentId) {
        validateId(theRequestDetails, theResource);
    }

    @Hook(Pointcut.STORAGE_PRESTORAGE_UPDATE_RESOURCE)
    public void validateIdBeforeUpdate(RequestDetails theRequestDetails, IBaseResource theResource, ResourcePersistentId theResourcePersistentId) {
        validateId(theRequestDetails, theResource);
    }

    private void validateId(RequestDetails theRequestDetails, IBaseResource theResource) {
        String id = theResource.getIdElement().getIdPart();
        System.out.println("Validating ID: " + id); // Agrega este registro
        if (id.matches("\\d+")) {
            throw new InvalidRequestException("IDs cannot be fully numeric: " + id);
        }
    }
}
