package stima.core.properties;

import stima.core.status.Status;

public interface PreventsStatus {
    boolean canApplyStatus(Status status);
}
