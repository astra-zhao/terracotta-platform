/*
 * The contents of this file are subject to the Terracotta Public License Version
 * 2.0 (the "License"); You may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://terracotta.org/legal/terracotta-public-license.
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 *
 * The Covered Software is Connection API.
 *
 * The Initial Developer of the Covered Software is
 * Terracotta, Inc., a Software AG company
 */

package org.terracotta.voltron.proxy;

import org.terracotta.entity.InvocationBuilder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Alex Snaps
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Async {

  Ack value() default Ack.NONE;

  enum Ack {
    NONE {
      @Override
      public void applyTo(InvocationBuilder builder) {
        // no-op
      }
    },

    RECEIVED {
      @Override
      public void applyTo(InvocationBuilder builder) {
        builder.ackReceived();
      }
    };

    public abstract void applyTo(InvocationBuilder builder);
  }


}
