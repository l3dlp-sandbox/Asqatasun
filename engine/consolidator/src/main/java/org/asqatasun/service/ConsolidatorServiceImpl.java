/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.asqatasun.consolidator.Consolidator;
import org.asqatasun.consolidator.ConsolidatorFactory;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.reference.Test;
import org.asqatasun.entity.service.audit.EvidenceDataService;
import org.asqatasun.entity.service.audit.EvidenceElementDataService;
import org.asqatasun.entity.service.audit.ProcessRemarkDataService;
import org.asqatasun.processing.ProcessRemarkServiceFactory;
import org.asqatasun.ruleimplementation.RuleImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author jkowalczyk
 */
@Service("consolidatorService")
public class ConsolidatorServiceImpl implements ConsolidatorService {

    @Autowired
    protected RuleImplementationLoaderService ruleImplementationLoaderService;
    @Autowired
    private ProcessRemarkDataService processRemarkDataService;
    @Autowired
    private EvidenceElementDataService evidenceElementDataService;
    @Autowired
    private EvidenceDataService evidenceDataService;
    @Autowired
    private ConsolidatorFactory consolidatorFactory;

    public ConsolidatorServiceImpl() {
        super();
    }

    @Override
    public Collection<ProcessResult> consolidate(Collection<ProcessResult> grossResultList,
            Collection<Test> testList) {
        List<ProcessResult> resultList = new ArrayList<>();
        for (Test test : testList) {
            // if the rule archive name is empty, the test is not launched
            if (!test.getNoProcess()) {
                RuleImplementation ruleImplementation = ruleImplementationLoaderService.loadRuleImplementation(test);
                Consolidator consolidator = consolidatorFactory.create(
                        grossResultList, 
                        ruleImplementation, 
                        ProcessRemarkServiceFactory.create(
                            processRemarkDataService, 
                            evidenceElementDataService, 
                            evidenceDataService));
                consolidator.run();
                resultList.addAll(consolidator.getResult());
            }
        }
        return resultList;
    }

}