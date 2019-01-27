/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
 *
 * This program is free software: you can redistribute it and/or modify
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
package org.asqatasun.rules.rgaa32016;

import org.asqatasun.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.asqatasun.rules.elementchecker.link.LinkTitlePertinenceChecker;
import org.asqatasun.rules.elementselector.CompositeLinkElementSelector;

/**
 * Implementation of the rule 6.2.3 of the referential RGAA 3.2016
 * <br/>
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/rgaa3.2016/06.Links/Rule-6-2-3.html">the rule 6.2.3 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#test-6-2-3">6.2.3 rule specification</a>
 *
 */
public class Rgaa32016Rule060203 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /**
     * Default constructor
     */
    public Rgaa32016Rule060203  () {
        super(new CompositeLinkElementSelector(false, false), new LinkTitlePertinenceChecker());
    }
}
