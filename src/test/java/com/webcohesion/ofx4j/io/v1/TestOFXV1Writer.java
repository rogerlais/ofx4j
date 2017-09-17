/*
 * Copyright 2008 Web Cohesion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.webcohesion.ofx4j.io.v1;

import java.io.StringWriter;
import junit.framework.TestCase;

/**
 * @author Ryan Heaton
 */
public class TestOFXV1Writer extends TestCase {

    /**
     * tests character escaping
     */
    public void testCharacterEscaping() throws Exception {
        StringWriter value = new StringWriter();
        OFXV1Writer writer = new OFXV1Writer(value);
        //Original conditions
        writer.setWriteAttributesOnNewLine(false); //compact format(one line)
        writer.setTabLength(0); //dont indent and dont ending single attribute
        writer.writeElement("NAME", "&<>");
        writer.close();
        assertEquals("<NAME>&amp;&lt;&gt;", value.toString());
        //well/beauty formatted XML
        writer.setTabLength(4);
        writer.setWriteAttributesOnNewLine(true);
        writer.setAllwaysCloseElement(true);
        writer.setWriteValuesOnNewLine(true);
        value.getBuffer().setLength(0); //clear old value
        writer.writeElement("NAME", "&<>");
        String fourSpaces = "    ";
        assertEquals("<NAME>\r\n" + fourSpaces + "&amp;&lt;&gt;\r\n" + fourSpaces + "</NAME>\r\n", value.toString());
    }

}
