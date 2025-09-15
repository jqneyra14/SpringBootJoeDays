package pe.joedayz.restapis.utils.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditLoggingAspect {

    @DeclareParents(value = "pe.joedayz.restapis.services.*", defaultImpl = AuditLoggableImpl.class)
    public static AuditLoggable auditLoggable;
}
