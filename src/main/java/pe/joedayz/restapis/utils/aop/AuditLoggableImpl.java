package pe.joedayz.restapis.utils.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.joedayz.restapis.domains.Todo;
import pe.joedayz.restapis.domains.TodoType;

public class AuditLoggableImpl implements AuditLoggable{

    private final Logger logger = LoggerFactory.getLogger(AuditLoggableImpl.class);

    @Override
    public void auditLog(Object o, String eventName) {
        String auditMsg = "";

        if(eventName.equals("INSERT")){
            auditMsg += "Inserting ";
        }else if (eventName.equals("UPDATE")) {
            auditMsg += "Updating ";
        }

        if(o instanceof Todo){
            auditMsg += "Todo";
        } else if (o instanceof TodoType){
            auditMsg += "TodoType";
        }
        logger.info(auditMsg);
    }
}
