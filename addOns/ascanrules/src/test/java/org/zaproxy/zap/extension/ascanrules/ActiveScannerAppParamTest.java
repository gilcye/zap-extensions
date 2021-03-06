/*
 * Zed Attack Proxy (ZAP) and its related class files.
 *
 * ZAP is an HTTP/HTTPS proxy for assessing web application security.
 *
 * Copyright 2018 The ZAP Development Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.zaproxy.zap.extension.ascanrules;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import org.junit.jupiter.api.Test;
import org.parosproxy.paros.core.scanner.AbstractAppParamPlugin;
import org.parosproxy.paros.core.scanner.Plugin;

public abstract class ActiveScannerAppParamTest<T extends AbstractAppParamPlugin>
        extends ActiveScannerTest<T> {

    @Test
    public void shouldSendReasonableNumberOfMessagesInLowStrength() throws Exception {
        // Given
        Plugin.AttackStrength strength = Plugin.AttackStrength.LOW;
        rule.setAttackStrength(strength);
        rule.init(getHttpMessage("?p=v"), parent);
        // When
        rule.scan();
        // Then
        assertThat(
                httpMessagesSent,
                hasSize(lessThanOrEqualTo(getRecommendMaxNumberMessagesPerParam(strength))));
        assertThat(alertsRaised, hasSize(0));
    }

    @Test
    public void shouldSendReasonableNumberOfMessagesInMediumStrength() throws Exception {
        // Given
        Plugin.AttackStrength strength = Plugin.AttackStrength.MEDIUM;
        rule.setAttackStrength(strength);
        rule.init(getHttpMessage("?p=v"), parent);
        // When
        rule.scan();
        // Then
        assertThat(
                httpMessagesSent,
                hasSize(lessThanOrEqualTo(getRecommendMaxNumberMessagesPerParam(strength))));
        assertThat(alertsRaised, hasSize(0));
    }

    @Test
    public void shouldSendReasonableNumberOfMessagesInHighStrength() throws Exception {
        // Given
        Plugin.AttackStrength strength = Plugin.AttackStrength.HIGH;
        rule.setAttackStrength(strength);
        rule.init(getHttpMessage("?p=v"), parent);
        // When
        rule.scan();
        // Then
        assertThat(
                httpMessagesSent,
                hasSize(lessThanOrEqualTo(getRecommendMaxNumberMessagesPerParam(strength))));
        assertThat(alertsRaised, hasSize(0));
    }

    @Test
    public void shouldSendReasonableNumberOfMessagesInInsaneStrength() throws Exception {
        // Given
        Plugin.AttackStrength strength = Plugin.AttackStrength.INSANE;
        rule.setAttackStrength(strength);
        rule.init(getHttpMessage("?p=v"), parent);
        // When
        rule.scan();
        // Then
        assertThat(
                httpMessagesSent,
                hasSize(lessThanOrEqualTo(getRecommendMaxNumberMessagesPerParam(strength))));
        assertThat(alertsRaised, hasSize(0));
    }
}
