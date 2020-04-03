import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.airtel.airtelwebsite.core.service.MathService;

@Component(service = WorkflowProcess.class,
property = {"process.label=Airtel Custom Process step"})
public class CustomLogProcessStep implements WorkflowProcess {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Reference
	MathService mathService;
	
	@Override
	public void execute(WorkItem item, WorkflowSession session, MetaDataMap args) throws WorkflowException {
		logger.warn("Calculate Value from MathService value ==== "+ mathService.calculateValue());
	}

}
