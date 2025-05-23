/*
 * Copyright 2022-2025 Google LLC
 * Copyright 2013-2021 CompilerWorks
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
package com.google.edwmigration.dumper.application.dumper.connector.teradata.query.model;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;

@AutoValue
public abstract class NaryExpression implements Expression {
  public abstract NaryOperator operator();

  public abstract ImmutableList<Expression> subexpressions();

  public static NaryExpression create(
      NaryOperator operator, ImmutableList<Expression> subexpressions) {
    return new AutoValue_NaryExpression(operator, subexpressions);
  }

  public enum NaryOperator {
    AND("AND"),
    OR("OR"),
    ADD("+"),
    SUBTRACT("-"),
    MUL("*");

    public final String serializedForm;

    NaryOperator(String serializedForm) {
      this.serializedForm = serializedForm;
    }
  }
}
