package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.step.Step;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;


@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = 371852467185246852L;
    public static final String LOG_LABEL = "log";

    @JsonProperty(LOG_LABEL)
    private LogStep log;

    public LogFlowStep() {
        //Needed for serialization
    }

    @JsonCreator
    public LogFlowStep(final @JsonProperty(value = LOG_LABEL, required = true) LogStep log) {
        super();
        this.setLog(log);
    }

    public LogFlowStep(Step step) {
        setLog(new LogStep(step));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> res = new HashMap<>();
        res.put(LOG_LABEL, log.getRepresenterProperties());
        return res;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return getLog().getStep(catalog, LOG_LABEL,  kameletStepParserService);
    }

    public LogStep getLog() {
        return log;
    }

    public void setLog(final LogStep log) {
        this.log = log;
    }
}
