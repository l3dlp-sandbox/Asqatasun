/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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
package org.asqatasun.analyser;

import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.subject.WebResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author jkowalczyk
 */
@Service(value = "analyserService")
public class AnalyserServiceImpl implements AnalyserService {

    private AnalyserFactory analyserFactory;

    @Autowired
    public AnalyserServiceImpl(AnalyserFactory analyserFactory){
        this.analyserFactory = analyserFactory;
    }

    @Override
    public void analyse(WebResource webResource, Audit audit) {
        Analyser analyser = analyserFactory.create(webResource, audit);
        analyser.run();
    }


}
