package ca.uhn.fhir.jpa.starter;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.exceptions.InvalidRequestException;
import org.hl7.fhir.instance.model.api.IIdType;

@Interceptor
public class CustomIdValidationInterceptor {

    @Hook(Pointcut.STORAGE_PRESTORAGE_CREATE_RESOURCE)
    public void validateCreateId(RequestDetails theRequestDetails) {
        validateId(theRequestDetails);
    }

    @Hook(Pointcut.STORAGE_PRESTORAGE_UPDATE_RESOURCE)
    public void validateUpdateId(RequestDetails theRequestDetails) {
        validateId(theRequestDetails);
    }

    private void validateId(RequestDetails theRequestDetails) {
        IIdType resourceId = theRequestDetails.getId();
        if (resourceId != null) {
            String id = resourceId.getIdPart();
            if (id.matches("[0-9]+")) {
                throw new InvalidRequestException("Resource ID must contain at least one non-numeric character");
            }
        }
    }
}
