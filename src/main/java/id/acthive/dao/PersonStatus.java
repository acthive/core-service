package id.acthive.dao;

import io.micronaut.core.annotation.Introspected;

@Introspected
public enum PersonStatus {
  NOT_ACTIVE,
  ACTIVE,
  DELETED
}
