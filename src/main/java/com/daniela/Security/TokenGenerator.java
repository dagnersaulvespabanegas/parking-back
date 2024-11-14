package com.daniela.Security;

public interface TokenGenerator {
    String build(Object id, Object role);
}