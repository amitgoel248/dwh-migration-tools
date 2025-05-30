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
package com.google.edwmigration.dumper.application.dumper.connector;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Iterator;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.time.DurationFormatUtils;

/** @author shevek */
public class ZonedIntervalIterable implements Iterable<ZonedInterval> {
  private final ZonedDateTime start;
  private final ZonedDateTime end;
  private final Duration intervalDuration;

  ZonedIntervalIterable(
      @Nonnull ZonedDateTime start,
      @Nonnull ZonedDateTime end,
      @Nonnull Duration intervalDuration,
      IntervalExpander expander) {
    this.intervalDuration =
        Preconditions.checkNotNull(intervalDuration, "Interval duration was null.");
    Preconditions.checkNotNull(start, "Start was null.");
    Preconditions.checkNotNull(end, "End was null.");

    Preconditions.checkState(
        start.isBefore(end), "Start date [%s] must precede end date [%s]", start, end);

    ZonedInterval expandedInterval = expander.apply(new ZonedInterval(start, end));

    this.start = expandedInterval.getStart();
    this.end = expandedInterval.getEndExclusive();
  }

  @Nonnull
  public ZonedDateTime getStart() {
    return start;
  }

  @Nonnull
  public ZonedDateTime getEnd() {
    return this.end;
  }

  @Nonnull
  public Duration getIntervalDuration() {
    return intervalDuration;
  }

  private class DatesIterator extends AbstractIterator<ZonedInterval> {

    private ZonedDateTime current;

    public DatesIterator() {
      this.current = start;
    }

    @Override
    protected ZonedInterval computeNext() {
      if (current.isEqual(end) || current.isAfter(end)) return endOfData();

      ZonedDateTime next = current.plus(intervalDuration);
      if (next.isAfter(end)) return endOfData();

      ZonedInterval result = new ZonedInterval(current, next);
      current = next;
      return result;
    }
  }

  @Nonnull
  @Override
  public Iterator<ZonedInterval> iterator() {
    return new DatesIterator();
  }

  @Override
  public String toString() {
    return String.format(
        "from [%s] to [%s] every [%s]ms",
        start,
        end,
        DurationFormatUtils.formatDurationWords(intervalDuration.toMillis(), true, true));
  }
}
