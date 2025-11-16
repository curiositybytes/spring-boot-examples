package com.interview.boot.config;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "task")
public class TaskProperties {

    private final Limits limits;
    private final Reminders reminders;

    @ConstructorBinding
    public TaskProperties(Limits limits, Reminders reminders) {
        this.limits = limits;
        this.reminders = reminders;
    }

    public Limits getLimits() {
        return limits;
    }

    public Reminders getReminders() {
        return reminders;
    }

    // ------------ Nested Classes ------------

    @Validated
    public static class Limits {
        @Min(1)
        private final int maxTasksPerUser;

        @NotNull
        private final Boolean autoDeleteOverdue;

        @ConstructorBinding
        public Limits(int maxTasksPerUser, Boolean autoDeleteOverdue) {
            this.maxTasksPerUser = maxTasksPerUser;
            this.autoDeleteOverdue = autoDeleteOverdue;
        }

        public int getMaxTasksPerUser() {
            return maxTasksPerUser;
        }

        public Boolean getAutoDeleteOverdue() {
            return autoDeleteOverdue;
        }
    }

    @Validated
    public static class Reminders {

        private final boolean enabled;

        @Email
        private final String email;

        @Min(1)
        private final int intervalMinutes;

        @ConstructorBinding
        public Reminders(boolean enabled, String email, int intervalMinutes) {
            this.enabled = enabled;
            this.email = email;
            this.intervalMinutes = intervalMinutes;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public String getEmail() {
            return email;
        }

        public int getIntervalMinutes() {
            return intervalMinutes;
        }
    }
}

