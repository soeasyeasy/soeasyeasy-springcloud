package com.soeasyeasy.common.util;

public class TenantContext {
    private static final ThreadLocal<String> TENANT = new ThreadLocal<>();

    public static void setTenant(String tenantId) {
        TENANT.set(tenantId);
    }

    public static String getTenant() {
        return TENANT.get();
    }

    public static void clear() {
        TENANT.remove();
    }
}
