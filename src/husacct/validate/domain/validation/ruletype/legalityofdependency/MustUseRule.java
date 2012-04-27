package husacct.validate.domain.validation.ruletype.legalityofdependency;

import husacct.analyse.AnalyseServiceStub;
import husacct.common.dto.DependencyDTO;
import husacct.common.dto.RuleDTO;
import husacct.validate.domain.check.CheckConformanceUtil;
import husacct.validate.domain.validation.Message;
import husacct.validate.domain.validation.Severity;
import husacct.validate.domain.validation.Violation;
import husacct.validate.domain.validation.ViolationType;
import husacct.validate.domain.validation.iternal_tranfer_objects.Mapping;
import husacct.validate.domain.validation.iternal_tranfer_objects.Mappings;
import husacct.validate.domain.validation.ruletype.RuleType;
import husacct.validate.domain.validation.ruletype.RuleTypes;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class MustUseRule extends RuleType{
	private final static EnumSet<RuleTypes> exceptionrules = EnumSet.of(RuleTypes.IS_ALLOWED, RuleTypes.IS_NOT_ALLOWED);

	public MustUseRule(String key, String category, List<ViolationType> violationtypes, Severity severity) {
		super(key, category, violationtypes, exceptionrules,severity);
	}

	@Override
	public List<Violation> check(RuleDTO appliedRule) {	
		List<Violation> violations = new ArrayList<Violation>();
		//TODO replace with real implementation
		AnalyseServiceStub analysestub = new AnalyseServiceStub();

		Mappings mappings = CheckConformanceUtil.filter(appliedRule);
		List<Mapping> physicalClasspathsFrom = mappings.getMappingFrom();
		List<Mapping> physicalClasspathsTo = mappings.getMappingTo();

		int totalCounter = 0, noDependencyCounter = 0;
		for(Mapping classPathFrom : physicalClasspathsFrom){			
			for(Mapping classPathTo : physicalClasspathsTo){
				DependencyDTO[] dependencies = analysestub.getDependencies(classPathFrom.getPhysicalPath(),classPathTo.getPhysicalPath());
				totalCounter++;
				if(dependencies.length == 0) noDependencyCounter++;			
			}
			if(noDependencyCounter == totalCounter){
				Message message = new Message(appliedRule);


				//Violation violation = createViolation(dependency, 1, this.key, logicalModules, false, message);
				//violations.add(violation);
			}
		}	
		if(noDependencyCounter != totalCounter)
			violations.clear();
		return violations;
	}
}