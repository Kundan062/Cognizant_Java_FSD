package com.example.ems.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Exercise 7: Enabling Entity Auditing.
 * Supplies the "current user" used to populate @CreatedBy / @LastModifiedBy.
 * Falls back to "system" when there is no authenticated user (e.g. this demo,
 * which has no security configured), so auditing still works out of the box.
 */
@Component("auditorAware")
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getPrincipal())) {
            return Optional.of("system");
        }
        return Optional.of(authentication.getName());
    }
}
