package net.lycee.web.enquete.api.controller.space;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SpacePostRequest {
    @NotEmpty
    public String name;

    @NotNull
    public Long closeTime;
}